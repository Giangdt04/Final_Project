package com.t3h.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String code;
    private String username;
    private String password;
    private String email;

    List<RoleDto> roleDtos;

}
