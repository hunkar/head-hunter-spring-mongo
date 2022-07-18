package org.example.repositories;

import org.example.models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CompanyRepository extends MongoRepository<Company, String> {
    @Query("{name:'?0'}")
    Company findItemByName(String name);
}