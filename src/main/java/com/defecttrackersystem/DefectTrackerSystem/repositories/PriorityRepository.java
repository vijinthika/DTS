package com.defecttrackersystem.DefectTrackerSystem.repositories;

import com.defecttrackersystem.DefectTrackerSystem.entities.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PriorityRepository extends JpaRepository<Priority,Long> , QuerydslPredicateExecutor<Priority> {

    public boolean existsByNameIgnoreCase(String name);
    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);
    boolean existsByColorIgnoreCase(String color);
    boolean existsByColorIgnoreCaseAndIdNot(String color,Long id);

}

