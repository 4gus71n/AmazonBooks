package com.si.amazonbooks.ui.books.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.si.amazonbooks.AmazonBooksApp;
import com.si.amazonbooks.R;
import com.si.amazonbooks.model.Book;
import com.si.amazonbooks.ui.books.adapter.BookAdapter;
import com.si.amazonbooks.ui.books.presenter.BookListPresenter;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class BookListFragment extends LightCycleSupportFragment<BookListFragment> implements
        BookListPresenter.View, SwipeRefreshLayout.OnRefreshListener {

    //region Views declaration
    @BindView(R.id.books_fragment_recycler_empty_text_view)
    TextView mEmptyTextView;
    @BindView(R.id.books_fragment_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.books_fragment_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    //endregion

    //region Variables declaration
    private Snackbar mLastShownSnackbar;
    private BookAdapter mBookAdapter;
    @Inject @LightCycle
    BookListPresenter bookListPresenter;
    //endregion

    //region Getter and Setters declaration
    public BookAdapter getAdapter() {
        return mBookAdapter;
    }

    public Snackbar getLastSnackbar() {
        return mLastShownSnackbar;
    }

    public RecyclerView gerRecyclerView() {
        return mRecyclerView;
    }
    //endregion

    //region BookListPresenter.View implementation
    @Override
    public void showError(Throwable e) {
        mLastShownSnackbar = Snackbar.make(getView(), e.getLocalizedMessage(), Snackbar.LENGTH_LONG);
        mLastShownSnackbar.show();
    }

    @Override
    public void loadBooksList(List<Book> bookList) {
        mEmptyTextView.setVisibility((!bookList.isEmpty()) ? View.GONE : View.VISIBLE);
        mBookAdapter.setBookList(bookList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    //endregion

    //region Constructors declarations
    public BookListFragment() {
        AmazonBooksApp.getObjectGraph().inject(this);
    }

    public static BookListFragment newInstance() {
        return new BookListFragment();
    }
    //endregion

    //region Fragment's lifecycle
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mBookAdapter = new BookAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mBookAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //This is the only way to show the spinner as soon as the activity/fragment starts
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        //We request the initial list
        bookListPresenter.onLoadBookList();
    }
    //endregion

    //region SwipeRefreshLayout.OnRefreshListener implementation
    @Override
    public void onRefresh() {
        bookListPresenter.onLoadBookList();
    }

    public TextView getEmptyMessageTextView() {
        return mEmptyTextView;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    //endregion

}
