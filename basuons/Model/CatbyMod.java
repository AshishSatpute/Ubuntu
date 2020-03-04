package com.xlix.basuons.Model;

import java.util.HashMap;
import java.util.Map;

public class CatbyMod {

    private String catId;
    private String moduleId;
    private String catTitle;
    private String catSlug;
    private String openTime;
    private String closeTime;
    private String type;
    private String status;
    private String createdAt;
    private Object updatedAt;
    private String todayStatus;
    private String dailyopeningno;
    private String dailyclosingno;
    private String openactive;
    private String closeactive;

    public String getOpenactive() {
        return openactive;
    }

    public void setOpenactive(String openactive) {
        this.openactive = openactive;
    }

    public String getCloseactive() {
        return closeactive;
    }

    public void setCloseactive(String closeactive) {
        this.closeactive = closeactive;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public CatbyMod(String cat_title, String open_time, String close_time,String catId,String openactive,String  closeactive) {
        this.catTitle = cat_title;
        this.openTime = open_time;
        this.closeTime = close_time;
        this.catId = catId;
        this.openactive = openactive;
        this.closeactive = closeactive;
    }



    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    public String getCatSlug() {
        return catSlug;
    }

    public void setCatSlug(String catSlug) {
        this.catSlug = catSlug;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTodayStatus() {
        return todayStatus;
    }

    public void setTodayStatus(String todayStatus) {
        this.todayStatus = todayStatus;
    }

    public String getDailyopeningno() {
        return dailyopeningno;
    }

    public void setDailyopeningno(String dailyopeningno) {
        this.dailyopeningno = dailyopeningno;
    }

    public String getDailyclosingno() {
        return dailyclosingno;
    }

    public void setDailyclosingno(String dailyclosingno) {
        this.dailyclosingno = dailyclosingno;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}