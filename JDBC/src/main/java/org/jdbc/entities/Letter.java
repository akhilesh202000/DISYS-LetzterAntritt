package org.jdbc.entities;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class Letter extends Delivery {
    @NonNull String country;

    @Override
    public String toString() {
        return "Letter{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", uuid=" + uuid +
                ", status=" + status +
                '}';
    }

    public Letter(@NonNull String name, @NonNull String country, @NonNull UUID uuid, @NonNull Status status) {
        super(name, uuid, status);
        this.country = country;
    }
}
