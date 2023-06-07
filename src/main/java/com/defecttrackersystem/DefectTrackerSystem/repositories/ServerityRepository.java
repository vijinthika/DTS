package com.defecttrackersystem.DefectTrackerSystem.repositories;

import com.defecttrackersystem.DefectTrackerSystem.entities.Severity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerityRepository extends JpaRepository<Severity,Long> {
}
