package example.com.myapplication;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kasundne on 9/21/2017.
 */

public class ApiClient {


    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MILLISECONDS)
                .writeTimeout(10, TimeUnit.MILLISECONDS)
                .readTimeout(10, TimeUnit.MILLISECONDS).addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl("http://202.191.196.210/UAT/IPO/CSMAppServices/CSMAppService/GaragePortalService.svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
