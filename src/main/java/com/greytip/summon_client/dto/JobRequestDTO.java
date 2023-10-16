package com.greytip.summon_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobRequestDTO {

    private String jobDefId;
    private String businessKey;
    private String tenantId;
    private String title;
    private Long totalSteps;
    private String parentJob;
    private LocalDateTime expiryDate;
    private String message;
    private Map<String, ?> context;
}
