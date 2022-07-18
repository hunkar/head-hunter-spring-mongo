package org.example.repositories;

import org.example.models.Company;
import org.example.models.DetailedCompany;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {
    @Query("{name:'?0'}")
    Company findItemByName(String name);

    @Aggregation(pipeline = {"{$match: { id: '?0' }}",
            "{$lookup: { from: \"jobposts\", as: \"jobPostList\", localField: \"_id\", foreignField: \"companyId\" }}"
    })
    List<DetailedCompany> findByIdWithJobPosts(String id);
}