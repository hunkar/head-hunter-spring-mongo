package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document("jobposts")
@Getter
@Setter
public class JobPost {
    @Id
    private String id = UUID.randomUUID().toString();
    private String description;
    private String title;
    private List<String> skills;
    private String companyId;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JobPost)) return false;

        JobPost other = (JobPost) o;
        return (this.id.equals(other.getId()));
    }
}
