package com.corpgtb.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTUser {
    String userId;
    String username;
    String rol;
}
