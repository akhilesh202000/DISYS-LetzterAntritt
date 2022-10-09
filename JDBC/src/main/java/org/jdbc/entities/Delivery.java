package org.jdbc.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Delivery {

    @NonNull String name;
    @NonNull UUID uuid;
    @NonNull Status status;

}
