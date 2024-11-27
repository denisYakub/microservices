package com.denis.sortAndAnalyzeService.service;

import com.denis.sortAndAnalyzeService.configuration.Config;
import com.denis.sortAndAnalyzeService.dto.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class AnalyzeService {
    @Value("${application.URL_BD_SERVICE}")
    public String URL_BD_SERVICE;
    @Value("${application.URL_VK_SERVICE}")
    public String URL_VK_SERVICE;
    @Value("${application.NUMBER_OF_THREADS}")
    public int NUMBER_OF_THREADS;

    @Autowired
    public final Gson gson;

    public final List<Analyze> results = Collections.synchronizedList(new ArrayList<>());
    public final List<UserEntity> users = GetUsers();

    public String UseMultiThreadToAnalyze(int from, int to) throws InterruptedException {
        int chunkSize = 1_000;
        List<int[]> ranges = new ArrayList<>();

        for(int i = from; i <= to; i+= chunkSize)
            ranges.add(new int[]{i, Math.min(to, ( i + chunkSize - 1 ))});

        ExecutorService executorService = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);
        for(var range: ranges){
            int start = range[0];
            int end = range[1];

            executorService.submit(() ->
                analyzeUsers(start, end)
            );
        }
        executorService.awaitTermination(1, TimeUnit.HOURS);
        executorService.shutdown();

        if(!results.isEmpty())
            return analyzeResults();

        return "null";
    }

    public String analyzeResults(){
        double avg_of_completeness_accounts = 0;

        int count_of_deleted_possible_bot_accounts = 0;
        int count_of_processed_accounts = 0;

        int count_processed_platforms = 0;
        int count_processed_followers = 0;

        int count_of_migrated_users = 0;
        int count_of_processed_migration = 0;

        int[] age20_users_followers_platforms = new int[4];
        int[] age30_users_followers_platforms = new int[4];
        int[] age40_users_followers_platforms = new int[4];
        int[] age60_users_followers_platforms = new int[4];
        int[] age100_users_followers_platforms = new int[4];

        var strResult = new StringBuilder();
        int size = results.size();

        for (var result: results){
            avg_of_completeness_accounts += result.averageCompleteness();

            count_of_deleted_possible_bot_accounts += result.count_of_deleted_possible_bot_accounts;
            count_of_processed_accounts += result.count_of_processed_accounts;

            count_processed_platforms += result.count_of_processed_platforms;
            count_processed_followers += result.count_of_processed_followers;

            count_of_migrated_users += result.count_of_migrated_users;
            count_of_processed_migration += result.count_of_processed_migration;

            age20_users_followers_platforms = increaseArr(age20_users_followers_platforms, result.avg20yo());
            age30_users_followers_platforms = increaseArr(age30_users_followers_platforms, result.avg30yo());
            age40_users_followers_platforms = increaseArr(age40_users_followers_platforms, result.avg40yo());
            age60_users_followers_platforms = increaseArr(age60_users_followers_platforms, result.avg60yo());
            age100_users_followers_platforms = increaseArr(age100_users_followers_platforms, result.avg100yo());
        }
        strResult.append("\navg_of_completeness_accounts: ").append(( avg_of_completeness_accounts / size )).append("\n");

        strResult.append("\ncount_of_deleted_possible_bot_accounts: ").append(count_of_deleted_possible_bot_accounts).append("\n");
        strResult.append("\ncount_of_processed_accounts: ").append(count_of_processed_accounts).append("\n");

        strResult.append("\ncount_processed_platforms: ").append(count_processed_platforms).append("\n");
        strResult.append("\ncount_processed_followers: ").append(count_processed_followers).append("\n");

        strResult.append("\nage20_users_followers_platforms: ")
                .append("Followers-").append(age20_users_followers_platforms[0])
                .append("Android-").append(age20_users_followers_platforms[1])
                .append("Iphone-").append(age20_users_followers_platforms[2])
                .append("DescTop-").append(age20_users_followers_platforms[3])
                .append("\n");

        strResult.append("\nage30_users_followers_platforms: ")
                .append("Followers-").append(age30_users_followers_platforms[0])
                .append("Android-").append(age30_users_followers_platforms[1])
                .append("Iphone-").append(age30_users_followers_platforms[2])
                .append("DescTop-").append(age30_users_followers_platforms[3])
                .append("\n");

        strResult.append("\nage40_users_followers_platforms: ")
                .append("Followers-").append(age40_users_followers_platforms[0])
                .append("Android-").append(age40_users_followers_platforms[1])
                .append("Iphone-").append(age40_users_followers_platforms[2])
                .append("DescTop-").append(age40_users_followers_platforms[3])
                .append("\n");

        strResult.append("\nage60_users_followers_platforms: ")
                .append("Followers-").append(age60_users_followers_platforms[0])
                .append(" Android-").append(age60_users_followers_platforms[1])
                .append(" Iphone-").append(age60_users_followers_platforms[2])
                .append(" DescTop-").append(age60_users_followers_platforms[3])
                .append("\n");

        strResult.append("\nage100_users_followers_platforms: ")
                .append("Followers-").append(age100_users_followers_platforms[0])
                .append(" Android-").append(age100_users_followers_platforms[1])
                .append(" Iphone-").append(age100_users_followers_platforms[2])
                .append(" DescTop-").append(age100_users_followers_platforms[3])
                .append("\n");

        strResult.append("\ncount_of_migrated_users: ").append(count_of_migrated_users).append("\n");
        strResult.append("\ncount_of_processed_migration: ").append(count_of_processed_migration).append("\n");

        return strResult.toString();
    }

    public int[] increaseArr(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length];

        for(int i = 0; i < arr1.length; i++){
            result[i] = arr1[i] + arr2[i];
        }

        return result;
    }

    /*public String analyzeResults(){
        double average_completeness = 0.0;

        int processed_platforms = 0;
        int average_android_user = 0;
        int average_iphone_user = 0;
        int average_descTop_user = 0;

        int processed_followers = 0;
        int average_followers_10 = 0;
        int average_followers_20 = 0;
        int average_followers_30 = 0;
        int average_followers_40 = 0;
        int average_followers_60 = 0;
        int average_followers_100 = 0;

        int bots = 0;
        int all_accounts = 0;

        int processed_cities = 0;
        int migrated = 0;

        int size = results.size();
        var strResult = new StringBuilder();

        for (var result: results){
            average_completeness += result.averageCompleteness();

            processed_platforms += result.count_of_processed_platforms;
            average_android_user += result.averageAgeOfAndroidUser();
            average_iphone_user += result.averageAgeOfIphoneUser();
            average_descTop_user += result.averageAgeOfDescTopUser();

            processed_followers += result.count_of_processed_followers;
            average_followers_10 += result.averageFollowersCountFor10YO();
            average_followers_20 += result.averageFollowersCountFor20YO();
            average_followers_30 += result.averageFollowersCountFor30YO();
            average_followers_40 += result.averageFollowersCountFor40YO();
            average_followers_60 += result.averageFollowersCountFor60YO();
            average_followers_100 += result.averageFollowersCountFor100YO();

            bots += result.count_of_deleted_possible_bot_accounts;
            all_accounts += result.count_of_processed_accounts;

            processed_cities += result.count_of_processed_migration;
            migrated += result.count_of_migrated_users;
        }

        strResult.append("\nAverage % of account fields completeness: ").append(( average_completeness / size )).append("\n\n");

        strResult.append("Processed users with android/iphone/descTop: ").append(processed_platforms).append("\n");
        strResult.append("Average age of android user: ").append(( average_android_user / size )).append("\n");
        strResult.append("Average age of iphone user: ").append(( average_iphone_user / size )).append("\n");
        strResult.append("Average age of descTop user: ").append(( average_descTop_user / size )).append("\n\n");

        strResult.append("Processed users with viewable followers: ").append(processed_followers).append("\n");
        strResult.append("Average number of followers from 10 to 20 years old user: ").append(( average_followers_20 / size )).append("\n");
        strResult.append("Average number of followers from 20 to 30 years old user: ").append(( average_followers_30 / size )).append("\n");
        strResult.append("Average number of followers from 30 to 40 years old user: ").append(( average_followers_40 / size )).append("\n");
        strResult.append("Average number of followers from 40 to 60 years old user: ").append(( average_followers_60 / size )).append("\n");
        strResult.append("Average number of followers from 60 to 100 years old user: ").append(( average_followers_100 / size )).append("\n\n");

        strResult.append("Possible Bot:").append(bots).append("/Processed:").append(all_accounts).append("\n\n");
        strResult.append("Migrated:").append(migrated).append("/Processed:").append(processed_cities).append("\n");

        return strResult.toString();
    }*/

    public void analyzeUsers(int fromId, int toId){
        int[] ids = getArrayOfIds(fromId, toId);
        var lastSeenAndFollowersCount = GetUserLastSeenCountersFollowersCount(ids);
        int itrForLastSeenAndFollowers = 0;

        Analyze analyze = new Analyze();

        for (int i = fromId; i < toId; i++){
            UserEntity user = getUserEntityById(i);

            if(user == null)
                continue;

            double accOccupancyPercentage = GetAccOccupancyPercentage(user);
            analyze.count_of_processed_accounts += 1;

            if(accOccupancyPercentage <= 10.0){
                analyze.addDeletedOrBot(user.getId());
                continue;
            }

            if(!user.getHome_town().isEmpty() && user.getCity() != null)
                analyze.increaseIfMigrated(user.getHome_town(), user.getCity().title);

            analyze.increaseCompleteness(accOccupancyPercentage);

            if(lastSeenAndFollowersCount == null)
                continue;

            var lastSeenAndFollowersCountOfUser = lastSeenAndFollowersCount.getResponse().get(itrForLastSeenAndFollowers);
            itrForLastSeenAndFollowers++;

            int age = user.GetAge();

            if(lastSeenAndFollowersCountOfUser == null || age == -1)
                continue;

            var lastSeen = lastSeenAndFollowersCountOfUser.getLast_seen();
            var followersCount = lastSeenAndFollowersCountOfUser.getFollowers_count();

            if(lastSeen != null) {
                int platform = lastSeen.getPlatform();

                analyze.increasePlatform(platform, age);
            }

            if(followersCount > 0) {
                analyze.increaseFollowers(age, followersCount);
            }

        }

        this.results.add(analyze);
        System.out.println(results.size() + " " + analyze.count_of_processed_accounts);
        //return analyze;
    }

    public UserEntity getUserEntityById(int id){
        try {
            var response = Config.restTemplate().getForEntity(
                    this.URL_BD_SERVICE + "/" + id,
                    String.class
            );

            if (response.getBody() == null)
                return null;

            return gson.fromJson(response.getBody(), UserEntity.class);
        } catch (Exception e) {
            return null;
        }
    }

    public AdditionInfoResponse GetUserLastSeenCountersFollowersCount(int[] ids){
        try {
            var response = Config.restTemplate().postForEntity(
                    this.URL_VK_SERVICE,
                    new vkUsersRequest(ids, new String[]{"followers_count", "last_seen", "counters"}),
                    String.class
            );

            return gson.fromJson(response.getBody(), AdditionInfoResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<UserEntity> GetUsers(){
        try {
            var response = Config.restTemplate().getForEntity(
                    this.URL_VK_SERVICE,
                    List.class
            );

            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public double GetAccOccupancyPercentage(UserEntity user){
        if(user.getFirst_name().contains("DELETED"))
            return 0.0;

        double probability = 100.0;

        if(user.getVerified() == 0)
            probability -= 2.5;

        if(user.getRelation() < 0)
            probability -= 2.5;

        if(user.getBooks().isEmpty())
            probability -= 2.5;

        if(user.getUniversity() < 0)
            probability -= 2.5;

        if(user.getGames().isEmpty())
            probability -= 2.5;

        if(user.getSex() == 0)
            probability -= 5.0;

        if(user.getHome_town().isEmpty())
            probability -= 5.0;

        if(user.getMovies().isEmpty())
            probability -= 2.5;

        if(user.getBdate().isEmpty())
            probability -= 5.0;

        if(user.getHas_photo() == 0)
            probability -= 10.0;

        if(!user.cityIsNull()){
            var city = user.getCity();

        } else {
            probability -= 5.0;
        }

        if(!user.personalIsNull()){

        } else {
            probability -= 2.5;
        }

        if(!user.relativeIsNull()){
            var relatives = user.getRelatives();

            for(var relative: relatives){

            }
        } else {
            probability -= 2.5;
        }

        if(!user.occupationIsNull()){
            var occupation = user.getOccupation();

            if (occupation.getName().toLowerCase().contains("bot") ||
                    occupation.getName().toLowerCase().contains("бот")){
                probability -= 45.0;
            }
        } else {
            probability -= 5;
        }

        if(!user.careerIsNull()){
            var careers = user.getCareer();

            for(var career: careers ){
                if(career.getPosition().toLowerCase().contains("bot") ||
                        career.getPosition().toLowerCase().contains("бот"))
                    probability -= 45.0;
            }

        } else {
            probability -= 5.0;
        }

        if(!user.getActivities().isEmpty()) {
            if (user.getActivities().toLowerCase().contains("bot") ||
                    user.getActivities().toLowerCase().contains("бот"))
                probability -= 45.0;
        } else {
            probability -= 2.5;
        }

        if(!user.getNickname().isEmpty()) {
            if (user.getNickname().toLowerCase().contains("bot") ||
                    user.getNickname().toLowerCase().contains("бот"))
                probability -= 45.0;
        } else {
            probability -= 5.0;
        }

        if(!user.getSite().isEmpty()) {
            if (user.getSite().toLowerCase().contains("bot") ||
                    user.getSite().toLowerCase().contains("бот"))
                probability -= 45.0;
        } else {
            probability -= 2.5;
        }

        if(!user.getScreen_name().isEmpty()) {
            if (user.getScreen_name().toLowerCase().contains("bot") ||
                    user.getScreen_name().toLowerCase().contains("бот"))
                probability -= 45.0;
        } else {
            probability -= 5.0;
        }

        if(!user.getFirst_name().isEmpty() || !user.getLast_name().isEmpty()) {
            if (user.getFirst_name().toLowerCase().contains("bot") ||
                    user.getLast_name().toLowerCase().contains("bot") ||
                    user.getFirst_name().toLowerCase().contains("бот") ||
                    user.getLast_name().toLowerCase().contains("бот"))
                probability -= 45.0;
        } else {
            probability -= 10.0;
        }

        if(!user.getAbout().isEmpty()) {
            if (user.getAbout().toLowerCase().contains("bot") || user.getAbout().toLowerCase().contains("бот"))
                probability -= 45.0;
        } else {
            probability -= 5.0;
        }

        return probability;
    }
    public int[] getArrayOfIds(int firstId, int lastId){
        int sizeOfIdsArray = lastId - firstId + 1;
        int[] ids = new int[sizeOfIdsArray];
        int valueOfIdsArray = firstId;

        for(int i = 0; i < sizeOfIdsArray; i++){
            ids[i] = valueOfIdsArray++;
        }

        return ids;
    }
}
