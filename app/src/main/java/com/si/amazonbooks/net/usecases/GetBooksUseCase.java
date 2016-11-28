package com.si.amazonbooks.net.usecases;

import com.si.amazonbooks.model.Book;
import com.si.amazonbooks.repository.BookRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 27/11/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

public class GetBooksUseCase extends UseCase<List<Book>> {

    private final BookRepository bookRepository;

    public GetBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Observable<List<Book>> buildUseCaseObservable() {
        return bookRepository.getBooks();
    }
}
