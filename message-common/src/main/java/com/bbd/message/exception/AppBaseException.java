/**  
 * BBD Service Inc
 * All Rights Reserved @2016
 *
 */
package com.bbd.message.exception;


import com.bbd.message.enums.MessageEnum;

/**
 * 异常基类.
 * 
 * @author hyjun
 * @version Id: TestUtil, v 0.1 2017-10-13 上午 10:41 Exp $$
 */
public abstract class AppBaseException extends RuntimeException {

  /** 序列化ID */
  private static final long serialVersionUID = 8449325257526444936L;
  
  private MessageEnum status;
  
  /**
   * @param status
   */
  public AppBaseException(MessageEnum status) {
    super("["+status.getCode()+"]: " + status.getMessage());
    this.status = status;
  }
  
  /**
   * @param status
   */
  public AppBaseException(MessageEnum status, Throwable ex) {
    super("["+status.getCode()+"]: " + status.getMessage(), ex);
    this.status = status;
  }

  /**
   * @param errorMsg
   */
  public AppBaseException(String errorMsg) {
    super("["+MessageEnum.SYSTEM_ERROR.getCode()+"]: " + errorMsg);
    this.status = MessageEnum.SYSTEM_ERROR;
  }

  /**
   *
   * @param errorMsg
   * @param ex
   */
  public AppBaseException(String errorMsg, Throwable ex) {
    super("["+MessageEnum.SYSTEM_ERROR.getCode()+"]: " + errorMsg,ex);
    this.status = MessageEnum.SYSTEM_ERROR;
  }

  public MessageEnum getResultStatusEnum() {
    return status;
  }
}
