locals {
  cluster_name                  = "${var.prefix}-${random_string.suffix.result}"
  k8s_service_account_namespace = "default"
  k8s_service_account_name      = "hotelservice"
  dynamodb_table_name               = "BookmarkedHotels"
  cluster_version = "1.21"

  common_tags = {
    app     = "${var.prefix}"
    version = "V1"
  }
}
