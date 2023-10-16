package com.greytip.summon_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobEventRequestDTO {

    private String jobId;
    private String businessKey;
    private String message;
    private String status;
    private String group;
    private Long duration;
    private String source;
}
