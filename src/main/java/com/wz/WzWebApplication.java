package com.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAsync
public class WzWebApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SpringApplication.run(WzWebApplication.class, args);
    }
}
