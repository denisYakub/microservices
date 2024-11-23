# Используем официальный образ OpenJDK
FROM openjdk:23-jdk-slim

# Указываем рабочую директорию
WORKDIR /KafkaService

# Копируем jar-файл вашего Spring Boot приложения
COPY build/libs/KafkaService-0.0.1-SNAPSHOT.jar kafka-service.jar

# Указываем порт, на котором работает приложение
EXPOSE 8083

# Устанавливаем команду для запуска приложения
CMD ["java", "-jar", "kafka-service.jar"]