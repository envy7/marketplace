package com.intuit.marketplace.model.dto;

import com.intuit.marketplace.model.ProjectStatus;

import java.util.Date;

public record ProjectResponseDto(Long id, Long clientId, Date deadline, Long maximumBudget,
                                 String description, String requirements,
                                 ProjectStatus projectStatus) {
}