package com.denis.sortAndAnalyzeService.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Analyze {
    public int count_of_deleted_possible_bot_accounts = 0;

    private double sum_of_completeness_accounts = 0.0;
    private int count_of_accounts_for_completeness = 0;

    public enum platforms {
        android(2), iphone(4), descTop(7);

        public int id;

        platforms(int id){
            this.id = id;
        }
    };

    public int count_of_processed_accounts = 0;
    public int count_of_processed_platforms = 0;
    public int count_of_processed_followers = 0;
    public int count_of_processed_migration = 0;

    public int count_of_migrated_users = 0;

    private final Map<Integer, Integer[]> age_to_followers_sum_platform_sum = new HashMap<>();

    public Analyze(){

        age_to_followers_sum_platform_sum.put(10, new Integer[]{0, 0, 0, 0});
        age_to_followers_sum_platform_sum.put(20, new Integer[]{0, 0, 0, 0});
        age_to_followers_sum_platform_sum.put(30, new Integer[]{0, 0, 0, 0});
        age_to_followers_sum_platform_sum.put(40, new Integer[]{0, 0, 0, 0});
        age_to_followers_sum_platform_sum.put(60, new Integer[]{0, 0, 0, 0});
        age_to_followers_sum_platform_sum.put(100,new Integer[]{0, 0, 0, 0});
    }

    public void addDeletedOrBot(int id){
        this.count_of_deleted_possible_bot_accounts++;
    }

    public void increaseCompleteness(double completeness){
        this.sum_of_completeness_accounts += completeness;
        this.count_of_accounts_for_completeness++;
    }

    public void increasePlatform(int platform, int age){
        int _age = (age <= 20 ? 20 : age <= 30 ? 30 : age <= 40 ? 40 : age <= 60 ? 60 : 100);
        var val = this.age_to_followers_sum_platform_sum.get(_age);

        if(platform == platforms.android.id){
            val[1]++;
        } else if (platform == platforms.iphone.id) {
            val[2]++;
        } else if (platform == platforms.descTop.id) {
            val[3]++;
        } else {
            return;
        }
        this.count_of_processed_platforms++;
        this.age_to_followers_sum_platform_sum.put(
                _age,
                val
        );
    }

    public void increaseFollowers(int age, int followers){
        if(age <= 10){
            var val = this.age_to_followers_sum_platform_sum.get(10);
            val[0]++;
            this.age_to_followers_sum_platform_sum.put(
                    10,
                    val
            );
        } else if(age <= 20){
            var val = this.age_to_followers_sum_platform_sum.get(20);
            val[0]++;
            this.age_to_followers_sum_platform_sum.put(
                    20,
                    val
            );
        } else if(age <= 30){
            var val = this.age_to_followers_sum_platform_sum.get(30);
            val[0]++;
            this.age_to_followers_sum_platform_sum.put(
                    30,
                    val
            );
        } else if(age <= 40){
            var val = this.age_to_followers_sum_platform_sum.get(40);
            val[0]++;
            this.age_to_followers_sum_platform_sum.put(
                    40,
                    val
            );
        } else if(age <= 60){
            var val = this.age_to_followers_sum_platform_sum.get(60);
            val[0]++;
            this.age_to_followers_sum_platform_sum.put(
                    60,
                    val
            );
        } else if(age <= 100){
            var val = this.age_to_followers_sum_platform_sum.get(100);
            val[0]++;
            this.age_to_followers_sum_platform_sum.put(
                    100,
                    val
            );
        } else {
            return;
        }
        this.count_of_processed_followers++;
    }

    public double averageCompleteness(){
        if (this.count_of_accounts_for_completeness == 0)
            return 0;
        return ( this.sum_of_completeness_accounts / this.count_of_accounts_for_completeness );
    }

    public int[] avg20yo(){
        return Arrays.stream(this.age_to_followers_sum_platform_sum.get(20)).mapToInt(i->i).toArray();
    }
    public int[] avg30yo(){
        return Arrays.stream(this.age_to_followers_sum_platform_sum.get(30)).mapToInt(i->i).toArray();
    }
    public int[] avg40yo(){
        return Arrays.stream(this.age_to_followers_sum_platform_sum.get(40)).mapToInt(i->i).toArray();
    }
    public int[] avg60yo(){
        return Arrays.stream(this.age_to_followers_sum_platform_sum.get(60)).mapToInt(i->i).toArray();
    }
    public int[] avg100yo(){
        return Arrays.stream(this.age_to_followers_sum_platform_sum.get(100)).mapToInt(i->i).toArray();
    }
    public void increaseIfMigrated(String home_town, String current_city){
        this.count_of_processed_migration++;
        if(home_town.equalsIgnoreCase(current_city))
            this.count_of_migrated_users++;
    }
}
