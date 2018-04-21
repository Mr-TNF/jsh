package com.jhs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: TangNengFa
 * @descption: 监听log4j日志，获取目录
 * @create: 2018-04-20-10-07
 **/
public class log4jListener implements ServletContextListener {
    public static final String log4jDirKeyDebug = "log4j_dir_debug";
    public static final String log4jDirKeyWarn = "log4j_dir_warn";
    public static final String log4jDirKeyError = "log4j_dir_error";
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.getProperties().remove(log4jDirKeyDebug);
        System.getProperties().remove(log4jDirKeyWarn);
        System.getProperties().remove(log4jDirKeyError);
    }
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String path = File.separator;
        String log4jDir = servletContextEvent.getServletContext().getRealPath("/");
        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyy_MM");
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date();
        String DateYM = simpleDateFormatYM.format(date);
        String DateYMD = simpleDateFormatYMD.format(date);
        log4jDir = log4jDir.replace("out"+path+"artifacts"+path+"jhsTest_war_exploded"+path,
                "web"+path+"logs"+path);
        System.setProperty(log4jDirKeyDebug, log4jDir+"debug"+path+DateYM+path+DateYMD+"_");
        System.setProperty(log4jDirKeyWarn, log4jDir+"warn"+path+DateYM+path+DateYMD+"_");
        System.setProperty(log4jDirKeyError, log4jDir+"error"+path+DateYM+path+DateYMD+"_");
    }
}
