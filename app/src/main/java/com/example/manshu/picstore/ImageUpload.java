package com.example.manshu.picstore;

/**
 * Created by manshu on 8/4/17.
 */

public class ImageUpload {

    public String name;
    public String url;
    public String email;
    public String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ImageUpload(String name, String url,String email) {
        this.name = name;
        this.url = url;
        this.email = email;
    }

    public ImageUpload(){

    }

}
