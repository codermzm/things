package com.things.common.log.impl;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.things.common.log.Logger;

public class LoggerImpl extends Logger {

    //private static Logger logger = LoggerFactory.getLogger(LoggerImpl.class);
    
    private static String ipAddress = StringUtils.EMPTY;
    private static boolean isDebug = false;
    public LoggerImpl(Properties properties) {
        isDebug = Boolean.valueOf((String)properties.get("hy.isDebug"));
        if(!isDebug){
            return ;
        }
        ipAddress = (String) properties.get("hy.ip");
        String port = (String) properties.get("hy.port");
        if (!StringUtils.isEmpty(port) && !StringUtils.isEmpty(ipAddress)
            && StringUtils.indexOf(ipAddress, ':') == -1) {
            ipAddress = String.format("%s:%s", ipAddress, port);
        }
    }

    /**
     * 跟踪
     * 
     * @author 马志铭
     * @param 消息
     * */
    public void trace(String msg) {
        super.log.trace(msg);
    }

    public void trace(String msg, Throwable e) {
        this.log.trace(msg, e);
    }

    public void debug(String msg) {
        this.log.debug(msg);
    }

    public void debug(String msg, Throwable e) {
        this.log.debug(msg, e);
    }

    public void info(String msg) {
        this.log.info(msg);
    }

    public void info(String msg, Throwable e) {
        this.log.info(msg, e);
    }

    public void warn(String msg) {
        this.log.warn(msg);
    }

    public void warn(String msg, Throwable e) {
        this.log.warn(msg, e);
    }

    public void error(String msg) {
        this.log.error(msg);
    }

    public void error(String msg, Throwable e) {
        this.log.error(msg, e);
    }
}
