package com.pet.mypet.services;

import com.pet.mypet.dto.Cl1Request;
import com.pet.mypet.dto.Cl1Response;
import com.pet.mypet.models.Cl1;
import com.pet.mypet.repositories.Cl1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Cl1Service {

    private final Cl1Repository cl1Repository;

    public void createCl1(Cl1Request cl1Request){
        Cl1 cl1 = Cl1.builder()
                .name(cl1Request.getName())
                .build();

        cl1Repository.save(cl1);
        log.info("Cl1 with id-{} is created and saved", cl1.getId());
    }

    public List<Cl1Response> getAllCl1() {
        List<Cl1> cl1s = cl1Repository.findAll();
        return cl1s.stream().map(this::mapToCl1Response).toList();
    }

    private Cl1Response mapToCl1Response(Cl1 cl1) {
        return Cl1Response.builder()
                .id(cl1.getId())
                .name(cl1.getName())
                .build();
    }
}
