package org.jdbc.dto;

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
            sb.append(String.format("- Letter [%s]: to %s - %s\n", letter.getUuid(), letter.getName(), letter.getStatus()))
        );
        packages.forEach(p ->
                sb.append(String.format("- Package [%s]: to %s - %s\n", p.getUuid(), p.getName(), p.getStatus()))
        );

        return sb.toString();
    }
}
