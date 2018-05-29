package flavio.nagle.com.repositories;

import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Toast;

import java.util.ArrayList;

import flavio.nagle.com.model.InvoiceResponse;
import flavio.nagle.com.model.LoginResponse;
import flavio.nagle.com.retrofit.RetrofitInstance;
import flavio.nagle.com.retrofit.callbacks.OnInvoice;
import flavio.nagle.com.retrofit.callbacks.OnLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceRepository {

    private static final String SUCCESS = "200";
    private final AppCompatActivity appCompatActivity;

    public InvoiceRepository(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    public void login(String email, String password, final OnLogin callback){
        password = Base64.encodeToString(password.getBytes(), Base64.NO_WRAP);
        RetrofitInstance.returnGlobalRetrofitInstance()
                .create(InvoiceRequest.class)
                .login(email, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.body()!= null && SUCCESS.equals(response.body().getCode())) {
                            callback.onSuccess(response.body());
                        }else{
                            callback.onError(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        LoginResponse loginResponse = new LoginResponse();
                        loginResponse.setMessage("Um erro ocorreu");
                        callback.onError(loginResponse);
                    }
                });
    }

    public void getInvoice(final OnInvoice callback){
        RetrofitInstance.returnGlobalRetrofitInstance()
                .create(InvoiceRequest.class)
                .invoice()
                .enqueue(new Callback<ArrayList<InvoiceResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<InvoiceResponse>> call, Response<ArrayList<InvoiceResponse>> response) {
                        callback.onResponse(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<InvoiceResponse>> call, Throwable t) {
                        Toast.makeText(appCompatActivity, "Um erro ocorreu", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
