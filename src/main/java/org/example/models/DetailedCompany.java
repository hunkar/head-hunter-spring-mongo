package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@Getter
@Setter
public class DetailedCompany extends Company {
    private List<JobPost> jobPostList;
}
