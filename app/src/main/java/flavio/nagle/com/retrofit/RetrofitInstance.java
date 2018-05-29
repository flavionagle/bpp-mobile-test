package flavio.nagle.com.retrofit;

import flavio.nagle.com.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit returnGlobalRetrofitInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(Constants.BASE_URL).build();
    }
}
