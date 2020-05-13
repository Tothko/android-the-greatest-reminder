package com.example.thegreatestreminder.DAO;

public interface IEmailClient {
    void send(String receiver,String subject,String message);
}
