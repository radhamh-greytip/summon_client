package com.greytip.summon_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobInfoDTO {
    private String jobId;
    private String title;
    private Long currentStep;
    private Long totalSteps;
    private Long createdOn;
    private Long lastModifiedOn;
    private String status;
    private List<JobEventDTO> events;
    private int percent;
    private Long totalDuration;

}
