variable "aws_region" {
  description = "La région AWS"
  type        = string
  default     = "us-east-1" # Région mise à jour
}

variable "cluster_name" {
  description = "Nom du cluster EKS"
  type        = string
  default     = "k8s-devops-auto" # Nom du cluster mis à jour
}

variable "subnet_ids" {
  description = "IDs des sous-réseaux"
  type        = list(string)
  default     = ["subnet-07dc97dd9773d4ece", "subnet-02999fde71944e009"] # Valeurs par défaut
}

variable "role_arn" {
  description = "ARN du rôle IAM pour EKS"
  type        = string
  default     = "arn:aws:iam::832695845398:role/LabRole" # Valeur par défaut
}

variable "vpc_id" {
  description = "L'ID du VPC pour le cluster EKS"
  type        = string
  default     = "vpc-0b83bacf0e8efb093" # Remplacez par votre ID de VPC réel
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "172.31.0.0/16" # Modifiez-le selon vos besoins
}
