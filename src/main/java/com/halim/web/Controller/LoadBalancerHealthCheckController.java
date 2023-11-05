package com.halim.web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/")
public class LoadBalancerHealthCheckController {
    //load balance health check open url

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ALL GOOD";
    }

}
