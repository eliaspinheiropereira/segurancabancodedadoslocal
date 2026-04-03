package com.github.eliaspinheiropereira.seguranca.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Roles {
    ADMIN,
    USUARIO;

    @JsonCreator
    public static Roles fromValue(String value){
        return Roles.valueOf(value.toUpperCase());
    }
}
