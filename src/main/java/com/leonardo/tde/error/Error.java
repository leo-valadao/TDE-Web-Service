package com.leonardo.tde.error;

import java.time.Instant;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Error {

    // Objetos
    // Status
    private int status;

    // Message
    private String message;

    // Time Stamp
    private Long timestamp;

    // Path
    private String path;

    // Hash Map
    private HashMap<String, String> hashMap;

    // Construtores
    // Cosntrutor Vazio
    public Error() {
    }

    // Contrutor Com Todos os Argumentos
    public Error(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now().toEpochMilli();
        this.path = path;
    }

    // Getters e Setters
    // Status
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Time Stamp
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    // Path
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Hash Map
    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }
}
