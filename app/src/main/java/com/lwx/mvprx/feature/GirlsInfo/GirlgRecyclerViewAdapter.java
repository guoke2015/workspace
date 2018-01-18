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
 *     desc   :
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

    /*public GirlgRecyclerViewAdapter(Context context, int layoutId, List<List<Girls>> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }*/

    /*@Override
    protected void convert(ViewHolder holder, List<Girls> girls, int position) {
        Girls girl = girls.get(position);
        holder.setText(R.id.girls_title, girl.getTitle());
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_error);
        Glide.with(context)
                .load(girl.getUrl())
                .apply(options)
                .into((ImageView) holder.getView(R.id.girls_img));
    }*/


    /*public GirlgRecyclerViewAdapter(Context context, List<Girls> girls) {
        this.context = context;
        this.girls = girls;
        mLayoutInflater = LayoutInflater.from(context);
    }*/

    /*@Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlsViewHolder holder = new GirlsViewHolder(mLayoutInflater.inflate(
                R.layout.girls_rec, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {
        Girls girl = girls.get(position);
        holder.setGirls(girl);

    }

    @Override
    public int getItemCount() {
        return girls.size();
    }*/

    /*class GirlsViewHolder extends RecyclerView.ViewHolder {
        TextView girlsTitle;
        ImageView girldImg;

        public GirlsViewHolder(View view) {
            super(view);
            girlsTitle = view.findViewById(R.id.girls_title);
            girldImg = view.findViewById(R.id.girls_img);
        }

        public void setGirls(Girls girls) {
            girlsTitle.setText(girls.getTitle());
            RequestOptions options=new RequestOptions()
                    .placeholder(R.drawable.ic_empty)
                    .error(R.drawable.ic_error);
            Glide.with(context)
                    .load(girls.getUrl())
                    .apply(options)
                    .into(girldImg);
        }
    }*/
}
