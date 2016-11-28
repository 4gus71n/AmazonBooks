package com.si.amazonbooks.net.rest;

import com.si.amazonbooks.model.Book;
import com.si.amazonbooks.net.services.RetrofitAmazonService;

import java.util.List;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * Concrete implementation of the {@link RestApi} interface. The network layer implementation,
 * in this class is where we call the Retrofit services.
 * Commonly known as "RestApiImpl", but since I don't like using "Impl" as suffix I change It to this.
 * {@see http://stackoverflow.com/a/2814831/1403997}
 */
public class ServiceRestApi implements RestApi {
    private RetrofitAmazonService retrofitAmazonService;

    public ServiceRestApi(RetrofitAmazonService retrofitAmazonService) {
        this.retrofitAmazonService = retrofitAmazonService;
    }

    @Override
    public Observable<List<Book>> getBooks() {
        return retrofitAmazonService.getBooks();
    }
}
