package com.things.common.log;

public abstract class Logger {
    
    protected org.slf4j.Logger log;
    
    private static boolean isLog = true;

    public static boolean isLog(){
        return isLog;
    }

    public void setLogger(org.slf4j.Logger logger) {
        this.log = logger;
    }
    
    /**
     * 跟踪
     * @author 马志铭
     * @param 消息
     * */
    public abstract void trace(String msg);

    public abstract void trace(String msg, Throwable e);
    
    public abstract void debug(String msg);

    public abstract void debug(String msg, Throwable e);
    
    public abstract void info(String msg);

    public abstract void info(String msg, Throwable e);

    public abstract void warn(String msg);

    public abstract void warn(String msg, Throwable e);
    
    public abstract void error(String msg);

    public abstract void error(String msg, Throwable e);

}
