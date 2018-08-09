package com.alpha.module_common.utils;

import android.util.Log;

public class NLog {
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    private static boolean isDebug = false;

    public static void d(String tag, Object... args) {
        log(Log.DEBUG, null, tag, args);
    }

    public static void i(String tag, Object... args) {
        log(Log.INFO, null, tag, args);
    }

    public static void w(String tag, Object... args) {
        log(Log.WARN, null, tag, args);
    }

    public static void e(Throwable ex) {
        log(Log.ERROR, ex, null);
    }

    public static void e(String tag, Object... args) {
        log(Log.ERROR, null, tag, args);
    }

    public static void e(Throwable ex, String tag, Object... args) {
        log(Log.ERROR, ex, tag, args);
    }
    /**
     * @param priority log 类型
     * @param tag log 表情
     * @param ex log 异常
     * @param args log 内容
     */
    private static void log(int priority, Throwable ex, String tag, Object... args) {

        if (!isDebug) return;

        String log = "";
        if (ex == null) {
            if(args != null && args.length > 0){
                StringBuilder logBuilder = new StringBuilder();
                for(Object obj : args){
                    logBuilder.append(String.valueOf(obj));
                }
                log = logBuilder.toString();
            }
        } else {
            String logMessage = ex.getMessage();
            String logBody = Log.getStackTraceString(ex);
            log = String.format(LOG_FORMAT, logMessage, logBody);
        }
        Log.println(priority, tag, log);
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean isDebug) {
        NLog.isDebug = isDebug;
    }
}
