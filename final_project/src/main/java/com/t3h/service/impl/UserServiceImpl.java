package com.t3h.service.impl;

import com.t3h.entity.UserEntity;
import com.t3h.model.dto.RoleDto;
import com.t3h.model.dto.UserDto;
import com.t3h.repository.RoleRepository;
import com.t3h.repository.UserRepository;
import com.t3h.service.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl() {
        logger.info("Tạo ra bean: {}",UserServiceImpl.class);
    }

    @Override
    public UserDto findUserByUsername(String userName) {
        UserEntity userEntity = userRepository.findUserByUsername(userName);
        UserDto userDto = modelMapper.map(userEntity,UserDto.class);
        List<RoleDto> roleDtos = roleRepository.getRoleByUsername(userName).stream().map(role -> modelMapper.map(role,RoleDto.class)).toList();
//        List<RoleDto> roleDtos = new ArrayList<>();
//        userEntity.getRoleEntities().forEach(role -> {
//            RoleDto roleDto = modelMapper.map(role,RoleDto.class);
//            roleDtos.add(roleDto);
//        });
        userDto.setRoleDtos(roleDtos);
        return userDto;
    }
}
