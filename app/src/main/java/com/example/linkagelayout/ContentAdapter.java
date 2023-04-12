package com.example.linkagelayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ItemViewHolder> {

    private Context context;
    private List<Entity> datas;
    private HashSet<ObservableScrollView> observerList = new HashSet<>();

    public ContentAdapter(Context context, ObservableScrollView ObservableScrollView) {
        this.context = context;
        //设置头部recycle
        observerList.add(ObservableScrollView);
        bindScroolView(ObservableScrollView);
    }

    public void setDatas(List<Entity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_content, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.tvLeftTitle.setText(datas.get(i).getLeftTitle());
        //右边滑动部分
        //设置多条recyclerview联动
        observerList.add(itemViewHolder.ObservableScrollView);
        bindScroolView(itemViewHolder.ObservableScrollView);
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_left_title)
        TextView tvLeftTitle;
        @BindView(R.id.sc_rl_content)
        ObservableScrollView ObservableScrollView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //多条recycleview联动
    public void bindScroolView(ObservableScrollView scrollView) {
        scrollView.setOnScrollViewListener((scrollView1, x, y, oldx, oldy) -> {
            for (ObservableScrollView item : observerList) {
                if (scrollView != item) {
                    item.smoothScrollTo(scrollView.getScrollX(), scrollView.getScrollY());
                }
            }
        });
    }
}
