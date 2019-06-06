package kr.co.bookmvvm.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018-01-29.
 */

public class RetrofitService {
    public static RetrofitService curr = null;
    private RetrofitRequest retrofitRequest;

    public static RetrofitService getInstance() {
        if (curr == null) {
            curr = new RetrofitService();
        }

        return curr;
    }

    private RetrofitService() {
        retrofitRequest = init();
    }

    public RetrofitRequest init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.itbook.store/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitRequest.class);
    }

    public RetrofitRequest getRetrofitRequest() {
        return retrofitRequest;
    }
}
