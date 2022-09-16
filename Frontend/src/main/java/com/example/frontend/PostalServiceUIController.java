package com.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostalServiceUIController {
    @FXML private Label label;
    @FXML private TextField tfLetterName;
    @FXML private TextField tfLetterCountry;

    @FXML private TextField tfPackageName;
    @FXML private TextField tfPackageWeight;

    //TODO:Change URL
    private static final String API = "http://localhost:8080/invoices";

    @FXML
    protected void onSendLetterClick() {
        String tfName = tfLetterName.getText();
        String tfCountry = tfLetterCountry.getText();
        processInput("letters", tfCountry, tfName);
    }

    public void onSendPackageClick() {
        String tfName = tfPackageName.getText();
        String tfWeight = tfPackageWeight.getText();
        processInput("packages", tfWeight, tfName);
    }

    public void onRefreshClick(ActionEvent actionEvent) {
    }

    private void processInput(String type, String countryOrWeight, String name) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(String.format("%s/%s/%s/%s", API, type, countryOrWeight, name)))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Request failed");
            System.out.println(e);
        }
    }
}