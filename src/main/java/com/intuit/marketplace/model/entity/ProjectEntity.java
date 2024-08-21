package com.intuit.marketplace.model.entity;

import com.intuit.marketplace.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private Date deadline;
    private Long maximumBudget;
    private String description;
    private String requirements;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
}
