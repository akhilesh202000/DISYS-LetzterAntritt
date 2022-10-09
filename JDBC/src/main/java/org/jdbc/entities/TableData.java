package org.jdbc.entities;

import java.util.ArrayList;

public class TableData {

    public ArrayList<Letter> letters;
    public ArrayList<Package> packages;

    public TableData() {
        this.letters = new ArrayList<Letter>();
        this.packages = new ArrayList<Package>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        letters.forEach(letter ->
            sb.append(String.format("- Letter []: to %s - %s\n", letter.getName(), letter.getStatus()))
        );
        packages.forEach(p ->
                sb.append(String.format("- Package []: to %s - %s\n", p.getName(), p.getStatus()))
        );

        return sb.toString();
    }
}
