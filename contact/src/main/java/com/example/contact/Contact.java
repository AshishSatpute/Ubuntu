
package com.example.contact;

import com.squareup.moshi.Json;

public class Contact {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "email")
    private String email;
    @Json(name = "address")
    private String address;
    @Json(name = "gender")
    private String gender;
    @Json(name = "profile_pic")
    private String profilePic;
    @Json(name = "phone")
    private Phone phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

}
