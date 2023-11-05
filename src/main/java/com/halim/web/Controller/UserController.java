package com.halim.web.Controller;


import com.halim.config.UserDetailsServiceImpl;
import com.halim.dto.LoginDto;
import com.halim.dto.UserDtoWithOutPassword;
import com.halim.model.User;
import com.halim.service.UserService;
import com.halim.util.JwtUtil;
import com.halim.web.response.UserTokenResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/public/auth")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/token")
	public ResponseEntity<?> generateToken(@Valid @RequestBody LoginDto loginDto) throws Exception {

			if(loginDto.getEmail().toString().contains("@")) {
		   	  this.authenticationManager.authenticate(
				   	new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			}


		// fine area..
		UserDetails userDetails = null;

		User user = null;
		if(loginDto.getEmail().toString().contains("@") == false) {
//			user = userRepository.getuserByNumber(jwtRequest.getUsername());
			String requestUsername = loginDto.getEmail();
			 user = userService.findByUserName(requestUsername);
			userDetails = this.userDetailsService.loadUserByUsername(user.getEmail());

		}
		else{
//			user = userRepository.getUserByUserName(jwtRequest.getUsername());
			String requestUsername = loginDto.getEmail();
			user = userService.findByUserName(requestUsername);
			userDetails = this.userDetailsService.loadUserByUsername(loginDto.getEmail());
		}

		String token = this.jwtUtil.generateToken(userDetails);

		UserDtoWithOutPassword data = this.modelMapper.map(user, UserDtoWithOutPassword.class);
		UserTokenResponse userTokenResponse = new UserTokenResponse();
		data.setToken(token);
		userTokenResponse.setMessage("User token response");
		userTokenResponse.setTimeStamp(System.currentTimeMillis());
		userTokenResponse.setStatusCode(HttpStatus.OK.value());
		userTokenResponse.setData(data);
		return new ResponseEntity<UserTokenResponse>(userTokenResponse,HttpStatus.OK);
	}




}
