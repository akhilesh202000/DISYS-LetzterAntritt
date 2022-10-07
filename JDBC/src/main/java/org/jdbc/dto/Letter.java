package org.jdbc.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
public class Letter extends Delivery {
    @NonNull String country;


    public Letter(@NonNull String name, @NonNull String country, @NonNull UUID uuid, @NonNull Status status) {
        super(name, uuid, status);
        this.country = country;
    }
}
