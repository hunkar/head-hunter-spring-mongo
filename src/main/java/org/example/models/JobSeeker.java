package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Document("jobseekers")
@Getter
@Setter
public class JobSeeker extends JobEventListener {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String surname;
    private String email;
    private String phone;
    private List<String> skills;

    @Override
    public void notifyNewJob(String companyName, JobPost jobPost, JobSeekerAnswer jobSeekerAnswer) {
        System.out.println(companyName + "(company) wants to interview for " + jobPost.getTitle() + " with " + this.name);

        if (makeResult()) {
            //this.jobEventManager.respondToJobOffer(this.getName(), jobPost);

            jobSeekerAnswer.acceptOffer(this);
        }
    }

    public boolean makeResult() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
