package com.soa.exception;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.LogUtil;
import com.lianzt.util.StringUtil;
import com.soa.util.SystemUtil;
import java.util.regex.Matcher;

public class GlobalException extends RuntimeException {

    /**
     * 占位符
     */
    private static final String PLACEHOLDER = "!#!";
    private int errorCode;
    private String errorMsg;

    public GlobalException() {
        super();
    }

    public GlobalException(AbstractCommonData head) {
        this.errorCode = Integer.parseInt(head.getStringValue("response_code"));
        if (this.errorCode > 900000) {
            this.errorMsg = head.getStringValue("response_desc");
        } else if (StringUtil.isNull(SystemUtil.getExByCode(this.errorCode))) {
            this.errorMsg = head.getStringValue("response_desc");
        } else {
            this.errorMsg = SystemUtil.getExByCode(this.errorCode);
            if (this.errorMsg.indexOf(PLACEHOLDER) != -1) {
                this.errorMsg = head.getStringValue("response_desc");
            }
        }
        if (LogUtil.isDebug()) {
            this.errorMsg = "远程方法返回：" + this.errorMsg;
        }
    }

    public GlobalException(int errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = SystemUtil.getExByCode(errorCode);
        if (errorMsg == null) {
            this.errorCode = 999997;
            this.errorMsg = "未定义的异常代码 ： " + errorCode;
        }
    }

    public GlobalException(String message) {
        this.errorMsg = message;
    }

    public GlobalException(String message, Throwable throwable) {
        super(message, throwable);
        this.errorMsg = message;
    }

    public GlobalException(int errorCode, Object... args) {
        this(errorCode);
        for (Object o : args) {
            errorMsg = errorMsg.replaceFirst(PLACEHOLDER, Matcher.quoteReplacement(o.toString()));
        }
    }

    public GlobalException(int errorCode, Throwable throwable) {
        super("( " + errorCode + " ) " + SystemUtil.getExByCode(errorCode), throwable);
        this.errorCode = errorCode;
        this.errorMsg = SystemUtil.getExByCode(errorCode);
    }

    public GlobalException(int errorCode, Throwable throwable, Object... args) {
        this(errorCode, throwable);
        for (Object o : args) {
            errorMsg = errorMsg.replaceFirst(PLACEHOLDER, Matcher.quoteReplacement(o.toString()));
        }
    }

    public GlobalException(Throwable throwable) {
        super(throwable);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : ( " + errorCode + " ) " + this.errorMsg;
    }
}
