package com.example.demo;

import com.example.demo.dto.Letter;
import com.example.demo.dto.Package;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PostalServiceController {

    private ArrayList<Letter> letters = new ArrayList<>();
    private ArrayList<Package> packages = new ArrayList<>();

    @PostMapping(value = "/letters/{country}/{name}", produces = "application/json")
    public Letter addLetter(@PathVariable String country, @PathVariable String name) {
        Letter letter = new Letter(name, country);
        letters.add(letter);
        return letter;
    }

    @PostMapping(value = "/packages/{weight}/{name}", produces = "application/json")
    public Package addPackage(@PathVariable float weight, @PathVariable String name) {
        Package p = new Package(name, weight);
        packages.add(p);
        return p;
    }

    @GetMapping("/letters")
    public ArrayList<Letter> getLetters() {
        return letters;
    }

    @GetMapping("/packages")
    public ArrayList<Package> getPackages() {
        return packages;
    }

    //TODO:GET STATUS

}
