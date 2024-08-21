package com.intuit.marketplace.model.dto;

import java.util.Date;

public record ProjectRequestDto(Long clientId, Date deadline, Long maximumBudget,
                                String description, String requirements) {
}