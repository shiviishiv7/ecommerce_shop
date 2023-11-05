package com.halim.config;

import com.halim.dto.LoginDto;
import com.halim.web.response.ShippingTokenResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class DeliveryShippingConfig {


    @Bean
    public ShippingTokenResponse getShipRocketToken()
    {
        final String uri = "https://apiv2.shiprocket.in/v1/external/auth/login";

        RestTemplate restTemplate = new RestTemplate();
        LoginDto loginDto = new LoginDto("resume22carrier@gmail.com","tyuCVB78$#");
        ShippingTokenResponse shippingTokenResponse = restTemplate.postForObject(uri, loginDto, ShippingTokenResponse.class);
        return shippingTokenResponse;
    }
}
