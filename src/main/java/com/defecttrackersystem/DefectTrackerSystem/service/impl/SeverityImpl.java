package com.defecttrackersystem.DefectTrackerSystem.service.impl;

import com.defecttrackersystem.DefectTrackerSystem.entities.Severity;
import com.defecttrackersystem.DefectTrackerSystem.repositories.SeverityRepository;
import com.defecttrackersystem.DefectTrackerSystem.request.dto.SeverityRequest;
import com.defecttrackersystem.DefectTrackerSystem.response.dto.SeverityResponse;
import com.defecttrackersystem.DefectTrackerSystem.service.SeverityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SeverityImpl implements SeverityService {
    @Autowired
    private SeverityRepository seviarityRepository;

    @Override
    public void saveSeviarity(SeverityRequest seviarityRequest) {
        Severity seviarity= new Severity();
        BeanUtils.copyProperties(seviarityRequest,seviarity);
        seviarityRepository.save(seviarity);
    }

    @Override
    public List<SeverityResponse> getAllSeviarity() {
        List<SeverityResponse> seviarityResponseList=new ArrayList<>();
        List<Severity> seviarityList=seviarityRepository.findAll();
        for(Severity seviarity:seviarityList){
            SeverityResponse seviarityResponse=new SeverityResponse();
            BeanUtils.copyProperties(seviarity,seviarityResponse);
            seviarityResponseList.add(seviarityResponse);
        }
        return seviarityResponseList;
    }

    @Override
    public SeverityResponse getSeviarityByid(Long id) {
        SeverityResponse seviarityResponse=new SeverityResponse();
        Severity seviarity=seviarityRepository.findById(id).get();
        BeanUtils.copyProperties(seviarity,seviarityResponse);
        return seviarityResponse;
    }

    @Override
    public void DeleteSeviarityByid(Long id) {
        seviarityRepository.deleteById(id);
    }

    @Override
    public boolean existsBySeviarityid(long id) {
        return seviarityRepository.existsById(id);
    }

    @Override
    public boolean isSeviarityExistsByName(String name) {
        return seviarityRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean isSeviarityExistsByColor(String color) {
        return seviarityRepository.existsByColorIgnoreCase(color);
    }

    @Override
    public boolean isUpdateSeviarityNameExists(String name, Long id) {
        return seviarityRepository.existsByNameIgnoreCaseAndIdNot(name,id);
    }

    @Override
    public boolean isUpdateSeviarityColorExists(String color, Long id) {
        return seviarityRepository.existsByColorIgnoreCaseAndIdNot(color,id);
    }
}
