package com.webber.demos.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webber.demos.R;
import com.webber.demos.utils.Utils;

import java.util.List;


public class BreakingNewsRecyclerAdapter extends RecyclerView.Adapter<BreakingNewsRecyclerAdapter.ViewHolder> {

    public static final String ARTICLE_A = "article?a=";
    private Context context;

    private List<String> items;
    private int lastPosition = 0;

    public int getCurrentPosition() {
        return lastPosition;
    }

    public int getRealCurrentPosition(){
        return lastPosition % items.size();
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public String getCurrentItem() {
        return items.get(lastPosition % items.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MarqueeTextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (MarqueeTextView) itemView.findViewById(R.id.breaking_news_title);
        }
    }

    public BreakingNewsRecyclerAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public BreakingNewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.breaking_news_layout, parent, false);
        v.getLayoutParams ().width = Utils.getScreenWidth(parent.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text.setText(items.get(position % items.size()));
        Log.d("picher", "pos:" + position + "lastPos：" + lastPosition);
       /* if (position == lastPosition) {
            Log.d("picher", "启动滚动！" + position % items.size());
            holder.text.requestFocus();
            holder.text.setSelected(true);
            holder.text.setEnabled(true);
            holder.text.startScroll();
        }*/
      /*  } else {
            holder.text.stopScroll();
        }*/
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startDetailFragment(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position % items.size());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

}