package org.postalservice.letterservice.service;

import lombok.RequiredArgsConstructor;
import org.jdbc.DBConnectionService;
import org.jdbc.entities.Letter;
import org.jdbc.entities.Status;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LetterService implements MessageListener {

    Connection conn = DBConnectionService.connect();

    private List<String> acceptableCountryCodes = new ArrayList<>(List.of("AT", "DE", "CH"));

    @Override
    @JmsListener(destination = "${activemq.letterqueue}")
    public void onMessage(Message message) {
        try{
            UUID uuid = UUID.fromString(((TextMessage) message).getText());
            Letter letter = DBConnectionService.getLetterById(conn, uuid);
            Status newStatus = isSendable(letter) ? Status.SENT : Status.REJECTED;
            DBConnectionService.updateStatus(conn, "letters", newStatus, uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isSendable(Letter letter) {
        return acceptableCountryCodes.stream().anyMatch(country -> country.equalsIgnoreCase(letter.getCountry()));
    }

}
