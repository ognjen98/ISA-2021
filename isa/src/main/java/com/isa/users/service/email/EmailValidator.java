package com.isa.users.service.email;

import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    private final String EMAIL_PATTERN = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    @Override
    public boolean test(String email) {
        return email.matches(EMAIL_PATTERN);
    }
}
