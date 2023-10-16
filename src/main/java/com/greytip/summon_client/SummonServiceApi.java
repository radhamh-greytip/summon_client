package com.greytip.summon_client;

import com.greytip.summon_client.dto.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface SummonServiceApi {


    @GET("/v1/job/{id}")
    Call<JobDTO> getJob(@Path("id") String Id);

    @POST("/v1/job")
    Call<JobResponseDTO> createJob(@Body JobRequestDTO jobRequestDTO);

    @PUT("/v1/job/{id}/total")
    Call<String> updateTotalSteps(@Path("id") String Id, @Body JobUpdateRequestDTO requestDTO);

    @PUT("/v1/job/{id}/increment/{count}")
    Call<String> incrementSteps(@Path("id") String Id,@Path("count") Long count);
    @PUT("/v1/job/{id}/done")
    Call<String> markJobAsDone(@Path("id") String Id);
    @GET("/v1/job/{id}/status")
    Call<JobInfoDTO> getJobStatus(@Path("id") String id);

    @DELETE("/v1/job/{id}")
    Call<String> deleteJob(@Path("id") String id);

    @GET("/v1/job/{id}/check")
    Call<String> ping(@Path("id") String id);

    @GET("/v1/job/{ids}/is-completed")
    Call<String> isJobsCompleted(@Path("ids") String id);

    @GET("/v1/job/{ids}/detailed-status")
    Call<JobInfoDTO> getJobEvents(@Path("ids") String id,@Query("consumer") String consumer);

    @POST("/v1/job/{id}/next")
    Call<JobResponseDTO> nextStep(@Path("id") String id);

    @POST("/v1/job/update-event")
    Call<JobResponseDTO> updateEvent(@Body JobEventRequestDTO requestDTO);

    @POST("/v1/job/register-event")
    Call<JobResponseDTO> registerEvent(@Body JobEventRequestDTO requestDTO);

}
