package com.t3h.service;

import com.t3h.model.dto.UserDto;

public interface IUserService {
    UserDto findUserByUsername(String userName);

}
