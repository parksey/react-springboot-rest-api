package com.example.gonggam.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SessionValidController {

    @GetMapping("/session")
    public ResponseEntity<Void> validSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            long lastAccessTime = session.getLastAccessedTime();
            int sessionTimeoutSeconds = session.getMaxInactiveInterval();
            long sessionExpirationTime = lastAccessTime + sessionTimeoutSeconds * 1000;
            if (System.currentTimeMillis() < sessionExpirationTime) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
