package kr.co.bookmvvm.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;

import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Books;
import kr.co.bookmvvm.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewBookViewModel extends ViewModel {
    private MutableLiveData<Books> newBooks;

    public LiveData<Books> getNewBooks() {
        if (newBooks == null) {
            newBooks = new MutableLiveData<Books>();
            loadNewBooks();
        }
        return newBooks;
    }

    private void loadNewBooks() {
        Call<Books> observ = RetrofitService.getInstance().getRetrofitRequest().getNewBooks();
        observ.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                if (response.isSuccessful()) {
                    newBooks.setValue(response.body());
                } else {
                    Log.d("net_err","get new book list err");
                }
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
