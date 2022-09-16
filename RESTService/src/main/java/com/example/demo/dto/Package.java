package com.example.demo.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Package {

    @NonNull private String name;
    @NonNull private float weight;
}
