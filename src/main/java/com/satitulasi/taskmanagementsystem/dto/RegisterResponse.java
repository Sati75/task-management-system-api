
package com.satitulasi.taskmanagementsystem.dto;

import com.satitulasi.taskmanagementsystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
}
