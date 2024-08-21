package com.intuit.marketplace.repository;

import com.intuit.marketplace.model.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<ProjectEntity, Long>  {
    ProjectEntity save(ProjectEntity projectEntity);
    ProjectEntity findById(Long id);

    @Query("select p from ProjectEntity p where p.projectStatus = OPEN AND p.deadline <= :date")
    List<ProjectEntity> findExpiredProjects(Date date);
}
