# Используем официальный образ OpenJDK
FROM openjdk:23-jdk-slim

# Указываем рабочую директорию
WORKDIR /pullingDataService

# Копируем jar-файл вашего Spring Boot приложения
COPY build/libs/pullingDataService-0.0.1-SNAPSHOT.jar pulling-data-service.jar

# Указываем порт, на котором работает приложение
EXPOSE 8082

# Устанавливаем команду для запуска приложения
CMD ["java", "-jar", "pulling-data-service.jar"]