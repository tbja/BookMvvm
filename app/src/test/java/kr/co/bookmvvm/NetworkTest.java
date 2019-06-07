package kr.co.bookmvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Books;
import kr.co.bookmvvm.retrofit.RetrofitService;
import kr.co.bookmvvm.vm.BookMarkViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NetworkTest {

    @Before
    public void setUp() {
    }

    @Test
    public void getBookDataFromServer() {
        Call<Books> observe = RetrofitService.getInstance().getRetrofitRequest().getNewBooks();
        observe.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                if (response.isSuccessful()) {
                    Books books = response.body();

                    if (books.getError() == 0) {
                        assertTrue(true);
                    } else {
                        assertTrue(false);
                    }
                } else {
                    assertTrue(false);
                }
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                assertTrue(false);
            }
        });
    }
}
