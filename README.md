# k8-learning

A sample Spring Boot application with Kafka and PostgreSQL, designed to run on Kubernetes (minikube).

---

## Features
- Spring Boot REST API
- Kafka producer/consumer
- PostgreSQL persistence
- Kubernetes manifests for local development (minikube)

---

## Prerequisites
- Java 21
- Gradle
- Docker
- [minikube](https://minikube.sigs.k8s.io/docs/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)

---

## Quick Start

### 1. **Build and Push the Docker Image (if you want to use your own)**
```sh
./gradlew build -x test
docker build -t meirfuces/k8-learning:1.0.2 .
docker push meirfuces/k8-learning:1.0.2
```
Or use the public image: [`meirfuces/k8-learning:1.0.2`](https://hub.docker.com/r/meirfuces/k8-learning)

---

### 2. **Deploy to Kubernetes**
```sh
kubectl apply -f postgres-deployment.yaml
kubectl apply -f zookeeper-deployment.yaml
kubectl apply -f kafka-deployment.yaml
kubectl apply -f app-configmap.yaml
kubectl apply -f app-secret.yaml
kubectl apply -f app-deployment.yaml
```

---

### 3. **Wait for All Pods to Be Running**
```sh
kubectl get pods
```

---

### 4. **Port-Forward the App for Local Testing**
```sh
kubectl port-forward svc/k8-learning 8888:8080
```
- Leave this terminal open.

---

### 5. **Test the Publish Endpoint**
```sh
curl "http://localhost:8888/publish?id=123&message=hello"
```
You should see:
```
Message sent: hello with id: 123
```

---

### 6. **(Optional) Access Swagger UI**
If enabled, open:
```
http://localhost:8888/swagger-ui/index.html
```

---

## Database Access
- To connect to Postgres from your laptop (e.g., with IntelliJ):
  ```sh
  kubectl port-forward svc/postgres 5432:5432
  # Then connect to localhost:5432 with user/password from postgres-deployment.yaml
  ```

---

## Configuration
- **Database credentials:** Set in `postgres-deployment.yaml` and `app-secret.yaml`.
- **Kafka:** Uses the `kafka` service in the cluster.
- **No secrets or passwords should be committed for production!**

---

## License
MIT 