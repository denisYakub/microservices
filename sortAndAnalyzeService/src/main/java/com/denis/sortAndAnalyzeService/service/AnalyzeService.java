package com.denis.sortAndAnalyzeService.service;

import com.denis.sortAndAnalyzeService.configuration.Config;
import com.denis.sortAndAnalyzeService.dto.OccupationEntity;
import com.denis.sortAndAnalyzeService.dto.PersonalEntity;
import com.denis.sortAndAnalyzeService.dto.UserEntity;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class AnalyzeService {
    @Value("${application.URL_BD_SERVICE}")
    public String URL_BD_SERVICE;
    @Value("${application.NUMBER_OF_THREADS}")
    public int NUMBER_OF_THREADS;

    @Autowired
    public final Gson gson;

    public List<Integer> analyzeUsersForBots(int numberOfUsers){
        List<Integer> idsOfBots = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);

        int usersPerThread = (int)Math.ceil((double) numberOfUsers / this.NUMBER_OF_THREADS);

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int thread = 0; thread < this.NUMBER_OF_THREADS; thread++){
            int fromId = thread * usersPerThread;
            int toId = Math.min(fromId + usersPerThread, numberOfUsers);

            executorService.submit(() -> {
                for (int i = fromId; i < toId; i++){
                    UserEntity user = getUserEntityById(i);

                    if(user != null && isBot(user))
                        idsOfBots.add(user.getId());
                }
            });
        }

        executorService.shutdown();

        return idsOfBots;
    }

    public UserEntity getUserEntityById(int id){
        var response = Config.restTemplate().getForEntity(
                this.URL_BD_SERVICE + "/" + id,
                String.class
        );

        if(response.getBody() == null)
            return null;

        return gson.fromJson(response.getBody(), UserEntity.class);
    }

    public boolean isBot(UserEntity user) {
        // 1. Проверка личной информации
        if (isNullOrEmpty(user.getFirst_name()) ||
                isNullOrEmpty(user.getLast_name()) ||
                isNullOrEmpty(user.getBdate())) {
            return true;
        }

        // 2. Проверка интересов
        if (isNullOrEmpty(user.getAbout()) &&
                isNullOrEmpty(user.getActivities()) &&
                isNullOrEmpty(user.getBooks()) &&
                isNullOrEmpty(user.getMusic()) &&
                isNullOrEmpty(user.getMovies()) &&
                isNullOrEmpty(user.getGames())) {
            return true;
        }

        // 3. Наличие фото
        if (user.getHas_photo() == 0) {
            return true;
        }

        // 4. Отсутствие связанных данных
        if (user.getRelatives() == null || user.getRelatives().isEmpty()) {
            return true;
        }
        if (user.getCareer() == null || user.getCareer().isEmpty()) {
            return true;
        }
        if (user.getCity() == null) {
            return true;
        }

        // 5. Проверка профессии
        OccupationEntity occupation = user.getOccupation();
        if (occupation != null && (occupation.getType() == null || occupation.getType().contains("bot"))) {
            return true;
        }

        // 6. Проверка монотонности данных
        PersonalEntity personal = user.getPersonal();
        if (personal != null && (personal.getLangs() != null && personal.getLangs().size() == 1)) {
            return true;
        }

        // Если не попадает ни под одно из условий, то человек
        return false;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
