resource "aws_iam_policy" "dynamodbpolicy" {
  name        = "dynamodb_access_policy"
  path        = "/"
  description = "DynamoDB Access Policy"
  depends_on = [
    module.dynamodb_table
  ]  
  policy = jsonencode({
    "Version" : "2012-10-17",
    "Statement" : [     
       {
            
            "Effect": "Allow",           
            "Action": ["dynamodb:*"],           
            "Resource": "arn:aws:dynamodb:${var.region}:${var.account_id}:table/${local.dynamodb_table_name}"
        }
    ]
  })
}
data "aws_caller_identity" "current" {}
data "aws_iam_policy_document" "sa_dynamodb_role" {
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
        "system:serviceaccount:${local.k8s_service_account_namespace}:${local.k8s_service_account_name}"
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

resource "aws_iam_role" "sa_dynamodb_role" {
  name               = "sa-dynamodb-role"
  assume_role_policy = data.aws_iam_policy_document.sa_dynamodb_role.json
}

resource "aws_iam_role_policy_attachment" "test-attach" {
  role       = aws_iam_role.sa_dynamodb_role.name
  policy_arn = aws_iam_policy.dynamodbpolicy.arn
}

resource "kubernetes_service_account" "sa_dynamodb_role" {
  metadata {
    name      = local.k8s_service_account_name
    namespace = local.k8s_service_account_namespace
    annotations = {
      # This annotation is needed to tell the service account which IAM role it
      # should assume
      "eks.amazonaws.com/role-arn" = aws_iam_role.sa_dynamodb_role.arn
    }
  }
}