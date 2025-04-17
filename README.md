# SWE 645 - Assignment 3: Containerizing Microservices

##  Project Members

- **Pranav Manish Reddi Madduri** (G01504276): Set up Amazon RDS database, connected via MySQL.
- **Lavanya Jillella** (G01449670): Implemented CI/CD using GitHub and Jenkins; tested GET and POST endpoints via Postman.
- **Sneha Rathi** (G01449688): Deployed the Spring Boot app on Kubernetes using Rancher.
- **Chennu Naga Venkata Sai** (G01514409): Built and pushed Docker image to Docker Hub.

---

## Project Overview

This project demonstrates how to build, containerize, and deploy a Spring Boot microservice that processes student survey data. The application stores form submissions in an Amazon RDS MySQL database, and supports full CRUD operations via REST endpoints. Deployment is managed with Rancher and CI/CD is automated through Jenkins. Postman is used to test the application endpoints.

---

## Prerequisites

- AWS account with permissions to manage EC2 and RDS instances
- Two Ubuntu-based EC2 instances with proper security group configurations
- An Amazon RDS MySQL instance with open access for MySQL clients
- Basic knowledge of:
  - Docker (for building and pushing images)
  - Rancher (for orchestration and deployment)
  - Jenkins (for CI/CD pipelines)
  - Postman (for REST API testing)

---

## Connecting to Amazon RDS

1. Open **MySQL Workbench**, click the **+** next to *MySQL Connections*.
2. Enter a **connection name** and the **RDS endpoint** from the AWS console.
3. Provide the **username and password** used during RDS setup.
4. Click **Test Connection**, then **OK** to save.
5. Create a new database named `Assignment` and a table `homework` using the provided SQL script.

---

## Docker Build Instructions

```bash
# Build the Docker image
docker build -t pranav1706/feedback:latest .

# Run the container locally to verify
docker run -it -p 8182:8080 pranav1706/feedback:latest

# Open in browser: http://localhost:8182/pranav1706/

# Push image to Docker Hub
docker push pranav1706/feedback:latest
```

---

## Deploying on Rancher

1. Open the **Rancher UI** → go to **Workloads** → **Deployments**.
2. Click **Create**, fill in:
   - Deployment name
   - Replicas: `3`
3. In the **Containers** section:
   - Set image: `your-dockerhub-username/your-image-name:tag`
   - Click **Add Port**:
     - Type: `Loadbalancer`
     - Port: `8080`
4. Click **Create** and wait until deployment is active.
5. Go to **Service Discovery → Services** → click service link to open the app.

---

## Jenkins CI/CD Pipeline Setup

1. Go to Jenkins Dashboard → click **New Item** → name the pipeline.
2. Select **Pipeline**, then:
   - Under **Build Triggers**, check **Poll SCM** and use: `* * * * *`
3. In the **Pipeline** section:
   - Set **Definition** to `Pipeline script from SCM`
   - Choose **Git** as SCM and enter your GitHub repo URL
   - Add credentials
   - Branch specifier: `*/main`
4. Make a change in GitHub (e.g., update HTML) and commit.
   - Jenkins will auto-trigger a build, or use **Build Now**
   - Refresh your app to verify deployment

---

## Postman API Testing

> Make sure to generate a **Bearer Token** via Rancher and add it in Postman under Authorization.

- **GET Request**
  - **URL**: `https://52.205.14.16/k8s/clusters/c-nm4sx/api/v1/namespaces/default/services/http:my-springboot-app-service:80/proxy/api/surveys`
  - Returns all survey records in JSON format.

- **POST Request**
  - **URL**: `https://52.205.14.16/k8s/clusters/cnm4sx/api/v1/namespaces/default/services/http:my-springboot-app-service:80/proxy/api/surveys`
  - **Body**: raw JSON data (use `application/json`)
  - Submits a new survey record to the database.

- **PUT Request**
  - **URL**: `https://52.205.14.16/k8s/clusters/cnm4sx/api/v1/namespaces/default/services/http:my-springboot-app-service:80/proxy/api/surveys/{id}`
  - Updates an existing survey entry.

- **DELETE Request**
  - **URL**: `https://52.205.14.16/k8s/clusters/cnm4sx/api/v1/namespaces/default/services/http:my-springboot-app-service:80/proxy/api/surveys/{id}`
  - Deletes a specific survey entry.

---
