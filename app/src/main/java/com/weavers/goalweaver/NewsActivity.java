package com.weavers.goalweaver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class NewsActivity extends AppCompatActivity {

//    public class MyActivity extends Activity {
        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mlayoutManager;
        private String[] mDataset = {"1","2"}; // 최초값을 몽땅 다 어디선가 받아와서 와장창! 어댑터한테 떠넘김

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_news);
            mRecyclerView = findViewById(R.id.my_recycler_view);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mlayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mlayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new MyAdapter(mDataset); // 내가 만들 어댑터가 여기에!
            mRecyclerView.setAdapter(mAdapter);
        }
//    }
}
