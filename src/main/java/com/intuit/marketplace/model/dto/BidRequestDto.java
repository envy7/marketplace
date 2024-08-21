package com.intuit.marketplace.model.dto;

import java.util.Date;

public record BidRequestDto(Long contractorId, Long bidAmount, Date date, Long projectId) {
}
