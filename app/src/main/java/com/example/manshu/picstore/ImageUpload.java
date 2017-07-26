package com.example.manshu.picstore;

/**
 * Created by manshu on 8/4/17.
 */

public class ImageUpload {

    public String name;
    public String url;
    public String email;
    public String desc;

    public String age;
    public String sex;
    public String sym1;
    public String sym2;
    public String sym3;

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getSym1() {
        return sym1;
    }

    public String getSym2() {
        return sym2;
    }

    public String getSym3() {
        return sym3;
    }
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

    public ImageUpload(String name, String url,String email,String age, String sex, String sym1) {
        this.name = name;
        this.url = url;
        this.email = email;
        this.age=age;
        this.sym1=sym1;
        this.sex = sex;

    }

    public ImageUpload(){

    }

}
