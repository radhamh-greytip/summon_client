package com.greytip.summon_client;

import com.greytip.summon_client.dto.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SummonClient {
    JobDTO getJob(@Path("id") String Id);


    JobResponseDTO createJob(@Body JobRequestDTO jobRequestDTO);

    String updateTotalSteps(@Path("id") String Id, @Body JobUpdateRequestDTO requestDTO);

    String markJobAsDone(@Path("id") String Id);
    JobInfoDTO getJobStatus(@Path("id") String id);

    String deleteJob(@Path("id") String id);

    String ping(@Path("id") String id);

    String isJobsCompleted(@Path("ids") String id);

    JobInfoDTO getJobEvents(@Path("ids") String id,@Path("consumer") String consumer);

    JobResponseDTO nextStep(@Path("id") String id);

    JobResponseDTO updateEvent(@Body JobEventRequestDTO requestDTO);

    JobResponseDTO registerEvent(@Body JobEventRequestDTO requestDTO);

    JobResponseDTO registerEvent(String jobId,String businessKey, String message, String status, String group, Long duration,String source);

    JobResponseDTO updateEvent(String jobId,String businessKey, String message, String status, String group, Long duration,String source);

    String  incrementSteps(String id,Long count) ;

}
