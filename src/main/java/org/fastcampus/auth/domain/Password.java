package org.fastcampus.auth.domain;

import lombok.Getter;

@Getter
public class Password {

    private String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createEncryptedPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        return new Password(SHA256.encrypt(password));
    }

    public boolean matches(String password) {
        return this.encryptedPassword.equals(SHA256.encrypt(password));
    }

}
