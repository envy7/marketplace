package com.intuit.marketplace.model.dto;

import java.util.Date;

public record BidResponseDto(Long id, Long contractorId, Long bidAmount, Date date, Long projectId, Boolean winner) {
}
