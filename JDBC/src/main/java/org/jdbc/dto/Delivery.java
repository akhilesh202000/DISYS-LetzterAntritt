package org.jdbc.dto;



import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Delivery {

    @NonNull String name;
    @NonNull UUID uuid;
    @NonNull Status status;

}
