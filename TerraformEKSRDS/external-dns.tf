resource "aws_iam_policy" "policy" {
  name        = "test_policy"
  path        = "/"
  description = "My test policy"

  # Terraform's "jsonencode" function converts a
  # Terraform expression result to valid JSON syntax.
  policy = jsonencode({
    "Version" : "2012-10-17",
    "Statement" : [
      {
        "Effect" : "Allow",
        "Action" : [
          "route53:ChangeResourceRecordSets"
        ],
        "Resource" : [
          "arn:aws:route53:::hostedzone/*"
        ]
      },
      {
        "Effect" : "Allow",
        "Action" : [
          "route53:ListHostedZones",
          "route53:ListResourceRecordSets"
        ],
        "Resource" : [
          "*"
        ]
      }
    ]
  })
}
data "aws_caller_identity" "current" {}
data "aws_iam_policy_document" "iam_role_test" {
  statement {
    actions = ["sts:AssumeRoleWithWebIdentity"]

    principals {
      type = "Federated"
      identifiers = [
        "arn:aws:iam::${data.aws_caller_identity.current.account_id}:oidc-provider/${module.eks.cluster_oidc_issuer_url}"
      ]
    }
    # Limit the scope so that only our desired service account can assume this role
    condition {
      test     = "StringEquals"
      variable = "${module.eks.cluster_oidc_issuer_url}:sub"
      values = [
        "system:serviceaccount:${local.k8s_service_account_namespace}:${local.k8s_service_account_name}"
      ]
    }
  }
}

resource "aws_iam_role" "iam_role_test" {
  name               = "iam-role-test"
  assume_role_policy = data.aws_iam_policy_document.iam_role_test.json
}

resource "aws_iam_role_policy_attachment" "test-attach" {
  role       = aws_iam_role.iam_role_test.name
  policy_arn = aws_iam_policy.policy.arn
}

resource "kubernetes_service_account" "iam_role_test" {
  metadata {
    name      = local.k8s_service_account_name
    namespace = local.k8s_service_account_namespace
    annotations = {
      # This annotation is needed to tell the service account which IAM role it
      # should assume
      "eks.amazonaws.com/role-arn" = aws_iam_role.iam_role_test.arn
    }
  }
}