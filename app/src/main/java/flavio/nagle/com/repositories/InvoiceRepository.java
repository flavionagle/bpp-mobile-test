package flavio.nagle.com.repositories;

import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flavio.nagle.com.model.InvoiceResponse;
import flavio.nagle.com.model.LoginResponse;
import flavio.nagle.com.retrofit.RetrofitInstance;
import flavio.nagle.com.retrofit.callbacks.OnLoginListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceRepository {

    private final AppCompatActivity appCompatActivity;

    public InvoiceRepository(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    public void login(final OnLoginListener callback){
        String string = "Br@silPP123";

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email", "waldisney@brasilprepagos.com.br");
        try {
            queryMap.put("passsord", Base64.encodeToString(string.getBytes("UTF-8"), Base64.DEFAULT));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RetrofitInstance.returnGlobalRetrofitInstance()
                .create(InvoiceRequest.class)
                .login(queryMap)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.body()!= null && "200".equals(response.body().getStatus())) {
                            callback.onSuccess(response.body());
                        }else{
                            callback.onError(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(appCompatActivity, "Um erro ocorreu", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getInvoice(){
        RetrofitInstance.returnGlobalRetrofitInstance()
                .create(InvoiceRequest.class)
                .invoice()
                .enqueue(new Callback<ArrayList<InvoiceResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<InvoiceResponse>> call, Response<ArrayList<InvoiceResponse>> response) {
                        Toast.makeText(appCompatActivity, response.body().toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<InvoiceResponse>> call, Throwable t) {
                        Toast.makeText(appCompatActivity, "Um erro ocorreu", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
