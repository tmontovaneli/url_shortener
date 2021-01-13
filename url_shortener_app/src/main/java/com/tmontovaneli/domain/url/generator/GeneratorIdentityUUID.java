package com.tmontovaneli.domain.url.generator;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class GeneratorIdentityUUID implements IGenerateIdentity {

    @Override
    public String generateId(String url) {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf("-"));
    }

}
