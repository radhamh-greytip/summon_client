package com.greytip.summon_client.exception;

public class SummonApiException extends RuntimeException{

    public SummonApiException() {
        super();
    }

    public SummonApiException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public SummonApiException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SummonApiException(String arg0) {
        super(arg0);
    }

    public SummonApiException(Throwable arg0) {
        super(arg0);
    }
}
