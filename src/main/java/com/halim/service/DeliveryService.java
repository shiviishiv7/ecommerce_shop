package com.halim.service;


import com.halim.dto.CreateReturnOrderBody;
import com.halim.dto.ShippingDto;
import com.halim.dto.ShippingOrderDto;
import com.halim.web.response.ProductDeliveryReturnResponse;
import com.halim.web.response.ShippingTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryService {

//    public ShippingTokenResponse getShipRocketToken()
//    {
//        final String uri = "https://apiv2.shiprocket.in/v1/external/auth/login";
//
//        RestTemplate restTemplate = new RestTemplate();
//        LoginDto loginDto = new LoginDto("resume2carrier@gmail.com","tyuCVB78$#");
//        ShippingTokenResponse shippingTokenResponse = restTemplate.postForObject(uri, loginDto, ShippingTokenResponse.class);
//        return shippingTokenResponse;
//    }

    @Autowired
   private ShippingTokenResponse shippingTokenResponse;

    private HttpHeaders createHttpHeaders()
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer "+shippingTokenResponse.getToken());
        return headers;
    }

    public ShippingOrderDto createOrder(ShippingDto shippingDto)
    {
        final String uri = "https://apiv2.shiprocket.in/v1/external/orders/create/adhoc";

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<ShippingDto> entity = new HttpEntity<ShippingDto>(shippingDto, headers);
            ResponseEntity<ShippingOrderDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, ShippingOrderDto.class);
            System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
            return  response.getBody();
        }
        catch (Exception eek) {
            System.out.println("** Exception: "+ eek.getMessage());
        }
        return null;
    }

    public Object getTrackingDetailsById(String orderId) {




        final String uri = "https://apiv2.shiprocket.in/v1/external/courier/track/awb/"+orderId;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
            System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
            return response.getBody();
        }
        catch (Exception eek) {
            System.out.println("** Exception: "+ eek.getMessage());
        }

        return null;

    }

    public ProductDeliveryReturnResponse createReturnOrder(CreateReturnOrderBody body) {


        final String uri = "https://apiv2.shiprocket.in/v1/external/orders/create/return";

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            return   restTemplate.postForObject(uri, body, ProductDeliveryReturnResponse.class);

        }
        catch (Exception eek) {
            System.out.println("** Exception: "+ eek.getMessage());
        }

        return null;
}


//    public

}
