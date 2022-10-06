package org.jdbc.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Letter {

    @NonNull private String name;
    @NonNull private String country;

}