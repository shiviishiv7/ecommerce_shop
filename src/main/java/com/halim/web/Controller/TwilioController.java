package com.halim.web.Controller;


import com.halim.dto.OtpDto;
import com.halim.dto.RegisterOtp;
import com.halim.dto.VerifyOtpRequest;
import com.halim.exceptionsHandler.RecordFound;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.service.TwilioOTPService;
import com.halim.service.UserService;
import com.halim.web.response.OtpResponse;
import com.halim.web.response.UserTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/public/auth")
public class TwilioController {

    @Autowired
    private UserService userService;

    @Autowired
    private TwilioOTPService twilioOTPService;
    @PostMapping("/register-send-otp")
    public ResponseEntity<OtpResponse> registerSendOTP(@Valid @RequestBody OtpDto otpDto) throws RecordFound {
        Map<String, String> stringStringMap = twilioOTPService.registerSendOTP(otpDto.getNumber());
        return addStatusCodeAndMessageHeloper(stringStringMap,"success");
    }
    @PostMapping("/register-resend-otp")
    public ResponseEntity<OtpResponse> registerResendOTP(@Valid @RequestBody OtpDto otpDto) throws RecordFound {
        Map<String, String> stringStringMap = twilioOTPService.registerSendOTP(otpDto.getNumber());
        return addStatusCodeAndMessageHeloper(stringStringMap,"success");
    }


    @PostMapping("/register-verify-otp")
    public ResponseEntity<UserTokenResponse> registerVerifyOTP(@Valid @RequestBody RegisterOtp registerOtp) throws Exception {
        UserTokenResponse userTokenResponse= twilioOTPService.registerVerifyOTP(registerOtp);
        return new ResponseEntity<UserTokenResponse>(userTokenResponse,HttpStatus.OK);
    }

    @PostMapping("/login-send-otp")
    public ResponseEntity<OtpResponse> loginSendOTP(@Valid @RequestBody OtpDto otpDto) throws RecordNotFound {
        Map<String, String> mapResponseEntity = twilioOTPService.loginSendOTP(otpDto.getNumber());
        return addStatusCodeAndMessageHeloper(mapResponseEntity,"success");

    }

    @PostMapping("/login-resend-otp")
    public ResponseEntity<OtpResponse> loginResendOTP(@Valid @RequestBody OtpDto otpDto) throws RecordNotFound {
        Map<String, String> stringStringMap = twilioOTPService.loginSendOTP(otpDto.getNumber());
        return addStatusCodeAndMessageHeloper(stringStringMap,"success");
    }


    @PostMapping("/login-verify-otp")
    public ResponseEntity<UserTokenResponse> loginVerifyOTP(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) throws RecordNotFound {
        UserTokenResponse userTokenResponse= twilioOTPService.loginVerifyOTP(verifyOtpRequest.getNumber(),verifyOtpRequest.getOtp());
        return new ResponseEntity<UserTokenResponse>(userTokenResponse,HttpStatus.OK);
    }



    private ResponseEntity<OtpResponse> addStatusCodeAndMessageHeloper(Map<String, String> mapResponseEntity,String message) {
        OtpResponse response = new OtpResponse();
        response.setMessage(message);
        response.setTimeStamp(System.currentTimeMillis());
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(mapResponseEntity);
        return new ResponseEntity<OtpResponse>(response,HttpStatus.OK);
    }


}

