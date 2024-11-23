package com.engsoft.marmita.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String role;

    private boolean active;
}
