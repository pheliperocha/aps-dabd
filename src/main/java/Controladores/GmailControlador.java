package Controladores;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GmailControlador {
    private static final String APPLICATION_NAME =
        "Gmail API Java Quickstart";
    
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/gmail-java-quickstart");
    
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;
    
    private static String USER = "me";
    
    private Gmail service;
    private List<Message> emails;

    private static final List<String> SCOPES =
        Arrays.asList(GmailScopes.GMAIL_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
    
    public static Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    public static List<Message> listMessages(Gmail service, String userId) throws IOException {
        ListMessagesResponse response = service.users().messages().list(userId).execute();
        
        List<Message> messages = new ArrayList<Message>();
        messages.addAll(response.getMessages());

        return messages;
    }
    
    public static Credential authorize() throws IOException {
        InputStream in =
            GmailControlador.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    public GmailControlador() {
        
        try {
            service = getGmailService();
            emails = listMessages(service, USER);
        } catch (IOException ex) {
            Logger.getLogger(GmailControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Gmail getService() {
        return service;
    }

    public List<Message> getEmails() {
        return emails;
    }
    
}
