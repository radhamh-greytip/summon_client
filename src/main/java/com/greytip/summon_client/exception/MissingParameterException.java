package com.greytip.summon_client.exception;

public class MissingParameterException extends RuntimeException {

    public MissingParameterException() {
        super();
    }

    public MissingParameterException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public MissingParameterException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public MissingParameterException(String arg0) {
        super(arg0);
    }

    public MissingParameterException(Throwable arg0) {
        super(arg0);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
