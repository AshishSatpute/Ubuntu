package com.example.contact;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ashish on 23/11/17.
 */

public interface ApiService {
    /*
   Retrofit get annotation with our URL
   And our method that will return us the List of ContactList
   */
    @GET("/json_data.json")
    Call<ContactList> getMyJSON();
}
