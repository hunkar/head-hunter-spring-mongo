package org.example.repositories;

import org.example.models.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JobPostRepository extends MongoRepository<JobPost, String> {
    @Query("{ 'skills' : {$in : [?0] }}")
    List<JobPost> findBySkills(List<String> skills);

    @Query(value = "{ 'companyId' : ?0}", delete = true)
    void deleteByCompany(String companyId);
}
