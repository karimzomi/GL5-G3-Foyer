FROM jenkins/jenkins:lts-jdk17
USER root
RUN apt-get update && apt-get install -y lsb-release 

RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
    https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
    signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
    https://download.docker.com/linux/debian \
    $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
RUN apt-get install -y wget
RUN apt-get install -y curl && \
    curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    chmod +x kubectl && \
    mv kubectl /usr/local/bin/
# install helm
RUN wget https://get.helm.sh/helm-v3.16.1-linux-amd64.tar.gz -O helm.tar.gz && \
    tar -zxvf helm.tar.gz && \
    mv linux-amd64/helm /usr/local/bin/helm
# install aws cli
RUN  curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" && \
    unzip awscliv2.zip && \
    ./aws/install
# install terraform
RUN wget https://releases.hashicorp.com/terraform/1.9.8/terraform_1.9.8_linux_amd64.zip -O terraform.zip && \
    unzip terraform.zip && \
    mv terraform /usr/local/bin/
RUN wget https://releases.hashicorp.com/terraform-provider-aws/5.73.0/terraform-provider-aws_5.73.0_linux_amd64.zip -O terraform-provider-aws.zip && \
    unzip -qo terraform-provider-aws.zip && \
    mv terraform-provider-aws_v5.73.0_x5 /usr/local/bin/
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"