package com.gjl.weixin.common.exception;

/**
 * @Author: WilliamJL
 * @Date: 2019/11/14 15:37
 * @Version 1.0
 */
public class FinancialException extends RuntimeException {

    private static final long serialVersionUID = 3858796974606918901L;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FinancialException(String message) {
        super();
        this.message = message;
    }
}
