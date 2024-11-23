package com.denis.sortAndAnalyzeService.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FieldsToDeleteTests {
    @Test
    public void test_FieldToDeleteBy_dto(){
        var fields = new FieldsToDeleteBy(
                new FieldToDeleteBy("fio", "bod"),
                new FieldToDeleteBy("age", 12)
        );
    }
}
