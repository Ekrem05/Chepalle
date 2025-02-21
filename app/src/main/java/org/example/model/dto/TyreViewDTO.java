package org.example.model.dto;

import java.util.Set;

public record TyreViewDTO (
        String id,
        String model,
        String description,
        String family,
        String season,
        String technology,
        Set<String> sizes,
        Set<String> types
) {}
