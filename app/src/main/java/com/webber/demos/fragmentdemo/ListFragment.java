package com.webber.demos.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.webber.demos.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by picher on 2017/12/12.
 * Describe：
 */

public class ListFragment extends BaseFragment {

    private int size = 20;
    private List<String> data = new ArrayList<>();
    private List<String> change = new ArrayList<>();

    int currentPos = 0;
    private RecyclerView mListContentRlv;
    private ListAdapter listAdapter;
    private int start = 0;
    private View mLoadMoreProgress;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("position","11111");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("picher",savedInstanceState!=null?savedInstanceState.getString("position",""):"null");

        mListContentRlv = (RecyclerView) view.findViewById(R.id.m_list_content_rlv);
        mListContentRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new ListAdapter();
        mLoadMoreProgress = getActivity().getLayoutInflater().inflate(R.layout.listcell_load_more, null, false);
        listAdapter.setFooter(mLoadMoreProgress);
        mListContentRlv.setAdapter(listAdapter);
        data.clear();
        change.clear();
        for (int i = 0; i < size; i++) {
            data.add("我是数据小 " + i + "");
            change.add("我改变啦！！ " + i + "");
        }
        Log.d("picher", "设置数据 " + view.hashCode());
        if (currentPos == 0) {
            listAdapter.setData(data);
        } else {
            //data.addAll(change);
            listAdapter.setData(data);
        }
        listAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(int pos) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        currentPos++;
                        //data.addAll(change);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    /*if (data.size() <= 50 && currentPos <= 1) {
                                        listAdapter.setData(data);

                                    }*/
                                    listAdapter.removeFooter();
                                }
                            });
                        }
                    }
                }, 5000);
            }
        });
    }
    public interface OnLoadMoreListener{
        void onLoadMore(int pos);
    }

    public class ListAdapter extends RecyclerView.Adapter {

        private static final int FOOTER_TYPE = 1;
        private List<String> strs = new ArrayList<>();
        private List<View> mFooters = new ArrayList<>();
        private OnLoadMoreListener loadMoreListener;

        public void setData(List<String> data) {
            this.strs = data;
            notifyDataSetChanged();
        }

        public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
            this.loadMoreListener = onLoadMoreListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == FOOTER_TYPE){
                FrameLayout frameLayout = new FrameLayout(parent.getContext());
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
                frameLayout.setLayoutParams(layoutParams);
                return new HeaderFooterViewHolder(frameLayout);
            }else{
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ViewHolder){
                ((ViewHolder) holder).tv.setText(strs.get(position));
                Log.d("pihcer", "绑定位置：" + position);
            }else if(holder instanceof HeaderFooterViewHolder){
                ((HeaderFooterViewHolder) holder).base.addView(mFooters.get(0));
                if(loadMoreListener != null){
                    loadMoreListener.onLoadMore(position);
                }
            }
        }

        @Override
        public int getItemCount() {
            return strs.size()+mFooters.size();
        }

        public void setFooter(View mLoadMoreProgress) {
            if(this.mFooters == null){
                mFooters = new ArrayList<>();
            }
            mFooters.add(mLoadMoreProgress);

            //notifyItemInserted(data.size()+1);
        }

        public void removeFooter(){
            if(mFooters != null){
                notifyItemRemoved(data.size()+1);
                //notifyItemChanged(data.size());
                if(mFooters.get(0).getParent()!=null){
                    ((ViewGroup) mFooters.get(0).getParent()).removeView(mFooters.get(0));
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(position >= data.size()){
                return FOOTER_TYPE;
            }
            return super.getItemViewType(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

        public class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
            public FrameLayout base;

            public HeaderFooterViewHolder(View itemView) {
                super(itemView);
                this.base = (FrameLayout) itemView;
            }
        }
    }

}
