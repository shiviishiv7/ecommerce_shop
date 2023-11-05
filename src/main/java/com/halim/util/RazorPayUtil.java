package com.halim.util;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RazorPayUtil {

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient("rzp_live_sE66qG7KfUgvRy","PoxxxFoLkfvb66aKpctMzDO2");
        return razorpayClient;
    }


}
