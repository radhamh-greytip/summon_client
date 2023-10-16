package com.greytip.summon_client.impl;

import com.greytip.summon_client.exception.MissingParameterException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SummonClientBuilder {
    private String url;
    public static SummonClientBuilder create() {
        return new SummonClientBuilder();
    }

    public SummonClientBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public SummonClientImpl build() {
        if (this.url == null) {
            throw new MissingParameterException("URL is not specified.");
        }
        return new SummonClientImpl(url);
    }

}