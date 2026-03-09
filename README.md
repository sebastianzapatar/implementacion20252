# Sindocker - Spring Boot App

Esta aplicación es un proyecto backend desarrollado con **Spring Boot** (Java 21) que incluye una integración con **PostgreSQL** y soporta despliegue mediante **Docker** y **Docker Compose**.

## 📌 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Docker](https://docs.docker.com/get-docker/) y [Docker Compose](https://docs.docker.com/compose/install/) (Recomendado para correr todo con contenedores)
- [Java 21](https://jdk.java.net/21/) (Solo si deseas correr el proyecto localmente sin Docker)
- [Maven](https://maven.apache.org/) (Opcional, el proyecto incluye el wrapper de Maven `mvnw`)

## ⚙️ Configuración del Entorno

El proyecto requiere un archivo `.env` que contenga las variables de entorno para funcionar correctamente.

1. En la raíz del proyecto, copia el archivo de plantilla `env.template` y renómbralo a `.env`.
   ```bash
   cp env.template .env
   ```

2. Configura los valores dentro de `.env`. Ejemplo de configuración para desarrollo local con Docker Compose:

   ```ini
   # Configuración de la Base de Datos PostgreSQL
   POSTGRES_DB=sindocker_db
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=TuPasswordSeguro

   # Configuración de la Aplicación Spring Boot
   SPRING_APPLICATION_NAME=sindocker
   SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/sindocker_db
   SPRING_DATASOURCE_USERNAME=postgres
   SPRING_DATASOURCE_PASSWORD=TuPasswordSeguro
   SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
   ```

> [!NOTE] 
> La URL de base de datos utiliza `db` como host, que es el nombre del servicio de PostgreSQL configurado en el archivo `compose.yml`. 


## 🐳 ¿Cómo correr la aplicación?

### Opción 1: Ejecutar con Docker Compose (Recomendado)

Esta es la manera más sencilla porque levantará tanto la base de datos PostgreSQL como la aplicación de forma automatizada usando la configuración definida en `compose.yml` y el `Dockerfile`.

1. Abre una terminal en la raíz del proyecto.
2. Ejecuta el siguiente comando para construir y levantar los contenedores en segundo plano:

   ```bash
   docker compose up -d --build
   ```

3. **Verificar estado:** La aplicación estará disponible en modo API en el puerto **`8070`**. La base de datos estará expuesta localmente en el puerto **`5435`**.

> Para detener los contenedores sin borrar volúmenes:
> ```bash
> docker compose down
> ```


### Opción 2: Ejecutar Localmente con Maven

Si prefieres correr la aplicación en tu máquina local, necesitarás tener una instancia de PostgreSQL en ejecución o usar H2 (memoria). Si usas PostgreSQL local, asegúrate de actualizar el archivo `.env` para que apunte a `localhost` en lugar de `db`:

```ini
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/sindocker_db
```

1. Desde la línea de comandos en la raíz del proyecto, asegúrate de que esté configurado para tu SO y corre:

   ```bash
   # En Linux o Mac
   ./mvnw spring-boot:run
   
   # En Windows
   .\mvnw.cmd spring-boot:run
   ```

2. La aplicación Spring Boot se iniciará y estará escuchando peticiones locales (generalmente configurada para escuchar en el puerto `8070`, según el `application.yml`).

## 🛠️ Tecnologías y Dependencias Principales
- **Spring Boot 3.5.3** (Web, Data JPA, Validation)
- **Base de datos:** Driver de PostgreSQL y H2 (para desarrollo local in-memory)
- **Lombok** para reducir código boilerplate
- **dotenv-java** para soporte de lectura de variables en el archivo `.env`
