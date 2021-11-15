package com.work.photoselector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.work.photoselector.R;
import com.work.photoselector.bean.ImageItem;
import com.work.photoselector.view.SquareImageView;

import java.util.List;

/**
 * Date:2021/11/15
 * Description:
 * Author:XueTingTing
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {


    private List<ImageItem> imageItemList;
    private Context context;
    private boolean useCamera;

    private static final int TYPE_CAMERA = 1;
    private static final int TYPE_IMAGE = 2;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecycleAdapter(List<ImageItem> imageItemList, Context context, boolean useCamera) {
        this.imageItemList = imageItemList;
        this.context = context;
        this.useCamera = useCamera;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CAMERA) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_usecamera, parent,false);
            return new ViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_image, parent,false);
            return new ViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleAdapter.ViewHolder holder, final int position) {

        if (getItemViewType(position) == TYPE_CAMERA) {
            holder.ivCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener!=null){
                        onItemClickListener.OnCameraClick();
                    }
                }
            });
        } else if (getItemViewType(position) == TYPE_IMAGE) {

            Glide.with(context).load(imageItemList.get(position).path).into(holder.images);
            holder.images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItemClick(imageItemList.get(position),position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return useCamera ? imageItemList.size() + 1 : imageItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (useCamera && position == 0) {
            return TYPE_CAMERA;
        } else {
            return TYPE_IMAGE;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView images;
        private final ImageView ivCamera;
        private final ImageView ivSelectIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.iv_image);
            ivCamera = itemView.findViewById(R.id.iv_camera);
            ivSelectIcon = itemView.findViewById(R.id.iv_selector);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(ImageItem image, int position);

        void OnCameraClick();
    }
}
