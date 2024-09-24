package org.hidrobots.platform.iam.infrastructure.hashing.bcrypt;


import org.hidrobots.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
