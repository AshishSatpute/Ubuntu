package com.xlix.basuons;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface ApiInterface {
    @FormUrlEncoded
    @POST("api/User/addMoneyToWallet")
    Call<Walleted_Response> walletred(@Field("user_id") String user_id, @Field("wallet_amount") String wallet_amount, @Field("trxId") String trxId);


}
