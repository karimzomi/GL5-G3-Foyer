variable "aws_region" {
  description = "La région AWS"
  type        = string
  default     = "us-east-1" # Région mise à jour
}

variable "cluster_name" {
  description = "Nom du cluster EKS"
  type        = string
  default     = "igl5-cluster" # Nom du cluster mis à jour
}

variable "subnet_ids" {
  description = "IDs des sous-réseaux"
  type        = list(string)
  default     = ["subnet-0a386ff1b85f106ac", "subnet-04222bd3046e7adb6"] # Valeurs par défaut
}

variable "role_arn" {
  description = "ARN du rôle IAM pour EKS"
  type        = string
  default     = "arn:aws:iam::462279347209:role/LabRole" # Valeur par défaut
}

variable "vpc_id" {
  description = "L'ID du VPC pour le cluster EKS"
  type        = string
  default     = "vpc-0c1e60b36ab914e05" # Remplacez par votre ID de VPC réel
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "172.31.0.0/16" # Modifiez-le selon vos besoins
}
