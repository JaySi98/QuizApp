package com.example.sb_projekt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sb_projekt.Adapter.Category;
import com.example.sb_projekt.R;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Category> categoryList;
    private OnCategoryListener categoryListener;

    public CategoryListAdapter(List<Category> list, OnCategoryListener categoryListener)
    {
        super();
        this.categoryList = list;
        this.categoryListener = categoryListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, categoryListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position)
    {
        Category itemAdapter = categoryList.get(position);
        ((ViewHolder) viewHolder).mTv_name.setText(itemAdapter.getTitle());
        ((ViewHolder) viewHolder).mImg.setImageResource(itemAdapter.getImage());
    }

    @Override
    public int getItemCount()
    {
        return categoryList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView mTv_name;
        public ImageView mImg;

        OnCategoryListener onCategoryListener;

        public ViewHolder(View itemView, OnCategoryListener onCategoryListener)
        {
            super(itemView);
            mTv_name = (TextView) itemView.findViewById(R.id.textView_name);
            mImg = (ImageView) itemView.findViewById(R.id.imageView_item);

            this.onCategoryListener = onCategoryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface OnCategoryListener
    {
        void onCategoryClick(int position);
    }
}
