package com.example.noteme.DataModel;

/**
 * Created by ashish on 9/11/17.
 */

public class AddDataModel {

    private String id;
    private String title;
    private String desc;

    public AddDataModel(String title, String desc){

    }

    public AddDataModel(String title, String desc,String id) {
        this.title = title;
        this.desc = desc;
        this.id= id;
    }

    @Override
    public String toString() {
        return "AddDataModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
