package com.denis.sortAndAnalyzeService.service;

import com.denis.sortAndAnalyzeService.configuration.Config;
import com.denis.sortAndAnalyzeService.dto.FieldToDeleteBy;
import com.denis.sortAndAnalyzeService.dto.FieldsToDeleteBy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SortService {
    @Value("${global.URL_BD_SERVICE}")
    private String URL_BD_SERVICE;

    public void deleteUsersWithClosedAccount(){
        FieldsToDeleteBy fields = new FieldsToDeleteBy(
                new FieldToDeleteBy<Boolean>("is_closed", true),
                new FieldToDeleteBy<Boolean>("can_access_closed", false)
        );

        Config.restTemplate()
                .exchange(this.URL_BD_SERVICE,
                        HttpMethod.DELETE,
                        new HttpEntity<>(fields),
                        Void.class
                );
    }
}
