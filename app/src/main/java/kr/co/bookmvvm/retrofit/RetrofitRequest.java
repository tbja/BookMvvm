package kr.co.bookmvvm.retrofit;

import java.util.ArrayList;

import kr.co.bookmvvm.model.Books;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018-01-29.
 */

public interface RetrofitRequest {

    @GET("new")
    Call<Books> getNewBooks();

    @GET("search/{query}/{page}")
    Call<Books> getSearchBooksList(@Path("query") String search, @Path("page") Integer page);
}
