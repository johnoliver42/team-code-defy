package org.TeamCodeDefy.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMessage {

    @JsonProperty("message")
    private String message;
    @JsonProperty("type")
    private String type;

    public ResponseMessage(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
