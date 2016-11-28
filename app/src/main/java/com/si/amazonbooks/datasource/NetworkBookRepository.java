package com.si.amazonbooks.datasource;

import com.si.amazonbooks.model.Book;
import com.si.amazonbooks.net.rest.ServiceRestApi;
import com.si.amazonbooks.repository.BookRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 27/11/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

public class NetworkBookRepository implements BookRepository {

    private final ServiceRestApi serviceRestApi;

    public NetworkBookRepository(ServiceRestApi serviceRestApi) {
        this.serviceRestApi = serviceRestApi;
    }

    @Override
    public Observable<List<Book>> getBooks() {
        return serviceRestApi.getBooks();
    }
}
