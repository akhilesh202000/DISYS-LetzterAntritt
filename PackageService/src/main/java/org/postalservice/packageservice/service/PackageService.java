package org.postalservice.packageservice.service;

import lombok.RequiredArgsConstructor;
import org.jdbc.DBConnectionService;
import org.jdbc.entities.Package;
import org.jdbc.entities.Status;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.sql.Connection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackageService implements MessageListener {

    Connection conn = DBConnectionService.connect();

    @Override
    @JmsListener(destination = "${activemq.packagequeue}")
    public void onMessage(Message message) {
        try{
            UUID uuid = UUID.fromString(((TextMessage) message).getText());
            Package Package = DBConnectionService.getPackageById(conn, uuid);
            Status newStatus = isSendable(Package) ? Status.SENT : Status.REJECTED;
            DBConnectionService.updateStatus(conn, "packages", newStatus, uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isSendable(Package p) {
        return p.getWeight() < 25;
    }

}
