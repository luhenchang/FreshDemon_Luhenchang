package com.example.ls.freshdemon_luhenchang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by 路很长~ on 2017/11/29.
 */
public class LastInforAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mData;

    public LastInforAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_item, null);
        FirstViewHolder holder = new FirstViewHolder(view, mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FirstViewHolder holder1 = (FirstViewHolder) holder;
        holder1.setData(mData.get(position), position);
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private TextView first_view;

        public FirstViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
            first_view=itemView.findViewById(R.id.tv_my);

        }

        public void setData(String str, final int position) {
           first_view.setText(str);
        }
    }
}
