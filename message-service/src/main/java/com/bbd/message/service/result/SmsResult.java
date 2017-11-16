/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.bbd.message.service.result;

import java.io.Serializable;

/**
 *
 * @author tjwang
 * @version $Id: SmsResult.java, v 0.1 2017/6/8 0008 16:55 tjwang Exp $
 */
public class SmsResult implements Serializable {

    private String state;

    public SmsResult() {
        this.state = "1";
    }

    public SmsResult(String state) {
        this.state = state;
    }

    public boolean isSuccess() {
        return "1".equals(state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
