# ========================
# Etapa 1: Construcción
# ========================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia solo el archivo pom.xml para aprovechar el cache de dependencias
COPY pom.xml .

# Descarga las dependencias necesarias
RUN mvn dependency:go-offline

# Copia el resto del código fuente
COPY src ./src

# Compila el proyecto y genera el JAR, sin ejecutar pruebas
RUN mvn clean package -DskipTests


# ========================
# Etapa 2: Imagen final
# ========================
FROM eclipse-temurin:21-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR desde la etapa anterior
COPY .env .env
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]




