package org.example.controllers;

import org.example.models.JobSeeker;
import org.example.repositories.JobSeekerRepository;
import org.example.services.JobEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {
    @Autowired
    JobEventManager jobEventManager;

    @Autowired
    JobSeekerRepository jobSeekerRepo;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JobSeeker get(@RequestParam("id") String id) {
        return jobSeekerRepo.findById(id).orElse(null);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<JobSeeker> list() {
        return jobSeekerRepo.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JobSeeker create(@RequestBody JobSeeker item) {
        JobSeeker jobSeeker = jobSeekerRepo.save(item);

        jobEventManager.checkAvailableJobPostsForJobSeeker(jobSeeker);

        return jobSeeker;
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String delete(@RequestParam("id") String id) {
        jobSeekerRepo.deleteById(id);

        return "ok";
    }

    @PostMapping(value = "/listbyskills", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<JobSeeker> listbyskills(@RequestBody List<String> skills) {
        return jobSeekerRepo.findBySkills(skills);
    }
}
