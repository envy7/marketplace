package com.intuit.marketplace.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "bids")
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contractorId;
    private Long bidAmount;
    private Date date;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;
    private Boolean winner;
}
