package com.work.photoselector.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    protected List<T> data;
    protected IClick iClick;

    public void setiClick(IClick iClick) {
        this.iClick = iClick;
    }

    public BaseAdapter(Context context, List<T> data){
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(getLayout(),parent,false);
        final BaseViewHolder viewHolder = new BaseViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iClick!=null){
                    iClick.click(viewHolder.getLayoutPosition());
                }
            }
        });
        return viewHolder;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BaseViewHolder vh = (BaseViewHolder) holder;
        T tData = data.get(position);
        bindData(vh,tData,position);
    }

    protected abstract void bindData(BaseViewHolder vh, T tData,int position);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface IClick{
        void click(int position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray views=new SparseArray();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
           // itemView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)); //让图片是个正方形
        }

        /**
         * 通过id获取适配器的组件
         * @param id
         * @return
         */
        public View getViewById(int id){
            View view = (View) views.get(id);
            if (view==null){
                view=itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
