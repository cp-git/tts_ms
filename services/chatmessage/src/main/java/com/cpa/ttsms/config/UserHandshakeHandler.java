package com.cpa.ttsms.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

//public class UserHandshakeHandler extends DefaultHandshakeHandler {
//    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);
//
//    @Override
//    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//        final String randomId = UUID.randomUUID().toString();
//        LOG.info("User with ID '{}' opened the page", randomId);
//
//        return new UserPrincipal(randomId);
//    }
//}
//public class UserHandshakeHandler extends DefaultHandshakeHandler {
//
//private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);
//
//
//@Override
//protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//    String userId = (String) attributes.get("USER_ID");
//
//    if (userId == null) {
//        LOG.error("User ID not found in session. Generating a random ID.");
//        userId = UUID.randomUUID().toString();
//    }
//
//    LOG.info("User with ID '{}' opened the page", userId);
//    final String finalUserId = userId; // Make userId effectively final for use in the anonymous class
//    return new Principal() {
//        @Override
//        public String getName() {
//            return finalUserId;
//        }
//    };
//}
//}

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private static final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);




@Override
protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
    String userId = (String) ((ServletServerHttpRequest) request).getServletRequest().getSession().getAttribute("USER_ID");

    if (userId == null) {
        LOG.error("User ID not found in session attributes. Generating a random ID.");
        userId = UUID.randomUUID().toString();
    } else {
        LOG.info("User ID found in session attributes: {}", userId);
    }

    final String finalUserId = userId;
    return () -> finalUserId;
}
   
}
 
