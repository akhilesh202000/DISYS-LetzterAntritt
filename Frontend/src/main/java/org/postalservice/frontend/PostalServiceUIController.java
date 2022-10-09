package org.postalservice.frontend;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jdbc.entities.TableData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostalServiceUIController {
    @FXML private TextField tfLetterName;
    @FXML private TextField tfLetterCountry;

    @FXML private TextField tfPackageName;
    @FXML private TextField tfPackageWeight;

    @FXML private Label statuses;

    private static final String API = "http://localhost:8080";

    @FXML
    protected void onSendLetterClick() {
        String tfName = tfLetterName.getText();
        String tfCountry = tfLetterCountry.getText();
        processInput("letters", tfCountry, tfName);
        if(tfName.isEmpty() || tfCountry.isEmpty())
            return;
        tfLetterName.setText("");
        tfLetterCountry.setText("");
    }

    @FXML
    public void onSendPackageClick() {
        String tfName = tfPackageName.getText();
        String tfWeight = tfPackageWeight.getText();
        if(tfName.isEmpty() || tfWeight.isEmpty())
            return;
        processInput("packages", tfWeight, tfName);

        tfPackageName.setText("");
        tfPackageWeight.setText("");
    }

    @FXML
    public void onRefreshClick(ActionEvent actionEvent) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API + "/status"))
                    .GET()
                    .build();

            var response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            statuses.setText(formatStatuses(response.body()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatStatuses(String body) {
        TableData td = new Gson().fromJson(body, TableData.class);
        return td.toString();
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
            e.printStackTrace();
        }
    }


    //TODO: transfer data in request body, NOT in URL
/*    private void processInput(String type, String countryOrWeight, String name) {
        HashMap<String, String> params = new HashMap<>();
        if(type.equals("letters")) {
            params.put("name", tfLetterName.getText());
            params.put("country", tfLetterCountry.getText());
        }
        else {
            params.put("name", tfPackageName.getText());
            params.put("weight", tfPackageWeight.getText());
        }

        StringBuilder requestBody = new StringBuilder();
        params.entrySet().forEach((v) -> requestBody.append(v + "\n"));

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(String.format("%s/%s", API, type)))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    //.header("accept", "application/json")
                    .build();

            var response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}