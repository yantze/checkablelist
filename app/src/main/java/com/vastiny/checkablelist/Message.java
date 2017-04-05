package com.vastiny.checkablelist;

/**
 * Package Name: com.vastiny.checkablelist
 * Author: yantze
 * Date: 4/5/17
 * Description:
 */

public class Message {

    private String message;
    private String time;

    public Message(String message, String time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
