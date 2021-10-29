package dev.masters.oauth;

import dev.masters.entites.Roles;
import dev.masters.entites.User;
import dev.masters.services.ServiceUser;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Created by max on 13/07/2017.
 */
public abstract class OAuthAuthenticator {

    private JSONObject accessedJsonData;

    private boolean gotData = false;
    private boolean attemptRecieved = false;
    private boolean loginAttempted = false;

    private String accessToken;
    private String accessCode;

    private String clientID;
    private String redirectUri;
    private String clientSecret;
    public JSONObject returnedJSON;
    ServiceUser su=new ServiceUser();

    private Stage stage;


    public OAuthAuthenticator (String clientID, String redirectUri, String clientSecret) {
        this.clientID = clientID;
        this.redirectUri = redirectUri;
        this.clientSecret = clientSecret;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri(){
        return redirectUri;
    }

    public void startLogin(Event e) {

        if(loginAttempted) {
            return;
        }
        loginAttempted = true;
        stage = new Stage();
        WebView root = new WebView();
        WebEngine engine = root.getEngine();

        engine.load(getWebUrl());

        engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
            public void handle(WebEvent<String> event) {

                if(gotData || attemptRecieved) {
                    return;
                }
                if (event.getSource() instanceof WebEngine) {
                    WebEngine we = (WebEngine) event.getSource();
                    String location = we.getLocation();
                    if (location.contains("code") && location.startsWith(getRedirectUri())) {

                        attemptRecieved = true;

                        closeStage();

                        accessCode = location.substring(location.indexOf("code=") + 5);

                        accessToken = doGetAccessTokenRequest(accessCode);
                     

                         returnedJSON = doGetAccountInfo(accessToken);
                         System.out.println(returnedJSON.length());
                         System.out.println(returnedJSON);

                        accessedJsonData = new JSONObject(returnedJSON);
                        

                        gotData = true;
                        notifyLoginViewCompleted(e);

                    }
                }
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

    }

    abstract String getWebUrl();

    abstract String getApiTokenUrl();

    abstract String getApiAccessUrl();

    abstract String getApiAccessParams();

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public boolean hasFinishedSuccessfully() {
        return gotData;
    }

    public JSONObject getJsonData() {
        if(gotData) {
            return accessedJsonData;
        } else {
            return null;

        }
    }

    private void closeStage() {
        stage.close();
    }

    private void notifyLoginViewCompleted(Event event) {
        if(gotData) {
            if(returnedJSON.length()==7){
                User u;
                u = new User();
                u.setFirst_name(returnedJSON.get("first_name").toString());
                u.setLast_name(returnedJSON.get("last_name").toString());
                u.setEmail(returnedJSON.get("email").toString());
                u.setUsername(returnedJSON.get("name").toString());

                String str =returnedJSON.get("birthday").toString().replace("/", "-").substring(6, 10)+"-"+returnedJSON.get("birthday").toString().replace("/", "-").substring(0, 2)+returnedJSON.get("birthday").toString().replace("/", "-").substring(2, 5)+" 00:00";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                u.setBirthday(dateTime);
                u.setRole(Roles.CLIENT);
                su.ajouter(u);
            }
            Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/FXMLGSTuser.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent)); 
            stage.show();
        }
    }

    private JSONObject doGetAccountInfo(String accessToken) {
        try {
            HttpURLConnection connection2 = null;
            URL url2 = new URL(getApiTokenUrl());
            connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setRequestProperty("User-Agent", "Mozilla/5.0");

            connection2.setDoInput(true);
            connection2.setDoOutput(true);

            System.out.println("URL: " + getApiTokenUrl());

            int reponseCode2 = connection2.getResponseCode();

            if (reponseCode2 == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in2 = new BufferedReader(new InputStreamReader(
                        connection2.getInputStream()));
                String inputLine2;
                
                StringBuffer response2 = new StringBuffer();

                while ((inputLine2 = in2.readLine()) != null) {
                    response2.append(inputLine2);
                }
                in2.close();
                connection2.disconnect();
                
                
                
                return new JSONObject(response2.toString());
            } else {
                System.out.println("Error retrieving api data!: " + reponseCode2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("####### ERROR GETTING ACCOUNT INFO ##############");
        }
        return null;
    }

    private String doGetAccessTokenRequest(String code) {
        try {
            URL url = new URL(getApiAccessUrl());
            String urlParams = getApiAccessParams();

            System.out.println("URL: " + getApiAccessUrl());
            System.out.println("PARAMS: " + urlParams);

            byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + postDataLength);
            connection.setRequestProperty("Connection", "keep-alive");
            //connection.setRequestProperty("Referer", "https://accounts.google.com/o/oauth2/token");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postData);

            wr.flush();

            int responseCode = connection.getResponseCode();

            System.out.println(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success

            } else {
                System.err.println("Error getting access token for OAuth Login!");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();
            String fullResponse = response.toString();

            JSONObject json = new JSONObject(fullResponse);

            String accessToken = json.getString("access_token");

            System.out.println(fullResponse);

            System.out.println("ACCESS TOKEN: " + accessToken);

            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
