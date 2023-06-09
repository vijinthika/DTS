package com.defecttrackersystem.DefectTrackerSystem.controler;

import com.defecttrackersystem.DefectTrackerSystem.request.dto.SeverityRequest;
import com.defecttrackersystem.DefectTrackerSystem.response.dto.SeverityResponse;
import com.defecttrackersystem.DefectTrackerSystem.service.SeverityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ServerityController {
    @Autowired
    private SeverityService seviarityService;

    @PostMapping("/createSeviarity")
    public ResponseEntity<String> saveSeviarity(@RequestBody SeverityRequest seviarityRequest){
        if(seviarityService.isSeviarityExistsByName(seviarityRequest.getName())) {
            return new ResponseEntity<String>("This name already exists", HttpStatus.BAD_REQUEST);
        }
        if(seviarityService.isSeviarityExistsByColor(seviarityRequest.getColor())){
            return new ResponseEntity<String>("This color already exists",HttpStatus.BAD_REQUEST);
        }
        seviarityService.saveSeviarity(seviarityRequest);
        return new ResponseEntity<String>("saved Successfully",HttpStatus.OK);
    }

    @GetMapping("/getAllSeviarity")
    public ResponseEntity<List<SeverityResponse>> getAllSeviarity() {
        List<SeverityResponse> seviarityResponseList=seviarityService.getAllSeviarity();
        return new ResponseEntity<List<SeverityResponse>>(seviarityResponseList,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeviarityByid(@PathVariable Long id){
        if(!seviarityService.existsBySeviarityid(id)) {
            return ResponseEntity.ok("this id is null");
        }

        SeverityResponse seviarityResponse=seviarityService.getSeviarityByid(id);
        return ResponseEntity.ok(seviarityResponse);
    }
    @PutMapping("/updateSeviarity")
    public ResponseEntity<String> updateSeviarity(@RequestBody SeverityRequest seviarityRequest){
        if(!seviarityService.existsBySeviarityid(seviarityRequest.getId())){
            return new ResponseEntity<String>("This id is not valid",HttpStatus.BAD_REQUEST);
        }
        if(seviarityService.isUpdateSeviarityNameExists(seviarityRequest.getName(), seviarityRequest.getId())){
            return new ResponseEntity<String>("This name already exists",HttpStatus.OK);
        }
        if(seviarityService.isUpdateSeviarityColorExists(seviarityRequest.getColor(), seviarityRequest.getId())){
            return new ResponseEntity<String>("This color already exists",HttpStatus.OK);
        }
        seviarityService.saveSeviarity(seviarityRequest);
        return new ResponseEntity<String>("updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteSeviarityByid(@PathVariable Long id){
        if(!seviarityService.existsBySeviarityid(id)) {
            return new ResponseEntity<String>("This id is not valid",HttpStatus.OK);
        }

        seviarityService.DeleteSeviarityByid(id);
        return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);

        // system.out.println("2");
    }





}
