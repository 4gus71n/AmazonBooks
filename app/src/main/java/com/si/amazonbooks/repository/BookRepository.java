package com.si.amazonbooks.repository;

import com.si.amazonbooks.model.Book;

import java.util.List;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 27/11/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

public interface BookRepository {

    Observable<List<Book>> getBooks();

}
