package org.example.controllers;

import org.example.models.JobPost;
import org.example.repositories.JobPostRepository;
import org.example.services.JobEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobpost")
public class JobPostController {
    @Autowired
    JobEventManager jobEventManager;

    @Autowired
    JobPostRepository jobPostRepository;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JobPost get(@RequestParam("id") String id) {
        return jobPostRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<JobPost> list() {
        return jobPostRepository.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JobPost create(@RequestBody JobPost item) {
        JobPost jobPost = jobPostRepository.save(item);

        jobEventManager.checkAvailableJobSeekersForJobPost(jobPost);
        return jobPost;
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String delete(@RequestParam("id") String id) {
        jobPostRepository.deleteById(id);

        return "ok";
    }
}
