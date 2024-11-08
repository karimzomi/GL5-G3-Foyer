provider "aws" {
  region = var.aws_region
}

resource "aws_eks_cluster" "my_cluster" {
  name     = var.cluster_name
  role_arn = var.role_arn
  version  = "1.30"

  vpc_config {
    subnet_ids = var.subnet_ids
  }
}

resource "aws_eks_node_group" "my_node_group" {
  cluster_name    = aws_eks_cluster.my_cluster.name
  node_group_name = "node-grp"
  node_role_arn   = var.role_arn
  subnet_ids      = var.subnet_ids

  scaling_config {
    desired_size = 1
    max_size     = 1
    min_size     = 1
  }

  instance_types = ["t3.medium"]
}

data "aws_security_group" "eks_cluster_sg" {
  filter {
    name   = "tag:aws:eks:cluster-name"
    values = ["${var.cluster_name}"]
  }
  depends_on = [aws_eks_cluster.my_cluster]
}

resource "aws_security_group_rule" "allow_30000_traffic" {
  type              = "ingress"
  from_port         = 30000
  to_port           = 30000
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = data.aws_security_group.eks_cluster_sg.id
}

resource "aws_security_group_rule" "allow_8082_traffic" {
  type              = "ingress"
  from_port         = 32000
  to_port           = 32000
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = data.aws_security_group.eks_cluster_sg.id
}

resource "aws_security_group_rule" "allow_30001_traffic" {
  type              = "ingress"
  from_port         = 32001
  to_port           = 32001
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = data.aws_security_group.eks_cluster_sg.id
}