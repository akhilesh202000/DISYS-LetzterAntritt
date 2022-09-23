package com.example.demo.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class Letter {

    @NonNull private String name;
    @NonNull private String country;

}
