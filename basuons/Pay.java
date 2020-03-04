package com.xlix.basuons;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paykun.sdk.PaykunApiCall;
import com.paykun.sdk.eventbus.Events;
import com.paykun.sdk.eventbus.GlobalBus;
import com.paykun.sdk.helper.PaykunHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import URLhelper.BaseHelper;
import URLhelper.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pay extends AppCompatActivity {
    TextView trew, tran;
    EditText amount;
    Button pay;
    String amount_wallet = "0";
    ApiInterface apiInterface;
    private String TAG ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        findviewbyid();
        walletupdate();
        onclick();


        apiInterface = ApiClient.getClient().create(ApiInterface.class);






    }

    private void walletupdate() {

        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.getwallet, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    Log.i(TAG, "onResponse: "+response);
                    boolean status = obj.getBoolean("status");

                    if (status == true){
                        Log.i(TAG, "gtfdt"+obj.getJSONObject("data"));
                        JSONObject object = obj.getJSONObject("data");
                        String Wallet_Ba = object.getString("wallet_amount");
                        Log.i(TAG, "Wallet_Ba"+Wallet_Ba);
                        trew.setText(Wallet_Ba);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", SharedPref.getString(getBaseContext(),"user_id"));

                Log.d("TAG", "Error = " + params.toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }

    private void onclick() {
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = SharedPref.getString(getBaseContext(),"full_name");
                String[] s = a.split("\\s+");

                String accessTokenSandbox = "66691346F43D0D42F7CCFB825D08EA8A";
                String merchantIdSandbox = "569718085254536";
                String productName = "Pay Service Amount";

                amount_wallet = amount.getText().toString().trim();


                JSONObject object = new JSONObject();
                try {
                    object.put("merchant_id", merchantIdSandbox);
                    object.put("access_token", accessTokenSandbox);
                    object.put("customer_name",  ""+s[0]);
                    object.put("customer_email", SharedPref.getString(getBaseContext(),"email"));
                    object.put("customer_phone", SharedPref.getString(getBaseContext(),"mobile"));
                    object.put("product_name", productName);
                    object.put("order_no", System.currentTimeMillis()); // order no. should have 10 to 30 character in numeric format
                    object.put("amount", amount.getText().toString());  // minimum amount should be 10
                    object.put("isLive", true); // need to send false if you are in sandbox mode
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new PaykunApiCall.Builder(Pay.this).sendJsonObject(object); // Paykun api to initialize your payment and send info.
            }
        });
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getResults(Events.PaymentMessage message) {
        if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SUCCESS)){
            // do your stuff here
            // message.getTransactionId() will return your failed or succeed transaction id
            /* if you want to get your transaction detail call message.getTransactionDetail()
             *  getTransactionDetail return all the field from server and you can use it here as per your need
             *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId */
            if(!TextUtils.isEmpty(message.getTransactionId())) {
                Toast.makeText(Pay.this, "Your Transaction is succeed with transaction id : "+message.getTransactionId() , Toast.LENGTH_SHORT).show();
                Log.v(" order id "," getting order id value : "+message.getTransactionDetail().order.orderId);
                String trxId =message.getTransactionId();
                tran.setText(trxId);
                sendWalltedData(SharedPref.getString(getBaseContext(),"user_id"),amount_wallet,trxId);
            }
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_FAILED)){
            // do your stuff here
            Toast.makeText(Pay.this,"Your Transaction is failed",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SERVER_ISSUE)){
            // do your stuff here
            Toast.makeText(Pay.this,PaykunHelper.MESSAGE_SERVER_ISSUE,Toast.LENGTH_SHORT).show();
        }else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING)){
            // do your stuff here
            Toast.makeText(Pay.this,"Access Token missing",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_MERCHANT_ID_MISSING)){
            // do your stuff here
            Toast.makeText(Pay.this,"Merchant Id is missing",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_INVALID_REQUEST)){
            Toast.makeText(Pay.this,"Invalid Request",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE)){
            Toast.makeText(Pay.this,"Network is not available",Toast.LENGTH_SHORT).show();
        }
    }

    public void findviewbyid() {
        trew =(TextView) findViewById(R.id.tree1);
        amount = findViewById(R.id.tree2);
        pay = findViewById(R.id.paynow);
        tran = findViewById(R.id.trand);

    }

    private void sendWalltedData(String user_id, String amount_wallet, String trxId) {
        Call<Walleted_Response> walletedResponseCall = apiInterface.walletred(user_id,amount_wallet,trxId);
        walletedResponseCall.enqueue(new Callback<Walleted_Response>() {
            @Override
            public void onResponse(Call<Walleted_Response> call, Response<Walleted_Response> response) {
                Walleted_Response walleted_response = response.body();
                String status = walleted_response.getStatus();
                String msg = walleted_response.getMsg();
                Intent intent = new Intent(Pay.this,MainActivity.class);
                startActivity(intent);


                Toast.makeText(getApplicationContext(),"ME Success"+msg,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Walleted_Response> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register this activity to listen to event.
        GlobalBus.getBus().register(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Unregister from activity
        GlobalBus.getBus().unregister(this);
    }


}
