package org.example.controllers;

import org.example.models.Company;
import org.example.repositories.CompanyRepository;
import org.example.repositories.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPostRepository jobPostRepository;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Company get(@RequestParam("id") String id) {
        return companyRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/getdetailed", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Company getDetailed(@RequestParam("id") String id) {
        return companyRepository.findByIdWithJobPosts(id).get(0);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Company> list() {
        return companyRepository.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Company create(@RequestBody Company item) {
        return companyRepository.save(item);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String delete(@RequestParam("id") String id) {
        companyRepository.deleteById(id);
        jobPostRepository.deleteByCompany(id);

        return "ok";
    }
}
