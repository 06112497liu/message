package com.bbd.message.exception;

import com.bbd.message.enums.MessageEnum;

/**
 * @author fisher
 * @version $Id: MessageException.java, v 0.1 2017/11/7 11:24 fisher Exp $
 */
public class MessageException extends AppBaseException{
    /**
     * @param status
     * @param ex
     */
    public MessageException(MessageEnum status, Throwable ex) {
        super(status, ex);
    }

    /**
     * @param status
     */
    public MessageException(MessageEnum status) {
        super(status);
    }

    /**
     * @param errorMsg
     */
    public MessageException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * @param errorMsg
     */
    public MessageException(String errorMsg,Throwable ex) {
        super(errorMsg,ex);
    }
}
