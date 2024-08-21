package com.intuit.marketplace.repository;

import com.intuit.marketplace.model.entity.BidEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends PagingAndSortingRepository<BidEntity, Long> {
    BidEntity save(BidEntity bidEntity);

    @Query("SELECT b from BidEntity b where b.projectEntity.id = :projectId ORDER BY b.bidAmount ASC LIMIT 1")
    BidEntity findLowestBid(Long projectId);
}