locals {
  cluster_name                  = "${var.prefix}-${random_string.suffix.result}"
  k8s_service_account_namespace = "default"
  k8s_service_account_name      = "hotelservice"
  k8s_externaldns_service_account_name="external-dns"
  dynamodb_table_name               = "BookmarkedHotels"
  cluster_version = "1.21"

  common_tags = {
    app     = "${var.prefix}"
    version = "V1"
  }
}
