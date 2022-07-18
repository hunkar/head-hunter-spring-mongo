package org.example.models;

import org.example.services.JobEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobEventListener {
    @Autowired
    public JobEventManager jobEventManager;

    public void notifyNewJob(String companyName, JobPost jobPost, JobSeekerAnswer jobSeekerAnswer){}

    protected void notifyCandidateResponse(String jobSeekerName, JobPost jobPost){}
}
