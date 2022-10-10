package org.jdbc.entities;

import java.util.ArrayList;

public class DeliveryData {

    public ArrayList<Letter> letters;
    public ArrayList<Package> packages;

    public DeliveryData() {
        this.letters = new ArrayList<Letter>();
        this.packages = new ArrayList<Package>();
    }

    @Override
    public String toString() {
        Letter letter;
        Package p;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < letters.size(); i++) {
            letter = letters.get(i);
            sb.append(String.format("- Letter [%d]: to %s - %s\n", i+1, letter.getName(), letter.getStatus()));
        }
        for(int i = 0; i < packages.size(); i++) {
            p = packages.get(i);
            sb.append(String.format("- Package [%d]: to %s - %s\n", i+1, p.getName(), p.getStatus()));
        }

        return sb.toString();
    }
}
