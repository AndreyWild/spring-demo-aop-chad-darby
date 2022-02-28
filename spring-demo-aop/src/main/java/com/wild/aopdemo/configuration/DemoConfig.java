package com.wild.aopdemo.configuration;

import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "com.wild.aopdemo")
public class DemoConfig {
}
