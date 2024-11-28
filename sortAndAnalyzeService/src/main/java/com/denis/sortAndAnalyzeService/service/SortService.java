package com.denis.sortAndAnalyzeService.service;

import com.denis.sortAndAnalyzeService.configuration.Config;
import com.denis.sortAndAnalyzeService.dto.FieldToDeleteBy;
import com.denis.sortAndAnalyzeService.dto.FieldsToDeleteBy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SortService {
    @Value("${application.URL_BD_SERVICE}")
    public String URL_BD_SERVICE;

    public void deleteUsersWithClosedAccount(){
        FieldsToDeleteBy fields = new FieldsToDeleteBy(
                new FieldToDeleteBy<Boolean>("is_closed", true),
                new FieldToDeleteBy<Boolean>("can_access_closed", false)
        );

        this.exchangeRequest(
                this.URL_BD_SERVICE,
                HttpMethod.DELETE,
                new HttpEntity<>(fields),
                Void.class
        );
    }

    public <T>ResponseEntity exchangeRequest(String url, HttpMethod method, Object body, Class<T> responseType){
        return Config.restTemplate().exchange(
                url,
                method,
                new HttpEntity<>(body),
                responseType
        );
    }
}
