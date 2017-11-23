
package com.example.contact;

import java.util.List;
import com.squareup.moshi.Json;

public class ContactList {

    @Json(name = "contacts")
    private List<Contact> contacts = null;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
