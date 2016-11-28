package com.si.amazonbooks.ui.books.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.si.amazonbooks.R;
import com.si.amazonbooks.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private ArrayList<Book> mBookList = new ArrayList<>();

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BookViewHolder(inflater.inflate(R.layout.view_holder_book, parent, false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.loadView(mBookList.get(position));
    }

    @Override
    public void onViewRecycled(BookViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelLoading();
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    /**
     * Loads a collection of {@link com.si.amazonbooks.model.Book} in the current adapter. Removes any old one.
     *
     * @param bookList
     */
    public void setBookList(List<Book> bookList) {
        if (bookList != null) {
            mBookList.clear();
            mBookList.addAll(bookList);
            notifyDataSetChanged();
        }
    }

}