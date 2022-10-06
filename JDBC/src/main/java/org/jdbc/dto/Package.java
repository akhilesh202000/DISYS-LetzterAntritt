package org.jdbc.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Package {

    @NonNull private String name;
    @NonNull private float weight;
}
