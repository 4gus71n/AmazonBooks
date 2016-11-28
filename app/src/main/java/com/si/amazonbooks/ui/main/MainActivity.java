package com.si.amazonbooks.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.si.amazonbooks.R;
import com.si.amazonbooks.ui.books.view.BookListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                    BookListFragment.newInstance()).commit();
        }
    }
}
