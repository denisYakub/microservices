package com.denis.BdService.controller;

import com.denis.BdService.dto.UserEntity;
import com.denis.BdService.dto.UsersRequest;
import com.denis.BdService.dto.UsersResponse;
import com.denis.BdService.service.PostgresqlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bd")
@AllArgsConstructor
public class BdController {
    @Autowired
    private final PostgresqlService postgresqlService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void saveUsers(@RequestBody UsersRequest usersRequest){
        this.postgresqlService.saveUsersRequest(usersRequest);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void deleteAllUsers(){
        this.postgresqlService.deleteAllRecordsFromBd();
    }
}
