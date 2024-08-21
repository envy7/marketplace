package com.intuit.marketplace.service;

import com.intuit.marketplace.model.dto.BidRequestDto;
import com.intuit.marketplace.model.dto.BidResponseDto;
import com.intuit.marketplace.model.dto.ProjectRequestDto;
import com.intuit.marketplace.model.dto.ProjectResponseDto;
import com.intuit.marketplace.model.entity.BidEntity;
import com.intuit.marketplace.model.entity.ProjectEntity;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.repository.ProjectRepository;
import com.intuit.marketplace.service.mapper.BidMapper;
import com.intuit.marketplace.service.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MarketplaceService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private BidMapper bidMapper;

    public Optional<ProjectResponseDto> createProject(ProjectRequestDto redditUserRequestDto) {
        ProjectEntity projectEntity = projectMapper.toEntity(redditUserRequestDto);
        return Optional.of(projectMapper.toDto(projectRepository.save(projectEntity)));
    }

    public Page<ProjectResponseDto> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(projectEntity -> projectMapper.toDto(projectEntity));
    }

    public Optional<BidResponseDto> createBid(BidRequestDto bidRequestDto) {
        ProjectEntity project = projectRepository.findById(bidRequestDto.projectId());
        if (project == null) {
            return Optional.empty();
        }
        BidEntity bidEntity = bidMapper.toEntity(bidRequestDto);
        bidEntity.setProjectEntity(project);
        return Optional.of(bidMapper.toDto(bidRepository.save(bidEntity)));
    }

    public void closeAllExpiredProjects() {
        List<ProjectEntity> expiredProjects = projectRepository.findExpiredProjects(new Date());
        expiredProjects.forEach(this::closeProject);
        // get all open projects
        // close the projects

    }

    private void closeProject(ProjectEntity projectEntity) {
        BidEntity lowestBidEntity = bidRepository.findLowestBid(projectEntity.getId());
        // some projects have no bids
        if (lowestBidEntity != null) {
            log.info(String.valueOf(lowestBidEntity));
        }

        // assign winner to the bid
        //bid.setWinner()
        // bidrepository.save
        //
    }
}
