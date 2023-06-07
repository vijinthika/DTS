package com.defecttrackersystem.DefectTrackerSystem.service.impl;

import com.defecttrackersystem.DefectTrackerSystem.entities.Severity;
import com.defecttrackersystem.DefectTrackerSystem.request.dto.SeverityRequest;
import com.defecttrackersystem.DefectTrackerSystem.service.SeveriryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeverityServiceImpl implements SeveriryService {
    @Override
    public void saveSeverity(SeverityRequest severityRequest) {
        Severity severity=new Severity();
        BeanUtils.copyProperties(severityRequest,severity);
        SeverityRepository.save(severity);

    }

    @Override
    public List<SeverityResponse> getAllSeverity() {
        List<SeverityResponse>SeverityResponseList=new ArrayList<>();
        List<Severity>SeverityList=SeverityRepository.findall();
        for(Severity severity : severityList){
            SeverityResponse severityResponse = new SeverityResponse();
            serverityResponseList.add(severityResponse);

        }
        return SeverityResponseList;
    }

    @Override
    public SeverityResponse getSeverityById(Long id) {
        SeverityResponse severityResponse=new SeverityResponse();
        Severity severity=SeverityRepository.findById(id).get();
        BeanUtils.copyProperties(severity,severityResponse);

        return severityResponse;
    }

    @Override
    public void deleteSeverity(Long id) {
        SeverityRepository.deleteById(id);
    }
}
}
