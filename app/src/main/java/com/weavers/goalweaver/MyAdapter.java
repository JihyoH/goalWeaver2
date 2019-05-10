package com.weavers.goalweaver;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.SimpleTimeZone;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;
    private static View.OnClickListener onClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    // 반복되는 칭구..
    public static class MyViewHolder extends RecyclerView.ViewHolder { // RecyclerView.ViewHolder는 안드로이드가 제공하는, 메모리를 효율적으로 관리하게 도와주는 클래스
        // each data item is just a string in this case
        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;
        public View rootView;

        public MyViewHolder(View v) { //row_new.xml에 들어가는 이미지와 텍스트 정의
            super(v);

            // 재사용할 수 있는 row_news의 자식들을 만들기
            TextView_title = v.findViewById(R.id.TextView_title);
            TextView_content = v.findViewById(R.id.TextView_content);
            ImageView_title = v.findViewById(R.id.ImageView_title);
            rootView = v;

            v.setClickable(true);// 누를 수 있다 없다
            v.setEnabled(true);// 활성화 상태이다 아니다
            v.setOnClickListener(onClickListener); // rootView 통해서 태그를 붙여줌.

            // v.findViewById인 이유
            // 액티비티에서는 바로 find가 되는 이유. 액티비티가 부모의 뷰, 즉 그자체가 뷰이기 때문에 바로 가능!
            // 근데 여기서는 View v가 부모가 됨.
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context, View.OnClickListener onClick) {
        //{"1","2"}
        mDataset = myDataset; // 굳이 액티비티에서 값을 받아오지 않고 MyAdpter이라는 지금 이 함수에 입력값 없이
        // 바로 여기에 mDataset = NewsActivity 어쩌고 해서 불러올 수도 있음.

        onClickListener = onClick;

        Fresco.initialize(context); // 액티비티에서 컨텍스트 받아옴. 이런식이면 데이터 누수? 현상이 일어나서 좋지 않음.

    }

    // 홀더에 부모 뷰를 정하기
   // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    // 당장 쓸 내용 정하는 곳
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        NewsData news = mDataset.get(position);
        holder.TextView_title.setText(news.getTitle());

        String content =news.getContent();
        if(content != null && content != "null" && content.length() > 0) {
            holder.TextView_content.setText(content);
        }else{
            holder.TextView_content.setText("내용없음");

        }
        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_title.setImageURI(uri);

        //tag - label
        holder.rootView.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //삼항 연산자
        return mDataset == null ? 0: mDataset.size();//if else 한줄로 합친거.
    }

    public NewsData getNews(int position){
        return mDataset !=null? mDataset.get(position): null;
    }
}
