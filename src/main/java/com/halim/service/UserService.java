package com.halim.service;

import com.halim.dao.UserRepository;
import com.halim.dto.UserDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.User;
import com.halim.request.MakeSellerRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findByUserName(String username){

        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else
            throw new UsernameNotFoundException("User not found by Username "+username);

    }


    public User findUserById(String userId) {
//        Optional<User> optionalUser = userRepository.findById("");
        Optional<User> optionalUser = null;

            if(optionalUser.isPresent()){
                return optionalUser.get();
            }else try {
                throw new RecordNotFound("User not found by Id"+userId);
            } catch (RecordNotFound e) {
                throw new RuntimeException(e);
            }

    }

    public UserDto addUser(UserDto userDto)  {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.findTopByOrderByIdDesc();
        if(user==null){
            userDto.setId(100);
        }else userDto.setId(user.getId()+100);
        User userModel = this.modelMapper.map(userDto, User.class);
        User save = this.userRepository.save(userModel);
        return this.modelMapper.map(save,UserDto.class);
    }



	public void makeSeller(MakeSellerRequest makeSellerRequest){

    }



}
