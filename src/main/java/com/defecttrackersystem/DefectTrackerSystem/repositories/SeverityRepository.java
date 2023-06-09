package com.defecttrackersystem.DefectTrackerSystem.repositories;


import com.defecttrackersystem.DefectTrackerSystem.entities.Severity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SeverityRepository extends JpaRepository<Severity,Long> {
    boolean existsByColorIgnoreCase(String color);

    public boolean existsByNameIgnoreCase(String name);

    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);

    public boolean existsByColorIgnoreCaseAndIdNot(String color,Long id);
}
