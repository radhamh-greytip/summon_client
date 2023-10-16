package com.greytip.summon_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JobDTO {
    private String jobDefinitionId;
    private String  businessId;
    private String  title;
    private Long  totalSteps;
        private Long ttl;

        private String  id;
        private String tenantId;
        private String  parentJob;
        private Long startDate;
        private Long  endDate;
        private LocalDateTime expiryDate;
        private Map<String, ?> context;

        private String  status;
        private String  attachmentId;
        private Long lastUpdatedOn;
        private Long currentStep;

        private String  message;
}
