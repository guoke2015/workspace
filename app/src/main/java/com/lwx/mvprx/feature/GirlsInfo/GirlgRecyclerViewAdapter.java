package com.lwx.mvprx.feature.GirlsInfo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lwx.mvprx.R;
import com.lwx.mvprx.data.local.Girls;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/17
 *     desc   : Adapter类
 *     version: 1.0
 * </pre>
 */

public class GirlgRecyclerViewAdapter extends CommonAdapter<Girls> {
    private Context context;

    public GirlgRecyclerViewAdapter(Context context, int layoutId, List<Girls> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, Girls girls, int position) {
        holder.setText(R.id.girls_title, girls.getTitle());
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_error);
        Glide.with(context)
                .load(girls.getUrl())
                .apply(options)
                .into((ImageView) holder.getView(R.id.girls_img));
    }
}
