package com.si.amazonbooks.di;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * <ul>
 *  <li>
 *      This module depends on {@link com.si.amazonbooks.di.ApplicationModule}
 *  </li>
 *  <li>
 *      This module provides a {@link com.si.amazonbooks.net.usecases.GetBooksUseCase} to be use in any
 *      presenter.
 *  </li>
 * </ul>
 *
 */

import com.si.amazonbooks.datasource.NetworkBookRepository;
import com.si.amazonbooks.net.rest.ServiceRestApi;
import com.si.amazonbooks.net.services.RetrofitAmazonService;
import com.si.amazonbooks.net.usecases.GetBooksUseCase;
import com.si.amazonbooks.ui.books.adapter.BookAdapter;
import com.si.amazonbooks.ui.books.presenter.BookListPresenter;
import com.si.amazonbooks.ui.books.view.BookListFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class,
        injects = {BookListFragment.class, BookListPresenter.class, BookAdapter.class})
public class UseCaseModule {

    @Provides
    GetBooksUseCase provideGetPlayerUseCase(RetrofitAmazonService retrofitAmazonService) {
        return new GetBooksUseCase(new NetworkBookRepository(new ServiceRestApi(retrofitAmazonService)));
    }

}
