# Используем официальный образ OpenJDK
FROM openjdk:23-jdk-slim

# Указываем рабочую директорию
WORKDIR /BdService

# Копируем jar-файл вашего Spring Boot приложения
COPY build/libs/BdService-0.0.1-SNAPSHOT.jar bd-service.jar

# Указываем порт, на котором работает приложение
EXPOSE 8081

# Устанавливаем команду для запуска приложения
CMD ["java", "-jar", "bd-service.jar"]