package com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions;

import java.io.Serial;

public class UserNameNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNameNotFoundException(String message) {
        super(message);
    }
}
