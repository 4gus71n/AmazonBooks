package com.si.amazonbooks.ui.books.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.si.amazonbooks.R;
import com.si.amazonbooks.model.Book;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class BookViewHolder extends RecyclerView.ViewHolder {

    //region View variables declaration
    @BindView(R.id.view_holer_book_title)
    TextView mBookTitleTextView;
    @BindView(R.id.view_holer_book_author)
    TextView mAuthorTextView;
    @BindView(R.id.view_holder_book_image_view)
    ImageView mThumbnailImageView;
    @BindView(R.id.view_holder_container)
    View mContainer;
    //endregion

    public BookViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void loadView(Book book) {
        if (book.getTitle() != null && !TextUtils.isEmpty(book.getTitle()))
            mBookTitleTextView.setText(book.getTitle());
        if (book.getAuthor() != null && !TextUtils.isEmpty(book.getAuthor()))
            mAuthorTextView.setText(book.getAuthor());
        if (book.getImageUrl() != null && !TextUtils.isEmpty(book.getImageUrl())) {
            Glide.with(itemView.getContext()).load(book.getImageUrl()).into(mThumbnailImageView);
            mThumbnailImageView.setVisibility(View.VISIBLE);
        } else {
            mThumbnailImageView.setVisibility(View.GONE);
        }

    }

    public void cancelLoading() {
        Glide.clear(mThumbnailImageView);
    }

    public TextView getTitleTextView() {
        return mBookTitleTextView;
    }

    public TextView getAuthorTextView() {
        return mAuthorTextView;
    }

    public ImageView getImageThumbnailView() {
        return mThumbnailImageView;
    }
}
