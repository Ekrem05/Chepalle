package org.example.model.dto;

import java.util.Set;

public record TyreCreationDTO(
        String model,
        String description,
        Long price,
        Integer familyId,
        Integer seasonId,
        Integer technologyId,
        Set<Integer> sizeIds,
        Set<Integer> typeIds
) {}
