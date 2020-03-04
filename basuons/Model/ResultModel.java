package com.xlix.basuons.Model;

import java.util.HashMap;
import java.util.Map;

public class ResultModel {
    private String catTitle;
        private String bookingId;
        private String moduleId;
        private String categoryId;
        private String userId;
        private String bookingData;
        private String totalAmt;
        private String totalUnit;
        private String bookingStatus;
        private String bookingType;
        private String drawResult;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public ResultModel(String cat_title, String booking_status, String draw_result,String bookingData) {
            this.catTitle = cat_title;
            this.bookingStatus = booking_status;
            this.drawResult = draw_result;
            this.bookingData = bookingData;
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

        public String getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(String bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public String getBookingType() {
            return bookingType;
        }

        public void setBookingType(String bookingType) {
            this.bookingType = bookingType;
        }

        public String getDrawResult() {
            return drawResult;
        }

        public void setDrawResult(String drawResult) {
            this.drawResult = drawResult;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }