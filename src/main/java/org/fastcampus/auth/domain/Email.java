package org.fastcampus.auth.domain;

import java.util.regex.Pattern;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Email {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_-]+@[a-zA-Z0-9+.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public static Email createEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }

        if (isNotValidEmailString(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }

        return new Email(email);
    }

    public String getEmail() {
        return email;
    }

    private static boolean isNotValidEmailString(String email) {
        return !pattern.matcher(email).matches();
    }
}
