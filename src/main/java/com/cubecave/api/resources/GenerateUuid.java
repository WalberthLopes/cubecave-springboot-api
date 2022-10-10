package com.cubecave.api.resources;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateUuid {

    public UUID uuid() {

        return UUID.randomUUID();
    }
}
