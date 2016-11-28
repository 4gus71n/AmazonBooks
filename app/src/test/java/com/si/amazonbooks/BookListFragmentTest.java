package com.si.amazonbooks;

import android.text.TextUtils;

import com.si.amazonbooks.model.Book;
import com.si.amazonbooks.ui.books.adapter.BookViewHolder;
import com.si.amazonbooks.ui.books.view.BookListFragment;
import com.si.amazonbooks.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23, application = TestAmazonBooksApp.class)
public class BookListFragmentTest {

    public static final String TEAM_NAME = "TeamName";
    private static final String SAMPLE_MESSAGE = "SampleMessage";
    private static final CharSequence APP_TITLE = "AmazonBooksApp";

    public BookListFragment fragment;

    @Before
    public void setUp() throws Exception
    {
        fragment = BookListFragment.newInstance();
        MainActivity activity = Robolectric.buildActivity(MainActivity.class )
                .create()
                .start()
                .resume()
                .get();

        activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                fragment).commit();
    }

    /**
     * Test that the error messages are properly shown
     * @throws Exception
     */
    @Test
    public void testMessage() throws Exception {
        fragment.showError(new RuntimeException(SAMPLE_MESSAGE));
        assertTrue("The message doesn't match", fragment.getLastSnackbar().isShownOrQueued());
    }

    /**
     * Tests that if we load a {@link com.si.amazonbooks.model.Book} list, the list is properly shown.
     * @throws Exception
     */
    @Test
    public void testBookList() throws Exception {
        List<Book> booksCollection = buildMockedBooksCollection();
        fragment.loadBooksList(booksCollection);
        //Force the recycler view to render its content
        fragment.getRecyclerView().measure(0,0);
        fragment.getRecyclerView().layout(0,0,100,1000);
        //Check the adapter size collection
        assertEquals(booksCollection.size(), fragment.getAdapter().getItemCount());
        //Check each RecyclerView's items
        BookViewHolder firstBookViewHolder = (BookViewHolder) fragment.getRecyclerView().findViewHolderForAdapterPosition(0);
        assertEquals(firstBookViewHolder.getTitleTextView().getText().toString(), booksCollection.get(0).getTitle());
        assertEquals(firstBookViewHolder.getAuthorTextView().getText().toString(), booksCollection.get(0).getAuthor());
        assertEquals(firstBookViewHolder.getImageThumbnailView().isShown(), TextUtils.isEmpty(booksCollection.get(0).getImageUrl()));

        BookViewHolder secondBookViewHolder = (BookViewHolder) fragment.getRecyclerView().findViewHolderForAdapterPosition(1);
        assertEquals(secondBookViewHolder.getTitleTextView().getText().toString(), booksCollection.get(1).getTitle());
        assertEquals(secondBookViewHolder.getAuthorTextView().getText().toString(), booksCollection.get(1).getAuthor());
        assertEquals(!secondBookViewHolder.getImageThumbnailView().isShown(), TextUtils.isEmpty(booksCollection.get(1).getImageUrl()));

        BookViewHolder thirdBookViewHolder = (BookViewHolder) fragment.getRecyclerView().findViewHolderForAdapterPosition(2);
        assertEquals(thirdBookViewHolder.getTitleTextView().getText().toString(), booksCollection.get(2).getTitle());
        assertEquals(thirdBookViewHolder.getAuthorTextView().getText().toString(), booksCollection.get(2).getAuthor());
        assertEquals(!thirdBookViewHolder.getImageThumbnailView().isShown(), TextUtils.isEmpty(booksCollection.get(2).getImageUrl()));
    }

    /**
     * Test that if we load an empty {@link com.si.amazonbooks.model.Book} collection the propery empty
     * message is shown.
     * @throws Exception
     */
    @Test
    public void testEmptyBookList() throws Exception {
        fragment.loadBooksList(new ArrayList<Book>());
        //Check the adapter size collection
        assertEquals(0, fragment.getAdapter().getItemCount());
        //Check that the empty text view is displaying the proper empty message
        assertEquals(fragment.getString(R.string.empty_books_collection), fragment.getEmptyMessageTextView());
    }

    //region Mockito methods declaration
    private List<Book> buildMockedBooksCollection() {
        ArrayList<Book> result = new ArrayList<>();
        Book firstBook = buildMockedBook("Dan Brown", "Inferno", "http://t3.gstatic.com/images?q=tbn:ANd9GcS4ZWEIHjjULQDl2w6x6hWFRtjx2SQ-Yl0MPPmforNFzrLyDTxH");
        result.add(firstBook);
        Book secondBook = buildMockedBook("Dan Brown", "Da Vinci Code", null);
        result.add(secondBook);
        Book thirdBook = buildMockedBook("Dan Brown", "Angels and Demons", null);
        result.add(thirdBook);
        return result;
    }

    private Book buildMockedBook(String author, String title, String imageURL) {
        Book mockedBook = Mockito.mock(Book.class);
        Mockito.when(mockedBook.getAuthor()).thenReturn(author);
        Mockito.when(mockedBook.getTitle()).thenReturn(title);
        Mockito.when(mockedBook.getImageUrl()).thenReturn(imageURL);
        return mockedBook;
    }
    //endregion
}