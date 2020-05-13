package com.example.thegreatestreminder.DAO.Impl;

import com.example.thegreatestreminder.DAO.IEmailClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MailjetEmailClient implements IEmailClient {

    private MailjetClient client;

    public MailjetEmailClient(){
        this.client = new MailjetClient(
                "AskMeForApiKey",
                "AskMeForApiSecret",
                new ClientOptions("v3.1"));
    }

    @Override
    public void send(String receiver,String subject, String message) {

        Thread thread = new Thread(() -> {
            MailjetRequest request;
            MailjetResponse response;

            try {
                request = new MailjetRequest(Emailv31.resource)
                        .property(Emailv31.MESSAGES, new JSONArray()
                                .put(new JSONObject()
                                        .put(Emailv31.Message.FROM, new JSONObject()
                                                .put("Email", "marekst11@gmail.com")
                                                .put("Name", "TheGreatestReminder"))
                                        .put(Emailv31.Message.TO, new JSONArray()
                                                .put(new JSONObject()
                                                        .put("Email", receiver)
                                                        .put("Name", "Dearest Customer")))
                                        .put(Emailv31.Message.SUBJECT, subject)
                                        .put(Emailv31.Message.TEXTPART, message)
                                        .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
                response = client.post(request);
                System.out.println(response.getStatus());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MailjetSocketTimeoutException e) {
                e.printStackTrace();
            } catch (MailjetException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }
}
