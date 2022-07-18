package org.example.repositories;

import org.example.models.JobPost;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobPostRepository extends CrudRepository<JobPost, String> {
    List<JobPost> findAll();

    @Query("{ 'skills' : {$in : [?0] }}")
    List<JobPost> findBySkills(List<String> skills);

    @Query(value = "{ 'companyId' : ?0}", delete = true)
    void deleteByCompany(String companyId);

    @Aggregation(pipeline = {"{$project: {\n" +
            "        title: 1,\n" +
            "     }}" +
            "{ $replaceRoot: { newRoot: \"title\" } }"
    })
    List<String> jobPostsTitleList();
}
