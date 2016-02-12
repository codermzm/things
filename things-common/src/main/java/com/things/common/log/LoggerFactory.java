package com.things.common.log;

import java.io.FileInputStream;
import java.util.Properties;

import com.things.common.log.impl.LoggerImpl;
import com.things.common.path.PathUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;

public final class LoggerFactory {

    static Properties prop = null;
    static {
        String rootPath = PathUtils.getConfigPath();
        String path = StringUtils.EMPTY;
        path = PathUtils.combine(rootPath, "log/log4j.properties");

        prop = new Properties();
        try {
            prop.load(new FileInputStream(path)); // 加载log4j.properties
            prop.setProperty("log4j.appender.file.File",PathUtils.combine(PathUtils.getWebRoot(),prop.getProperty("log4j.appender.file.File"))); // 设置日志文件的输出路径
            PropertyConfigurator.configure(prop); // 加载配置项
        } catch (Exception e) {
            System.out.println("初始化日志失败!异常信息：" + e.getMessage());
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger loggerApi = new LoggerImpl(prop);
        loggerApi.setLogger(org.slf4j.LoggerFactory.getLogger(clazz));

        return loggerApi;
    }
}