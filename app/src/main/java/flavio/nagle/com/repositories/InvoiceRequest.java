package flavio.nagle.com.repositories;

import java.util.ArrayList;
import java.util.Map;

import flavio.nagle.com.Constants;
import flavio.nagle.com.model.InvoiceResponse;
import flavio.nagle.com.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface InvoiceRequest {

    @POST(Constants.LOGIN)
    Call<LoginResponse> login(@QueryMap Map<String, String> queryMap);

    @GET(Constants.INVOICE)
    Call<ArrayList<InvoiceResponse>> invoice();
}
