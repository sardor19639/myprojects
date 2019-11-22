/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.logger;



/**
 *
 * @author sardorrokhillaev
 */
public class LogMessage {

    private String className;
    private String methodName;
    private String cause;
    private String request;
    private String errorCode;
    private Object requestBody;
   
    private LogMessage() {
    }
  
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getClasssName() {
        return className;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCause() {
        return cause;
    }

    public String getRequest() {
        return request;
    }

    public static Builder newBuilder() {
        return new LogMessage().new Builder();
    }

    public class Builder {

        public Builder setClassName(String className) {
            LogMessage.this.className = className;
            return this;
        }

        public Builder setMethodName(String methodName) {
            LogMessage.this.methodName = methodName;
            return this;
        }

        public Builder setRequestBody(Object body) {
            LogMessage.this.requestBody = body;
            return this;
        }

        public Builder setCause(String cause) {
            LogMessage.this.cause = cause;
            return this;
        }

        public Builder setErrorCode(String errorCode) {
            LogMessage.this.errorCode = errorCode;
            return this;
        }

        public Builder setRequest(String request) {
            LogMessage.this.request = request;
            return this;
        }

        public LogMessage build() {
            return LogMessage.this;
        }

    }
}
