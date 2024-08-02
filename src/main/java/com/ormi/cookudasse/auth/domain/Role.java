package com.ormi.cookudasse.auth.domain;

import lombok.Getter;

@Getter
public enum Role{
    BANNED("정지"), ORDINARY("일반"), MANAGER("관리자");

    private final String description;

    Role(String description){
        this.description = description;
    }
}
