resource "aws_iam_policy" "externaldnspolicy" {
  name        = "externaldns_access_policy"
  path        = "/"
  description = "externaldns Access Policy"
 
  policy = jsonencode({
    "Version" : "2012-10-17",
    "Statement" : [     
       {
      "Effect": "Allow",
      "Action": [
        "route53:ChangeResourceRecordSets"
      ],
      "Resource": [
        "arn:aws:route53:::hostedzone/${var.hosted_zone_id}"
      ]
    },
    {
      "Effect": "Allow",
      "Action": [
        "route53:ListHostedZones",
        "route53:ListResourceRecordSets"
      ],
      "Resource": [
        "*"
      ]
    }
    ]
  })
}

data "aws_iam_policy_document" "sa_externaldns_role" {
  statement {
    actions = ["sts:AssumeRoleWithWebIdentity"]

    principals {
      type = "Federated"
      identifiers = [
        "arn:aws:iam::${data.aws_caller_identity.current.account_id}:oidc-provider/${replace(module.eks.cluster_oidc_issuer_url, "https://", "")}"
      ]
    }
    
    condition {
      test     = "StringEquals"
      variable = "${replace(module.eks.cluster_oidc_issuer_url, "https://", "")}:sub"
      values = [
        "system:serviceaccount:${local.k8s_service_account_namespace}:${local.k8s_externaldns_service_account_name}"
      ]
    }
    condition {
      test     = "StringEquals"
      variable = "${replace(module.eks.cluster_oidc_issuer_url, "https://", "")}:aud"
      values = [
       "sts.amazonaws.com"
      ]
    }
  }
}

resource "aws_iam_role" "sa_externaldns_role" {
  name               = "sa-externaldns-role"
  assume_role_policy = data.aws_iam_policy_document.sa_externaldns_role.json
}

resource "aws_iam_role_policy_attachment" "externaldns-policy-attach" {
  role       = aws_iam_role.sa_externaldns_role.name
  policy_arn = aws_iam_policy.externaldnspolicy.arn
}

resource "kubernetes_service_account" "sa_externaldns_role" {
  metadata {
    name      = local.k8s_externaldns_service_account_name
    namespace = local.k8s_service_account_namespace
    annotations = {
      # This annotation is needed to tell the service account which IAM role it
      # should assume
      "eks.amazonaws.com/role-arn" = aws_iam_role.sa_externaldns_role.arn
    }
  }
}