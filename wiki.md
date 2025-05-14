# Java
./gradlew bootRun
./gradlew clean
./gradlew build

./gradlew build
docker build --build-arg JAR_FILE=build/libs/integrador-ai-0.0.1.jar -t integrador_ai .

docker run -d -p 8080:8080 \
-e INT_WEB_POSTGRES_URL="jdbc:postgresql://host.docker.internal:5432/integrador-web" \
-e INT_WEB_POSTGRES_USER="postgres" \
-e INT_WEB_POSTGRES_PASS="1234" \
--name integrador_web integrador_web

# Java Swagger
`localhost:8080/swagger`


# Postgres Docker
docker pull postgres
docker run --name postgres1 -e POSTGRES_PASSWORD=1234 -d -p 5432:5432 postgres

server: `localhost:5432/postgres`   
Usuario: `postgres`     
Senha: `1234`       