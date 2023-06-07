package com.defecttrackersystem.DefectTrackerSystem.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:MessageAndCodes.properties")
@Getter
@Setter
public class ValidationFailureResponseCode {
    // Common Success code
    @Value("${code.success.common}")
    private String commonSuccessCode;

    @Value("${code.failure.common}")
    private String failureCode;

    //validation code for Priority
    @Value("${code.validation.priority.alreadyExists}")
    private String priorityAlreadyExists;
    @Value("${code.validation.priority.notExists}")
    private String priorityNotExistsCode;

    // Messages for Priority
    @Value("${message.validation.priority.alreadyExists}")
    private String validationPriorityAlreadyExists;

    @Value("${message.success.save.priority}")
    private String savePrioritySuccessMessage;

    @Value("${message.success.getAll.priority}")
    private String getAllPrioritySuccessMessage;

    @Value("${message.validation.priority.notExists}")
    private String priorityNotExistsMessage;

    @Value("${message.success.getById.priority}")
    private String getPriorityByIdSuccessMessage;

    @Value("${message.success.deleteById.priority}")
    private String deletePrioritySuccessMessage;

    @Value("${message.success.update.priority}")
    private String updatePrioritySuccessMessage;

    @Value("${message.success.searchAndPagination.priority}")
    private String searchAndPaginationPrioritySuccessMessage;
}
