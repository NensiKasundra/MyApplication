package example.com.myapplication;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by kasundne on 9/21/2017.
 */

public interface APIInterface {


    @POST("User/SignIn")
    Call<LoginData> loginUser(@Body JsonObject jsonObject);

    @POST("Authentication")
    Call<LoginResponse> login(@Body JsonObject jsonObject);

}
