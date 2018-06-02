package ru.ohapegor.travelservice;

import java.util.Map;

public class Test {

    @org.junit.Test
    public void test(){
        System.out.println(System.getenv("OS"));
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }
}
