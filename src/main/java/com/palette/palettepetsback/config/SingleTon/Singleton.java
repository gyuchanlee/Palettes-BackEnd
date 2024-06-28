package com.palette.palettepetsback.config.SingleTon;

import com.palette.palettepetsback.config.aop.notification.TimeTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Singleton {

    public static final String S3_BUCKET_NAME = "bitcamp-edu-bucket-55";
//    public static final String FRONT_URL = "http://localhost:80";
    public static final String FRONT_URL = "http://175.45.204.119";

    @Bean
    public Integer PAGE_SIZE(){
        return 10;
    }

    @Bean
    public String BUCKET_NAME(){
        return "bitcamp-edu-bucket-55";
    }

//    @Bean
//    public TimeTrace timeTrace(){
//        return new TimeTrace();
//    }
}
