package com.example.demo.dto;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

public class TableData {

    public ArrayList<Letter> letters;
    public ArrayList<Package> packages;

    public TableData() {
        this.letters = new ArrayList<Letter>();
        this.packages = new ArrayList<Package>();
    }
}
