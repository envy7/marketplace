package com.intuit.marketplace.service.mapper;

import com.intuit.marketplace.model.dto.BidRequestDto;
import com.intuit.marketplace.model.dto.BidResponseDto;
import com.intuit.marketplace.model.entity.BidEntity;
import org.springframework.stereotype.Component;

@Component
public class BidMapper implements EntityMapper<BidRequestDto, BidResponseDto, BidEntity> {
    @Override
    public BidEntity toEntity(BidRequestDto dto) {
        BidEntity bidEntity = new BidEntity();
        bidEntity.setDate(dto.date());
        bidEntity.setContractorId(dto.contractorId());
        bidEntity.setBidAmount(dto.bidAmount());
        bidEntity.setWinner(Boolean.FALSE);
        return bidEntity;
    }

    @Override
    public BidResponseDto toDto(BidEntity entity) {
        return new BidResponseDto(entity.getId(), entity.getContractorId(), entity.getBidAmount(),
                entity.getDate(), entity.getProjectEntity().getId(), entity.getWinner());
    }
}
