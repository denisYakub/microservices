# Используем официальный образ OpenJDK
FROM openjdk:23-jdk-slim

# Указываем рабочую директорию
WORKDIR /sortAndAnalyzeService

# Копируем jar-файл вашего Spring Boot приложения
COPY build/libs/sortAndAnalyzeService-0.0.1-SNAPSHOT.jar sort-and-analyze-service.jar

# Указываем порт, на котором работает приложение
EXPOSE 8084

# Устанавливаем команду для запуска приложения
CMD ["java", "-jar", "sort-and-analyze-service.jar"]