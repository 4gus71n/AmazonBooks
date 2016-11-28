package com.si.amazonbooks.net.services;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

import com.si.amazonbooks.model.Book;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitAmazonService {
    @GET("books.json")
    Observable<List<Book>> getBooks();
}
