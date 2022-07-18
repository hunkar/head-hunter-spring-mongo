package org.example.services;

import org.example.models.JobPost;
import org.example.models.JobSeeker;
import org.example.repositories.CompanyRepository;
import org.example.repositories.JobPostRepository;
import org.example.repositories.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class JobEventManager {
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPostRepository jobPostRepository;

    public void checkAvailableJobSeekersForJobPost(JobPost jobPost) {
        List<String> sentJobSeekers = new ArrayList<>();

        List<JobSeeker> jobSeekersBySkill = jobSeekerRepository.findBySkills(jobPost.getSkills());
        jobSeekersBySkill.forEach(jobSeeker -> {
            if (!sentJobSeekers.contains(jobSeeker.getId())) {
                companyRepository.findById(jobPost.getCompanyId()).ifPresent(company -> {
                    jobSeeker.notifyNewJob(company.getName(), jobPost, jobSeeker1 ->
                            company.notifyCandidateResponse(jobSeeker1.getName(), jobPost));

                    sentJobSeekers.add(jobSeeker.getId());
                });
            }
        });
    }

    public void checkAvailableJobPostsForJobSeeker(JobSeeker jobSeeker) {
        List<JobPost> jobPostsBySkill = jobPostRepository.findBySkills(jobSeeker.getSkills());

        List<String> sentJobPosts = new ArrayList<>();

        jobPostsBySkill.forEach(jobPost -> {
            if (!sentJobPosts.contains(jobPost.getId())) {
                companyRepository.findById(jobPost.getCompanyId()).ifPresent(company -> {
                    jobSeeker.notifyNewJob(company.getName(), jobPost, jobSeeker1 ->
                            System.out.println(jobSeeker1.getName() + "(job seeker) accepted to interview for job " + jobPost.getTitle()));

                    sentJobPosts.add(jobPost.getId());
                });
            }
        });
    }
}
