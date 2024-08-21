package com.intuit.marketplace.controller;

import com.intuit.marketplace.model.dto.BidRequestDto;
import com.intuit.marketplace.model.dto.BidResponseDto;
import com.intuit.marketplace.model.dto.ProjectRequestDto;
import com.intuit.marketplace.model.dto.ProjectResponseDto;
import com.intuit.marketplace.service.MarketplaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/marketplace")
public class MarketplaceController {

    @Autowired
    private MarketplaceService marketplaceService;

    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@RequestBody ProjectRequestDto projectRequestDto) {
        log.debug("Create project api called for project: {}", projectRequestDto.toString());
        Optional<ProjectResponseDto> projectData = marketplaceService.createProject(projectRequestDto);
        return projectData.map(project -> ResponseEntity.created(URI.create("/projects/" + project.id()))
                        .body((Object) project))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to create project "));
    }

    @GetMapping("/projects")
    public ResponseEntity<Page<ProjectResponseDto>> getAllProjects(Pageable pageable) {
        log.debug("Getting list of all projects");
        return ResponseEntity.ok(marketplaceService.getAllProjects(pageable));
    }

    @PostMapping("/bids")
    public ResponseEntity<Object> createBid(@RequestBody BidRequestDto bidRequestDto) {
        log.debug("Create bid api called for bid: {}", bidRequestDto.toString());
        Optional<BidResponseDto> projectData = marketplaceService.createBid(bidRequestDto);
        return projectData.map(bid -> ResponseEntity.created(URI.create("/bids/" + bid.id()))
                        .body((Object) bid))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body("Project id doesn't exist"));
    }
}
