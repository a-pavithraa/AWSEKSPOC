variable "oidc_thumbprint_list" {
  type    = list(any)
  default = []
}

variable "region" {
  default = "us-east-1"
}
variable "account_id" {
  description="Account ID"
}

variable "prefix" {
  default = "travelservice"
}

variable "instance_type" {
  default = "t2.medium"
}

variable "vpc_cidr_block" {
  description = "VPC CIDR Block"
  type        = string
  default     = "10.16.0.0/16"
}

variable "hosted_zone_id"{
description="Hosted Zone Id for External DNS service account"
type= string
}

