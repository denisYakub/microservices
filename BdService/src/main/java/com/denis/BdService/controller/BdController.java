package com.denis.BdService.controller;

import com.denis.BdService.dto.FieldsToDeleteBy;
import com.denis.BdService.dto.UserEntity;
import com.denis.BdService.service.PostgresqlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bd")
@AllArgsConstructor
public class BdController {
    @Autowired
    private final PostgresqlService postgresqlService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable int id){
        return this.postgresqlService.getUser(id);
    }

    @GetMapping("/{startId}/{endId}")
    public ResponseEntity<List<UserEntity>> getUsersRange(@PathVariable("startId") int startId, @PathVariable("endId") int endId){
        return ResponseEntity.ok(this.postgresqlService.getUsersFromTo(startId, endId));
    }

    @PostMapping
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
