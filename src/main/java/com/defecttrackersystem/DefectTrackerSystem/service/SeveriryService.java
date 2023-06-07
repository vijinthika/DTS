package com.defecttrackersystem.DefectTrackerSystem.service;

import com.defecttrackersystem.DefectTrackerSystem.request.dto.SeverityRequest;

import java.util.List;

public interface SeveriryService {
    public void saveSeverity(SeverityRequest severityRequest);
    public List<SeverityResponse> getAllSeverity();
    public  SeverityResponse getSeverityById(Long id);
    void deleteSeverity(Long id);

}
