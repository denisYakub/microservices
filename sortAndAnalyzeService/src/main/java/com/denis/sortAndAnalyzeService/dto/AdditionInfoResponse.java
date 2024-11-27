package com.denis.sortAndAnalyzeService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class AdditionInfoResponse {

    private List<AdditionalInfo> response;

    @Builder
    @Getter
    @Setter
    public static class AdditionalInfo {
        @Builder
        @Getter
        @Setter
        public static class LastSeen{
            private int platform;
            private long time;

            @Override
            public String toString(){
                return "{" + "\"platform\":" + platform +
                        ",\"time\":" + time + "}";
            }
        }
        @Builder
        @Getter
        @Setter
        public static class Counters{
            private int albums;
            private int videos;
            private int audios;
            private int photos;
            private int notes;
            private int friends;
            private int gifts;
            private int groups;
            private int online_friends;
            private int mutual_friends;
            private int user_videos;
            private int user_photos;
            private int followers;
            private int pages;
            private int subscriptions;
        }
        private int id;
        private int followers_count;
        private LastSeen last_seen;
        private Counters counters;

        @Override
        public String toString(){
            return "\"id\":" + id +
                    ",\"followers_count\":" + followers_count +
                    ",\"last_seen\":" + last_seen;
        }
    }

    @Override
    public String toString(){
        return "{" + "\"response\":" + response + "}";
    }

}