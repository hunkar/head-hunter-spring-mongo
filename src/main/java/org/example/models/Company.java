package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@Document("companies")
@Getter
@Setter
public class Company extends JobEventListener {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String phone;

    @Override
    public void notifyCandidateResponse(String jobSeekerName, JobPost jobPost) {
        System.out.println(jobSeekerName + "(job seeker) accepted to interview for job " + jobPost.getTitle());
    }
}
