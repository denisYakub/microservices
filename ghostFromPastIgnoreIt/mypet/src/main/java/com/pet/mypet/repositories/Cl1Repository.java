package com.pet.mypet.repositories;

import com.pet.mypet.models.Cl1;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Cl1Repository extends MongoRepository<Cl1, String> {
}
