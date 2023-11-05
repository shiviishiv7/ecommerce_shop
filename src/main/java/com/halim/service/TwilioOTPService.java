package com.halim.service;

import com.halim.config.CustomUserDetails;
import com.halim.dao.UserRepository;
import com.halim.dto.RegisterOtp;
import com.halim.dto.UserDto;
import com.halim.dto.UserDtoWithOutPassword;
import com.halim.exceptionsHandler.RecordFound;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.User;
import com.halim.util.JwtUtil;
import com.halim.web.response.UserTokenResponse;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    Map<String,String> registerUserMap = new HashMap<>();
    Map<String,String> loginUserMap = new HashMap<>();

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    TwilioOTPService(){
//        this.registerUserMap.put("+919350039160","788084");
    }
    public Map<String, String> registerSendOTP(String phoneNumber) throws RecordFound {
        User user = userRepository.findByNumber(phoneNumber);
        if(user!=null){
            throw new RecordFound("Mobile number exists "+phoneNumber);
        }
        String otp = generateOtp();
        String from ="+13153063562";
        Message message = Message.creator(new PhoneNumber("+"+phoneNumber),
                new PhoneNumber(from), "Your OTP code is "+otp).create();
        registerUserMap.put(phoneNumber,otp);
        Map<String,String> map = new HashMap<>();
        map.put("message","OTP is send to this number "+phoneNumber);
        map.put("state","DELIVERED");
        return map;
    }

    public String generateOtp(){
        String format = new DecimalFormat("000000").format(new Random().nextInt(999999));
        return format;
    }

    public UserTokenResponse registerVerifyOTP(RegisterOtp registerOtp) throws RecordNotFound {
        Map<String,String> map = new HashMap<>();
        String number = registerOtp.getNumber();
        String otp = registerOtp.getOtp();
        if(registerUserMap.containsKey(number)){
            if(registerUserMap.get(number).equals(otp)){
                map.put("message","VERIFIED");
                registerUserMap.remove(number);
            }else throw new RecordNotFound("Invalid OTP sent by "+number);
        }else throw new RecordNotFound("Mobile number incorrect please check your Mobile number "+number);




            UserDto userDto = new UserDto();
            userDto.setPassword(registerOtp.getPassword());
            userDto.setEmail(registerOtp.getEmail());
            userDto.setNumber(registerOtp.getNumber());
            userDto.setFirstName(registerOtp.getFirstName());

        UserDto registerUser = this.userService.addUser(userDto);

        return generateTokenOfUser(registerUser.getNumber());


    }


    public Map<String, String> loginSendOTP(String phoneNumber) throws RecordNotFound {
        User user = userRepository.findByNumber(phoneNumber);
        if(user==null){
            throw new RecordNotFound("Mobile number not-exists with "+phoneNumber);
        }
        String otp = generateOtp();
        String from ="+13153063562";
        Message message = Message.creator(new PhoneNumber("+"+phoneNumber),
                new PhoneNumber(from), "Your OTP code is "+otp).create();
        loginUserMap.put(phoneNumber,otp);
        Map<String,String> map = new HashMap<>();
        map.put("message","OTP is send to this number "+phoneNumber);
        map.put("state","DELIVERED");
        return map;
    }

    public UserTokenResponse loginVerifyOTP(String number, String otp) throws RecordNotFound {
        Map<String,String> map = new HashMap<>();

        if(loginUserMap.containsKey(number)){
            if(loginUserMap.get(number).equals(otp)){
                map.put("message","VERIFIED");
                loginUserMap.remove(number);
            }else throw new RecordNotFound("Invalid OTP sent by "+number);
        }else throw new RecordNotFound("Mobile number incorrect please check your Mobile number "+number);
       return generateTokenOfUser(number);

    }
    public UserTokenResponse  generateTokenOfUser(String phoneNumber){
        User user = userRepository.findByNumber(phoneNumber);
        // fine area..
        UserDetails userDetails = new CustomUserDetails(user);
        String token = this.jwtUtil.generateToken(userDetails);

        UserDtoWithOutPassword data = this.modelMapper.map(user, UserDtoWithOutPassword.class);
        UserTokenResponse userTokenResponse = new UserTokenResponse();
        data.setToken(token);
        userTokenResponse.setMessage("User token response");
        userTokenResponse.setTimeStamp(System.currentTimeMillis());
        userTokenResponse.setStatusCode(HttpStatus.OK.value());
        userTokenResponse.setData(data);
        return userTokenResponse;
    }
}

//@Service
//public class TwilioOTPService {
//    @Autowired
//    private TwilioConfig twilioConfig;
//
//    Map<String, String> otpMap = new HashMap<>();
//
//    public PasswordResetResponseDto sendOTPForPasswordReset(PasswordResetRequestDto passwordResetRequestDto) {
//
//        PasswordResetResponseDto passwordResetResponseDto = null;
//        try {
//            PhoneNumber to = new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
//            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
//            String otp = generateOTP();
//            String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
//            Message message = Message
//                    .creator(to, from,
//                            otpMessage)
//                    .create();
//            otpMap.put(passwordResetRequestDto.getUserName(), otp);
//            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
//        } catch (Exception ex) {
//            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
//        }
//        return passwordResetResponseDto;
//    }
//
//    public String validateOTP(String userInputOtp, String userName) {
//        if (userInputOtp.equals(otpMap.get(userName))) {
//            otpMap.remove(userName,userInputOtp);
//            return "Valid OTP please proceed with your transaction !";
//        } else {
//            return "Invalid otp please retry !";
//        }
//    }
//
//    //6 digit otp
//    private String generateOTP() {
//        return new DecimalFormat("000000")
//                .format(new Random().nextInt(999999));
//    }
//
//}
