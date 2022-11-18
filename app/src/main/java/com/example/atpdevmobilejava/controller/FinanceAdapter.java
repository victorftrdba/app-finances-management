package com.example.atpdevmobilejava.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atpdevmobilejava.R;
import com.example.atpdevmobilejava.model.DataModel;
import com.example.atpdevmobilejava.model.Finance;

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;

        public ViewHolder (View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null) {
                        return longClickListener.onItemLongClick(view, getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }

    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public void setOnItemClickListener (OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnItemLongClickListener (OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(
                R.layout.item_recyclerview,
                parent,
                false
        );

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Finance f = DataModel.getInstance().getFinance(position);
        holder.textView1.setText(f.getName());
        holder.textView2.setText(f.getStatus());
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getFinancesSize();
    }
}
