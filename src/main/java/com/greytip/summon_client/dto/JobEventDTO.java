package com.greytip.summon_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobEventDTO {
    private String jobId;
    private String businessKey;
    private String message;
    private String eventGroup;
    private String id ;
    private Long step;

    private Long createdOn;

    private Long updatedOn;

    private String status;
    private String source;

    private Long duration;
    private Long  ttl;
}
