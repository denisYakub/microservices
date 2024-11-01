package com.denis.BdService.controller;

import com.denis.BdService.dto.FieldsToDeleteBy;
import com.denis.BdService.service.PostgresqlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/bd")
@AllArgsConstructor
public class BdController {
    @Autowired
    private final PostgresqlService postgresqlService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void saveUsers(@RequestBody String usersRequestJson){
        this.postgresqlService.saveUsersRequest(usersRequestJson);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUsers(@RequestBody FieldsToDeleteBy fields){
        this.postgresqlService.deleteUsers(fields);
    }
}
