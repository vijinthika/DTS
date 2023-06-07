package com.defecttrackersystem.DefectTrackerSystem.service.impl;

import com.defecttrackersystem.DefectTrackerSystem.common.response.PaginatedContentResponse;
import com.defecttrackersystem.DefectTrackerSystem.entities.Priority;
import com.defecttrackersystem.DefectTrackerSystem.entities.QPriority;
import com.defecttrackersystem.DefectTrackerSystem.repositories.PriorityRepository;
import com.defecttrackersystem.DefectTrackerSystem.request.dto.PriorityRequest;
import com.defecttrackersystem.DefectTrackerSystem.response.dto.PriorityResponse;
import com.defecttrackersystem.DefectTrackerSystem.search.dto.PrioritySearch;
import com.defecttrackersystem.DefectTrackerSystem.service.PriorityService;
import com.defecttrackersystem.DefectTrackerSystem.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    @Autowired
    private PriorityRepository priorityRepository;
    @Override
    public void savePriority(PriorityRequest priorityRequest) {
        Priority priority=new Priority();
        BeanUtils.copyProperties(priorityRequest,priority);
        priorityRepository.save(priority);
    }

    @Override
    public boolean isPriorityExistsByName(String name) {
        return priorityRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean isPriorityExistsByColor(String color) {
        return priorityRepository.existsByColorIgnoreCase(color);
    }

    @Override
    public List<PriorityResponse> getAllPriority() {
        List<PriorityResponse> priorityResponseList=new ArrayList<>();
        List<Priority> priorityList=priorityRepository.findAll();
        for (Priority priority:priorityList
             ) {
            PriorityResponse priorityResponse=new PriorityResponse();
            BeanUtils.copyProperties(priority,priorityResponse);
            priorityResponseList.add(priorityResponse);
        }
        return priorityResponseList;
    }

    @Override
    public PriorityResponse getPriorityById(Long id) {
        PriorityResponse priorityResponse=new PriorityResponse();
        Priority priority=priorityRepository.findById(id).get();
        BeanUtils.copyProperties(priority,priorityResponse);
        return priorityResponse;

    }

    @Override
    public boolean existsByPriorityId(Long id) {
        return priorityRepository.existsById(id);
    }

    @Override
    public void deletePriorityById(Long id) {
        priorityRepository.deleteById(id);
    }

    @Override
    public boolean isUpdatePriorityNameExists(String name,Long id) {
        return priorityRepository.existsByNameIgnoreCaseAndIdNot(name,id);
    }

    @Override
    public boolean isUpdatePriorityColorExists(String color,Long id) {
        return priorityRepository.existsByColorIgnoreCaseAndIdNot(color,id);
    }

    @Override
    public List<PriorityResponse> multiSearchPriority(Pageable pageable, PaginatedContentResponse.Pagination pagination, PrioritySearch prioritySearch) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (Utils.isNotNullAndEmpty(prioritySearch.getPriorityName())) {
            booleanBuilder.and(QPriority.priority.name.eq(prioritySearch.getPriorityName()));
        }
        if (Utils.isNotNullAndEmpty(prioritySearch.getPriorityColor())) {
            booleanBuilder.and(QPriority.priority.color.eq(prioritySearch.getPriorityColor()));
        }
        List<PriorityResponse> priorityResponseList = new ArrayList<>();
        Page<Priority> priorityList = priorityRepository.findAll(booleanBuilder, pageable);
        pagination.setTotalRecords(priorityList.getTotalElements());
        pagination.setPageSize(priorityList.getTotalPages());
        for (Priority priority : priorityList) {
            PriorityResponse priorityResponse = new PriorityResponse();
            BeanUtils.copyProperties(priority, priorityResponse);
            priorityResponseList.add(priorityResponse);
        }
        return priorityResponseList;
    }



}
