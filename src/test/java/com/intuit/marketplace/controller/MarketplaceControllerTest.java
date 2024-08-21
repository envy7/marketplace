package com.intuit.marketplace.controller;

import com.intuit.marketplace.model.ProjectStatus;
import com.intuit.marketplace.model.dto.ProjectRequestDto;
import com.intuit.marketplace.model.dto.ProjectResponseDto;
import com.intuit.marketplace.repository.ProjectRepository;
import com.intuit.marketplace.service.MarketplaceService;
import com.intuit.marketplace.service.mapper.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MarketplaceControllerTest {

    @Autowired
    private MarketplaceController marketplaceController;
    @MockBean
    private MarketplaceService marketplaceService;
    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private ProjectMapper projectMapper;

    @Test
    public void testCreateProject() {
        Date now = new Date();
        ProjectRequestDto requestDto = new ProjectRequestDto(1L, now, 300L, "testing", "testing");
        ProjectResponseDto expectedResponse = new ProjectResponseDto(1L, 1L, now, 300L, "testing", "testing", ProjectStatus.OPEN);

        Mockito.when(marketplaceService.createProject(requestDto)).thenReturn(Optional.of(expectedResponse));
        ResponseEntity<Object> response = marketplaceController.createProject(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        URI location = response.getHeaders().getLocation();
        assertNotNull(location);
        assertEquals("/projects/1", location.getPath());
    }
}
