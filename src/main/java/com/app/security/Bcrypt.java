package com.app.security;

import com.password4j.BcryptFunction;

public class Bcrypt {
    public static BcryptFunction getBcryptFunction() {
        return BcryptFunction.getInstance(com.password4j.types.Bcrypt.B,12);
    }
}
