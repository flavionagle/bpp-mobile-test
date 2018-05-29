package flavio.nagle.com.repositories;

import java.util.ArrayList;

import flavio.nagle.com.Constants;
import flavio.nagle.com.model.InvoiceResponse;
import flavio.nagle.com.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InvoiceRequest {

    @POST(Constants.LOGIN)
    @FormUrlEncoded
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password);
//            @QueryMap Map<String, String> queryMap);

    @GET(Constants.INVOICE)
    Call<ArrayList<InvoiceResponse>> invoice();
}
