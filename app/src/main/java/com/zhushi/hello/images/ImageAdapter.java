package com.zhushi.hello.images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhushi.hello.R;
import com.zhushi.hello.beans.ImageBean;
import com.zhushi.hello.beans.NewsBean;
import com.zhushi.hello.utils.ImageLoaderUtils;
import com.zhushi.hello.utils.ToolsUtil;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageItemViewHolder> {
    private Context mContext;
    private int mMaxWidth;
    private int mMaxHeight;
    private List<ImageBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ImageBean getItem(int position) {
        return mData.get(position);
    }

    public ImageAdapter(Context context) {
        this.mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    public void setmData(List<ImageBean> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        ImageItemViewHolder viewHolder = new ImageItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageItemViewHolder holder, int position) {
        ImageBean imageBean = mData.get(position);
        if (imageBean == null) {
            return;
        }
        float scale = (float) imageBean.getWidth() / (float) mMaxWidth;
        int height = (int) (imageBean.getHeight() / scale);
        if (height > mMaxHeight) {
            height = mMaxHeight;
        }
        holder.mTitle.setText(imageBean.getTitle());
        holder.mImage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext, holder.mImage, imageBean.getThumburl());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ImageItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public android.widget.ImageView mImage;

        public ImageItemViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mImage = (android.widget.ImageView) itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getPosition());
            }
        }
    }
}
