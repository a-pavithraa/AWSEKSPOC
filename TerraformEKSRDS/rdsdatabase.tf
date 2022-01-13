module "rds_sg" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "3.18.0"

  name        = "rds-sg"
  description = "Security Group with HTTP open for entire Internet (IPv4 CIDR), egress ports are all world open"
  vpc_id      = module.vpc.vpc_id

  # Egress Rule - all-all open
  egress_rules = ["all-all"]
  tags         = local.common_tags

  # Open to CIDRs blocks (rule or from_port+to_port+protocol+description)
  ingress_with_cidr_blocks = [
    {
      from_port   = 3306
      to_port     = 3306
      protocol    = -1
      description = "Allow Port 3306 from internet"
      cidr_blocks = "0.0.0.0/0"

    },
  ]
}

module "db" {

  source = "github.com/terraform-aws-modules/terraform-aws-rds.git"


  identifier = "${var.prefix}-${var.db_engine}-${var.environment}"

  engine            = var.db_engine
  engine_version    = var.db_engine_version
  instance_class    = var.db_instance_class
  storage_type      = var.db_storage_type
  allocated_storage = var.db_allocated_storage
  storage_encrypted = false

  # kms_key_id        = "arm:aws:kms:<region>:<account id>:key/<kms key id>"
  name = "${var.prefix}db"

  # NOTE: Do NOT use 'user' as the value for 'username' as it throws:
  # "Error creating DB Instance: InvalidParameterValue: MasterUsername
  # user cannot be used as it is a reserved word used by the engine"
  username = var.db_username

  password = var.db_password
  port     = var.db_instance_port

  vpc_security_group_ids = [module.rds_sg.this_security_group_id]
  publicly_accessible    = true

  maintenance_window = "Mon:00:00-Mon:03:00"
  backup_window      = "03:00-06:00"

  # disable backups to create DB faster
  backup_retention_period = 0

  tags = {
    Environment = var.environment
  }



  # DB subnet group
  subnet_ids = module.vpc.public_subnets

  # DB parameter group
  family = var.db_family

  # DB option group
  major_engine_version = var.db_major_engine_version

  # Snapshot name upon DB deletion


  # Database Deletion Protection
  skip_final_snapshot = true
  deletion_protection = false
}

/*resource "null_resource" "setup_db" {
  depends_on = [module.db] #wait for the db to be ready
  provisioner "local-exec" {
    command = "mysql -u ${var.db_username} -p${var.db_password} -h ${module.db.db_instance_endpoint} < airportsscript.sql"
  }
}*/
