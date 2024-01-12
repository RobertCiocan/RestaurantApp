package com.gastrosfera.shared.v1.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

public class EntityAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: get user uid for current user
        return Optional.of(UUID.randomUUID().toString());
    }

}
