package org.example.repositories;

import org.example.models.JobSeeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerRepository extends MongoRepository<JobSeeker, String> {
    @Query("{ 'skills' : {$in : [?0] }}")
    List<JobSeeker> findBySkills(List<String> skills);
}
