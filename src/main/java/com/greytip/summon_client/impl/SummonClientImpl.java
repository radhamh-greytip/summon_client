package com.greytip.summon_client.impl;

import com.greytip.summon_client.SummonClient;
import com.greytip.summon_client.SummonServiceApi;
import com.greytip.summon_client.constants.JobConstants;
import com.greytip.summon_client.dto.*;
import com.greytip.summon_client.exception.SummonApiException;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SummonClientImpl implements SummonClient {
    private static final Logger logger = LoggerFactory.getLogger(SummonClientImpl.class);
    private final String url;
    private final int readTimeout = 10;
    private final int connectTimeout = 5;
    private SummonServiceApi summonServiceApi;
    public SummonClientImpl(String url) {
        this.url = url;
        summonServiceApi = getClient().create(SummonServiceApi.class);
    }
    public Retrofit getClient() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .followRedirects(true)
                //.addInterceptor(interceptor())
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Override
    public JobDTO getJob(String Id) {
        logger.debug("Fetching Job by Job Id: {}", Id);
        Call<JobDTO> call = summonServiceApi.getJob(Id);
        try {
            Response<JobDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }

        } catch (IOException e) {
            throw new SummonApiException("Api exception when fetching Job info", e);
        }
    }

    @Override
    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {
        logger.debug("Creating a Job");
        Call<JobResponseDTO> call = summonServiceApi.createJob(jobRequestDTO);
        try {
            Response<JobResponseDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobResponseDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }

        } catch (IOException e) {
            throw new SummonApiException("Api exception when creating Job", e);
        }
    }

    @Override
    public String updateTotalSteps(String Id, JobUpdateRequestDTO requestDTO) {
        logger.debug("Creating a Job");
        Call<String> call = summonServiceApi.updateTotalSteps(Id,requestDTO);
        try {
            call.execute();
        } catch (IOException e) {
            throw new SummonApiException("Api exception when creating Job", e);
        }
        return "OK";
    }

    @Override
    public String markJobAsDone(String Id) {
        logger.debug("Marking Job as Done");
        Call<String> call = summonServiceApi.markJobAsDone(Id);
        try {
            call.execute();
        } catch (IOException e) {
            throw new SummonApiException("Api exception when creating Job", e);
        }
        return "OK";
    }

    @Override
    public JobInfoDTO getJobStatus(String id) {
        logger.debug("Get Job Status");
        Call<JobInfoDTO> call = summonServiceApi.getJobStatus(id);
        try {
            Response<JobInfoDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobInfoDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when getting Job status", e);
        }
    }

    @Override
    public String deleteJob(String id) {
        logger.debug("Get Job Status");
        Call<String> call = summonServiceApi.deleteJob(id);
        try {
            Response<String> response = call.execute();
            if (response.isSuccessful()) {
                return "OK";
            } else {
                if (response.code() == 404) {
                    return "OK";
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when deleting Job", e);
        }
    }

    @Override
    public String ping(String id) {
        logger.debug("Ping Job");
        Call<String> call = summonServiceApi.ping(id);
        try {
            Response<String> response = call.execute();
            if (response.isSuccessful()) {
                return "OK";
            } else {
                if (response.code() == 404) {
                    return JobConstants.JobStatus.ABORTED.toString();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when ping Job", e);
        }
    }

    @Override
    public String isJobsCompleted(String id) {
        logger.debug("checking Job completed/not");
        Call<String> call = summonServiceApi.isJobsCompleted(id);
        try {
            Response<String> response = call.execute();
            if (response.isSuccessful()) {
                return "OK";
            } else {
                if (response.code() == 404) {
                    return JobConstants.JobStatus.ABORTED.toString();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when checking Job completed/not", e);
        }
    }

    @Override
    public JobInfoDTO getJobEvents(String id, String consumer) {
        logger.debug("Get Job events");
        Call<JobInfoDTO> call = summonServiceApi.getJobEvents(id,consumer);
        try {
            Response<JobInfoDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobInfoDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when getting Job events", e);
        }
    }

    @Override
    public JobResponseDTO nextStep(String id) {
        logger.debug("Get nextStep");
        Call<JobResponseDTO> call = summonServiceApi.nextStep(id);
        try {
            Response<JobResponseDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobResponseDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when getting nextStep", e);
        }
    }

    @Override
    public JobResponseDTO updateEvent(JobEventRequestDTO requestDTO) {
        logger.debug("Get updateEvent");
        return getUpdateJobResponse(requestDTO);
    }

    @Override
    public JobResponseDTO registerEvent(String jobId, String businessKey, String message, String status, String group, Long duration, String source) {
        logger.debug("Get registerEvent");
        JobEventRequestDTO jobEventRequestDTO = new JobEventRequestDTO(jobId, businessKey, message, status, group, duration, source);
        return getJobRegisterEventResponse(jobEventRequestDTO);
    }

    private JobResponseDTO getJobRegisterEventResponse(JobEventRequestDTO jobEventRequestDTO) {
        Call<JobResponseDTO> call = summonServiceApi.registerEvent(jobEventRequestDTO);
        try {
            Response<JobResponseDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobResponseDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when calling registerEvent", e);
        }
    }

    @Override
    public JobResponseDTO updateEvent(String jobId, String businessKey, String message, String status, String group, Long duration, String source) {
        logger.debug("Get updateEvent");
        JobEventRequestDTO jobEventRequestDTO = new JobEventRequestDTO(jobId, businessKey, message, status, group, duration, source);
        return getUpdateJobResponse(jobEventRequestDTO);
    }

    private JobResponseDTO getUpdateJobResponse(JobEventRequestDTO jobEventRequestDTO) {
        Call<JobResponseDTO> call = summonServiceApi.updateEvent(jobEventRequestDTO);
        try {
            Response<JobResponseDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 404) {
                    return new JobResponseDTO();
                } else {
                    throw new SummonApiException(String.format("Summon API error code=%d, error=%s", response.code(), response.errorBody().string()));
                }
            }
        } catch (IOException e) {
            throw new SummonApiException("Api exception when calling updateEvent", e);
        }
    }

    @Override
    public JobResponseDTO registerEvent(JobEventRequestDTO requestDTO) {
        logger.debug("Get registerEvent");
        return getJobRegisterEventResponse(requestDTO);


    }

    @Override
    public String incrementSteps(String id, Long count) {
        logger.debug("Incrementing Job step count");
        Call<String> call = summonServiceApi.incrementSteps(id, count);
        try {
            call.execute();
        } catch (IOException e) {
            throw new SummonApiException("Api exception when incrementing Job step count", e);
        }
        return "OK";
    }
}
