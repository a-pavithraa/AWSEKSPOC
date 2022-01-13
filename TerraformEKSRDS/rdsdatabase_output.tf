output "rds_instance_endpoint" {
  description = "Endpoint Connection"
  value       = module.db.db_instance_endpoint
}

output "rds_instance_name" {
  description = "The database name"
  value       = module.db.db_instance_name
}

