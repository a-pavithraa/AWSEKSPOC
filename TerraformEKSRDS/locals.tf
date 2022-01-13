locals {
  cluster_name                  = "${var.prefix}-${random_string.suffix.result}"
  k8s_service_account_namespace = "default"
  k8s_service_account_name      = "pills"
  dynamodb_table_name               = "BookmarkedHotels"

  common_tags = {
    app     = "${var.prefix}"
    version = "V1"
  }
}
