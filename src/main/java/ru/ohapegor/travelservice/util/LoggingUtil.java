package ru.ohapegor.travelservice.util;


import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggingUtil {

    public static String enterInfo(){
        return "Entering "+ getContext();
    }

    public static String exitInfo(){
        return "Exiting "+ getContext();
    }

    public static String enterInfo(Object o){
        return "Entering "+ getContext()+" ["+o+"]";
    }

    public static String exitInfo(Object o){
        return "Exiting "+ getContext()+" ["+o+"]";
    }

    public static String methodInfo(){
        return getContext();
    }

    private static String getContext(){
        return Thread.currentThread().getStackTrace()[3].getClassName().replaceAll(".*\\.","")+
                        "."+
        Thread.currentThread().getStackTrace()[3].getMethodName()+"()";
    }

    public static String errorInfo(Throwable e){
        return "Exception in "+getContext()+" : "+getStackTrace(e);
    }

    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }


}
