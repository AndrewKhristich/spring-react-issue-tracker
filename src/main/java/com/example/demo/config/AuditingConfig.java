package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.utils.SecurityUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }
}

class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication auth = SecurityUtil.getAuth();

        if (SecurityUtil.isAuthEmpty(auth)) {
            return Optional.empty();
        }

        User principal = (User) auth.getPrincipal();

        return Optional.ofNullable(principal.getId());
    }
}
