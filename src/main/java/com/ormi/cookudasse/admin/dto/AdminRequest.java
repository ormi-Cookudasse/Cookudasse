package com.ormi.cookudasse.admin.dto;


import com.ormi.cookudasse.auth.domain.Role;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AdminRequest {

    private Long userId;

    @Enumerated
    private Role role;
}

