package com.moster.AT.exception;

import java.time.LocalDateTime;

public class ResponsePayload {

    String mensagem;
    LocalDateTime dateTime;



    public ResponsePayload(String mensagem) {

        this.mensagem = mensagem;
        this.dateTime = LocalDateTime.now();
    }
}
