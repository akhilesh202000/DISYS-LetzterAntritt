package org.restservice;


import org.jdbc.DBConnectionService;
import org.jdbc.dto.Letter;
import org.jdbc.dto.Package;
import org.jdbc.dto.TableData;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

@RestController
public class PostalServiceController {

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

    @GetMapping(value = "/status", produces = "application/json")
    public TableData getStatus() {
        return DBConnectionService.getTableData(conn);
    }

    //TODO:GET STATUS

}
