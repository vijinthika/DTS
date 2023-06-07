package com.defecttrackersystem.DefectTrackerSystem.controler;

import com.defecttrackersystem.DefectTrackerSystem.common.response.BaseResponse;
import com.defecttrackersystem.DefectTrackerSystem.common.response.ContentResponse;
import com.defecttrackersystem.DefectTrackerSystem.common.response.PaginatedContentResponse;
import com.defecttrackersystem.DefectTrackerSystem.rest.enums.RequestStatus;
import com.defecttrackersystem.DefectTrackerSystem.utils.Constants;
import com.defecttrackersystem.DefectTrackerSystem.utils.EndpointURI;
import com.defecttrackersystem.DefectTrackerSystem.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class severityController {
    @Autowired
    private SeverityService severityService;
    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(value= EndpointURI.SEVERITY)
    public ResponseEntity<Object> saveSeverity(@RequestBody SeverityRequest severityRequest)
    {
        if(severityService.isSeverityExistsByName(severityRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        if(severityService.isSeverityExistsByColor(severityRequest.getColor()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        severityService.saveSeverity(severityRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveSeveritySuccessMessage()));
    }

    @PutMapping(value = EndpointURI.SEVERITY)
    public ResponseEntity<Object> updateSeverity(@RequestBody SeverityRequest severityRequest) {
        if (!SeverityService.existsBySeverityId(SeverityRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityNotExistsCode(),
                    validationFailureResponseCode.getSeverityNotExistsMessage()));
        }
        if(severityService.isUpdateSeverityColorExists(severityRequest.getColor(),severityRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        if(severityService.isUpdateSeverityNameExists(severityRequest.getName(),severityRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        severityService.saveSeverity(severityRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveSeveritySuccessMessage()));
    }

    @GetMapping(value=EndpointURI.SEVERITY)
    public ResponseEntity<Object> getAllSeverity()
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.PRIORITIES,severityService.getAllSeverity(),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllSeveritySuccessMessage()));
    }

    @GetMapping(value = EndpointURI.SEVERITY_BY_ID)
    public ResponseEntity<Object> getSeverityById(@PathVariable Long id)
    {
        if(!severityService.existsBySeverityId(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityNotExistsCode(),
                    validationFailureResponseCode.getSeverityNotExistsMessage()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.SEVERITY,severityService.getSeverityById(id),
                RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetSeverityByIdSuccessMessage()));
    }

    @DeleteMapping(value = EndpointURI.SEVERITY_BY_ID)
    public ResponseEntity<Object> deleteSeverityById(@PathVariable Long id)
    {
        if(!severityService.existsBySeverityId(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeverityNotExistsCode(),
                    validationFailureResponseCode.getSeverityNotExistsMessage()));
        }
        severityService.deleteSeverity ById(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteSeveritySuccessMessage()));
    }
    @GetMapping(value = EndpointURI.SEVERITY_SEARCH_AND_PAGINATION)
    public ResponseEntity<Object> multiSearchSeverity(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "sortField", defaultValue = "id") String sortField,
            SeveritySearch severitySearch
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortField);
        PaginatedContentResponse.Pagination pagination = new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(
                Constants.PRIORITIES,
                severityService.multiSearchSeverity(pageable, pagination, severitySearch),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSearchAndPaginationSeveritySuccessMessage(),
                pagination)
        );
    }


}

}