
package com.example.contact;

import com.squareup.moshi.Json;

public class Phone {

    @Json(name = "mobile")
    private String mobile;
    @Json(name = "home")
    private String home;
    @Json(name = "office")
    private String office;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

}
