package org.jdbc.entities;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class Package extends Delivery {

    @NonNull float weight;

    @Override
    public String toString() {
        return "Package{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", uuid=" + uuid +
                ", status=" + status +
                '}';
    }

    public Package(@NonNull String name, @NonNull float weight, @NonNull UUID uuid, @NonNull Status status) {
        super(name, uuid, status);
        this.weight = weight;
    }
}
