# Используем официальный образ OpenJDK
FROM openjdk:23-jdk-slim

# Указываем рабочую директорию
WORKDIR /vkService

# Копируем jar-файл вашего Spring Boot приложения
COPY build/libs/vkService-0.0.1-SNAPSHOT.jar vk-service.jar

# Указываем порт, на котором работает приложение
EXPOSE 8080

# Устанавливаем команду для запуска приложения
CMD ["java", "-jar", "vk-service.jar"]