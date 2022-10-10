package org.postalservice.restapi;


import org.jdbc.DBConnectionService;
import org.jdbc.entities.Letter;
import org.jdbc.entities.Package;
import org.jdbc.entities.Status;
import org.jdbc.entities.DeliveryData;
import org.postalservice.restapi.queue.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.UUID;

@RestController
public class PostalServiceController {

    Connection conn = DBConnectionService.connect();

    @Autowired
    Producer producer;

    @PostMapping(value = "/letters/{country}/{name}", produces = "application/json")
    public Letter addLetter(@PathVariable String country, @PathVariable String name) {
        Letter letter = new Letter(name, country, UUID.randomUUID(), Status.WAITING);
        try {
            DBConnectionService.insertLetter(conn, letter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        producer.send(letter);
        return letter;
    }

    @PostMapping(value = "/packages/{weight}/{name}", produces = "application/json")
    public Package addPackage(@PathVariable float weight, @PathVariable String name) {
        Package p = new Package(name, weight, UUID.randomUUID(), Status.WAITING);
        DBConnectionService.insertPackage(conn, p);
        producer.send(p);
        return p;
    }

//    @GetMapping(value = "/status", produces = "application/json")
//    public void getStatus() {
//
//        Package l = DBConnectionService.getPackageById(conn, UUID.fromString("9e04335f-96eb-4a02-a1d4-cce85d443744"));
//        System.out.println(l);
//    }

    @GetMapping(value = "/status", produces = "application/json")
    public DeliveryData getStatus() {

        return DBConnectionService.getTableData(conn);
    }


}
