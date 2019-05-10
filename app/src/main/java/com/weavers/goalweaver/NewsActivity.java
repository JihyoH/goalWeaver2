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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    //    public class MyActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private String[] mDataset = {"1", "2"}; // 최초값을 몽땅 다 어디선가 받아와서 와장창! 어댑터한테 떠넘김
    RequestQueue queue;

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

        queue = Volley.newRequestQueue(this);
        getNews();
        //1. 화면이 로딩 -> 뉴스 정보를 받아온다
        //2. 정보 -> 어댑터 넘겨준다
        //3. 어댑터 -> 셋팅
    }

    public void getNews() {
// Instantiate the RequestQueue.
        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=8106d9946f274928b7122e666e66f816";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // textView.setText("Response is: " + response.substring(0, 500));
                        Log.d("NEWS", response);

                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            JSONArray arrayArticles = jsonObj.getJSONArray("articles"); // 데이터의 형태가 배열이라서 getJSONArray

                            //response ->> NewsData Class 분류
                            List<NewsData> news = new ArrayList<>();

                            for(int i=0, j=arrayArticles.length(); i<j; i++){
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("NEWS", obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setContent(obj.getString("content"));
                                news.add(newsData);
                            }

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(news,NewsActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Object obg = v.getTag();
                                    if(obg != null){
                                        int position = (int)obg;
                                        NewsData newsDataSet = ((MyAdapter)mAdapter).getNews(position);
                                        Intent intent = new Intent(NewsActivity.this,MyAdapter.class);
                                        //1. 본문 내용만 넘기기
                                        //2. 전체를 다 넘기기
                                        //2-1. 하나씩
                                        //2-2. 한번에 다 ?? 이건 어떻게?
                                        intent.putExtra("title", newsDataSet.getTitle());
                                        intent.putExtra("urlToImage", newsDataSet.getUrlToImage());
                                        intent.putExtra("content", newsDataSet.getContent());
                                        startActivity(intent);
                                        //인텐트에 밀어넣고 어디에서 이걸 가져와...??
                                    }

                                }
                            }); // 내가 만들 어댑터가 여기에!
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
//    }
}
