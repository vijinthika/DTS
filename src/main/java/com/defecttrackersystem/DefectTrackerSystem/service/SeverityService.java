package com.defecttrackersystem.DefectTrackerSystem.service;

import com.defecttrackersystem.DefectTrackerSystem.request.dto.SeverityRequest;
import com.defecttrackersystem.DefectTrackerSystem.response.dto.SeverityResponse;

import java.util.List;

public interface SeverityService {
    public void saveSeviarity(SeverityRequest seviarityRequest);

    public List<SeverityResponse> getAllSeviarity();
    public SeverityResponse getSeviarityByid(Long id);

    public void DeleteSeviarityByid(Long id);



    public boolean existsBySeviarityid(long id);

    public boolean isSeviarityExistsByName(String name);
    public boolean isSeviarityExistsByColor(String color);

    public boolean isUpdateSeviarityNameExists(String name,Long id);

    public boolean isUpdateSeviarityColorExists(String color,Long id);
}
