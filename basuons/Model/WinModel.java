package com.xlix.basuons.Model;

import java.util.HashMap;
import java.util.Map;

public class WinModel {

    public String moduleTitle;
    public String catTitle;
    public String bookingId;
    public String moduleId;
    public String categoryId;
    public String userId;
    public String bookingData;
    public String totalAmt;
    public String totalUnit;
    public String drawResult;
    public String resultDate;
    public String bookingType;
    public String result;
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public WinModel(String module_title,
                    String cat_title,
                    String book_id,
                    String tamt,
                    String tunit,
                    String book_data,
                    String book_date,
                    String book_type,
                    String result
    ) {
        // this.catTitle = cat_title;
        this.moduleTitle = module_title;
        this.catTitle = cat_title;
        this.bookingId = book_id;
        this.totalAmt = tamt;
        this.totalUnit = tunit;
        this.bookingData = book_data;
        this.resultDate = book_date;
        this.bookingType = book_type;
        this.result = result;

    }


    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingData() {
        return bookingData;
    }

    public void setBookingData(String bookingData) {
        this.bookingData = bookingData;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }

    public String getDrawResult() {
        return drawResult;
    }

    public void setDrawResult(String drawResult) {
        this.drawResult = drawResult;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "WinModel{" +
                "moduleTitle='" + moduleTitle + '\'' +
                ", catTitle='" + catTitle + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userId + '\'' +
                ", bookingData='" + bookingData + '\'' +
                ", totalAmt='" + totalAmt + '\'' +
                ", totalUnit='" + totalUnit + '\'' +
                ", drawResult='" + drawResult + '\'' +
                ", resultDate='" + resultDate + '\'' +
                ", bookingType='" + bookingType + '\'' +
                ", result='" + result + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}