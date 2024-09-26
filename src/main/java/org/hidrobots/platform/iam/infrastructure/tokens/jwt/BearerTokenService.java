package org.hidrobots.platform.iam.infrastructure.tokens.jwt;


import jakarta.servlet.http.HttpServletRequest;
import org.hidrobots.platform.iam.application.internal.outboundservices.tokens.TokenService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);
    String generateToken(Authentication authentication);

}
