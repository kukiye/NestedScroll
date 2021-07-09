package com.kuki.nestedscroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kuki.nestedscroll.activity.NestedViewPagerActivity;
import com.kuki.nestedscroll.activity.NestedViewPagerActivityTest1;
import com.kuki.nestedscroll.activity.NestedViewPagerActivityTest2;
import com.kuki.nestedscroll.activity.NestedViewPagerActivityTest3;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onButtonClick(View v) {
        if (v.getId() == R.id.nested_scroll_test1) {
            Intent intent = new Intent(this, NestedViewPagerActivityTest1.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.nested_scroll_test2) {
            Intent intent = new Intent(this, NestedViewPagerActivityTest2.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.nested_scroll_test3) {
            Intent intent = new Intent(this, NestedViewPagerActivityTest3.class);
            startActivity(intent);
        } else if (v.getId() == R.id.nested_scroll) {
            Intent intent = new Intent(this, NestedViewPagerActivity.class);
            startActivity(intent);
        }
    }
}