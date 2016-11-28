package com.si.amazonbooks.ui.books.presenter;

import android.os.Bundle;

import com.si.amazonbooks.AmazonBooksApp;
import com.si.amazonbooks.model.Book;
import com.si.amazonbooks.net.usecases.GetBooksUseCase;
import com.si.amazonbooks.ui.books.view.BookListFragment;
import com.si.amazonbooks.utils.DefaultSubscriber;
import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Agustin Tomas Larghi on 30/07/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class BookListPresenter extends DefaultSupportFragmentLightCycle<BookListFragment> {

    //region Variables declaration
    @Inject
    GetBooksUseCase getBooksUseCase;
    BookListPresenter.View view;
    //endregion

    @Override
    public void onCreate(BookListFragment fragment, Bundle bundle) {
        super.onCreate(fragment, bundle);
        AmazonBooksApp.getObjectGraph().inject(this);
    }

    @Override
    public void onResume(BookListFragment fragment) {
        super.onResume(fragment);
        this.view = fragment;

    }

    @Override
    public void onDestroy(BookListFragment fragment) {
        super.onDestroy(fragment);
        this.view = null;
        getBooksUseCase.unsubscribe();
    }

    public void onLoadBookList() {
        getBooksUseCase.execute(new DefaultSubscriber<List<Book>>() {
            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }

            @Override
            public void onNext(List<Book> books) {
                view.loadBooksList(books);
            }
        });

    }

    //region Presenter-View Interface
    public interface View {

        /**
         * Shows an error in a SnackBar
         * @param e
         */
        void showError(Throwable e);

        /**
         * Triggered once we got the {@link Book} list
         * @param bookList
         */
        void loadBooksList(List<Book> bookList);
    }
    //endregion
}
