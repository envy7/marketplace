package com.intuit.marketplace.service.mapper;

import com.intuit.marketplace.model.ProjectStatus;
import com.intuit.marketplace.model.dto.ProjectRequestDto;
import com.intuit.marketplace.model.dto.ProjectResponseDto;
import com.intuit.marketplace.model.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements EntityMapper<ProjectRequestDto, ProjectResponseDto, ProjectEntity> {
    @Override
    public ProjectEntity toEntity(ProjectRequestDto dto) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setDeadline(dto.deadline());
        projectEntity.setMaximumBudget(dto.maximumBudget());
        projectEntity.setRequirements(dto.requirements());
        projectEntity.setDescription(dto.description());
        projectEntity.setClientId(dto.clientId());
        projectEntity.setProjectStatus(ProjectStatus.OPEN);

        return projectEntity;
    }

    @Override
    public ProjectResponseDto toDto(ProjectEntity entity) {
        return new ProjectResponseDto(entity.getId(), entity.getClientId(), entity.getDeadline(),
                entity.getMaximumBudget(), entity.getDescription(), entity.getRequirements(), entity.getProjectStatus());
    }
}
