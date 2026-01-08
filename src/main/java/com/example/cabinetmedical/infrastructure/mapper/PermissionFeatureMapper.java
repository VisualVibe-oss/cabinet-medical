package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public final class PermissionFeatureMapper {

    private static final Map<PermissionKey, Featurekey> PERMISSION_TO_FEATURE = Map.of(
            PermissionKey.CREE_RENDEZ_VOUS, Featurekey.CREE_RENDEZ_VOUS
    );
    public static List<PermissionKey> permissionsAllowedByFeatures(
            List<Featurekey> enabledFeatures
    ) {
        return PERMISSION_TO_FEATURE.entrySet()
                .stream()
                .filter(entry -> enabledFeatures.contains(entry.getValue()))
                .map(Map.Entry::getKey)
                .toList(); // Java 16+
    }



    private PermissionFeatureMapper() {
    }
}