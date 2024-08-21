package com.intuit.marketplace.service;

import com.intuit.marketplace.model.ProjectStatus;
import com.intuit.marketplace.model.dto.ProjectRequestDto;
import com.intuit.marketplace.model.dto.ProjectResponseDto;
import com.intuit.marketplace.model.entity.ProjectEntity;
import com.intuit.marketplace.service.mapper.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectMapperTest {

    @Mock
    private ProjectEntity projectEntity;

    @Mock
    private ProjectRequestDto projectRequestDto;

    @InjectMocks
    private ProjectMapper projectMapper;

    @Test
    public void testToEntity_mapsDtoFieldsToEntity() {
        // Mock DTO data
        Date date = new Date();
        when(projectRequestDto.deadline()).thenReturn(date);
        when(projectRequestDto.requirements()).thenReturn("Test Project Requirements");
        when(projectRequestDto.description()).thenReturn("Test Project Description");
        when(projectRequestDto.maximumBudget()).thenReturn((1000L));
        when(projectRequestDto.clientId()).thenReturn(1L);

        // Call the method
        ProjectEntity mappedEntity = projectMapper.toEntity(projectRequestDto);

        // Verify mapping
        assertEquals(date, mappedEntity.getDeadline());
        assertEquals("Test Project Description", mappedEntity.getDescription());
        assertEquals("Test Project Requirements", mappedEntity.getRequirements());
        assertEquals(1000.00, mappedEntity.getMaximumBudget(), 0.001);
        assertEquals(1L, mappedEntity.getClientId());
        assertEquals(ProjectStatus.OPEN, mappedEntity.getProjectStatus());
    }

    @Test
    public void testToDto_mapsEntityFieldsToDto() {
        // Mock entity data
        Long entityId = 2L;
        Date date = new Date();
        when(projectEntity.getId()).thenReturn(entityId);
        when(projectEntity.getClientId()).thenReturn(3L);
        when(projectEntity.getDeadline()).thenReturn(date);
        when(projectEntity.getDescription()).thenReturn("Test Project Description");
        when(projectEntity.getRequirements()).thenReturn("Test Project Requirements");
        when(projectEntity.getMaximumBudget()).thenReturn(5000L);
        when(projectEntity.getProjectStatus()).thenReturn(ProjectStatus.OPEN);

        // Call the method
        ProjectResponseDto mappedDto = projectMapper.toDto(projectEntity);

        // Verify mapping
        assertEquals(entityId, mappedDto.id());
        assertEquals(3L, mappedDto.clientId());
        assertEquals(date, mappedDto.deadline());
        assertEquals("Test Project Description", mappedDto.description());
        assertEquals("Test Project Requirements", mappedDto.requirements());
        assertEquals(5000L, mappedDto.maximumBudget());
        assertEquals(ProjectStatus.OPEN, mappedDto.projectStatus());
    }
}