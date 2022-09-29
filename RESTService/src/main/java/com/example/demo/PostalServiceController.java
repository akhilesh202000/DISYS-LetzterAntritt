package com.example.demo;

import com.example.demo.dto.Letter;
import com.example.demo.dto.Package;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.ArrayList;

@RestController
public class PostalServiceController {

    private ArrayList<Letter> letters = new ArrayList<>();
    private ArrayList<Package> packages = new ArrayList<>();
    Connection conn = DBConnectionService.connect();

    @PostMapping(value = "/letters/{country}/{name}", produces = "application/json")
    public Letter addLetter(@PathVariable String country, @PathVariable String name) {
        Letter letter = new Letter(name, country);
        DBConnectionService.insertLetter(conn, letter);
        return letter;
    }

    @PostMapping(value = "/packages/{weight}/{name}", produces = "application/json")
    public Package addPackage(@PathVariable float weight, @PathVariable String name) {
        Package p = new Package(name, weight);
        DBConnectionService.insertPackage(conn, p);
        return p;
    }

    @GetMapping("/status")
    public void getStatus() {
        DBConnectionService.getTableData(conn);
    }

    //TODO:GET STATUS

}
