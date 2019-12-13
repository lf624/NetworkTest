package com.example.networktext;

public class Text {
    private String name;
    private String content;
    private String web;

    public Text(String name, String content, String web) {
        this.name = name;
        this.content = content;
        this.web = web;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getWeb() {
        return web;
    }
}
