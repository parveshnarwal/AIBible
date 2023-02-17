package com.aibible.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aibible.R;
import com.aibible.models.Tool;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ToolCategoryListAdapter extends RecyclerView.Adapter<ToolCategoryListAdapter.ViewHolder>{

    private final List<Tool> toolList;
    private final Context context;

    public ToolCategoryListAdapter(List<Tool> toolList, Context context){

        this.toolList = toolList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tool tool = toolList.get(position);
        holder.tvToolName.setText(tool.toolName);

        holder.tvToolShorDescription.setText(tool.shortDes);

        String url =  tool.tnUrl
                .replace(".png", ".jpg")
                .replace(".webp",".jpg")
                .replace(".ai","")
                .replace(".AI","");

        Glide.with(context)
                .load(url)
                .fitCenter()
                .into(holder.ivToolImage);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, tool.url, Toast.LENGTH_SHORT).show();
            }
        });

        String[] prices = tool.pricing;

        for(int i = 0; i < prices.length; i++){
            if(i == 0){
                holder.priceChip1.setText(prices[i]);
                holder.priceChip1.setVisibility(View.VISIBLE);
            } else if (i == 1){
                holder.priceChip2.setText(prices[i]);
                holder.priceChip2.setVisibility(View.VISIBLE);
            } else if (i == 2){
                holder.priceChip3.setText(prices[i]);
                holder.priceChip3.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return toolList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvToolName;
        TextView tvToolShorDescription;
        ImageView ivToolImage;
        MaterialButton button;
        Chip priceChip1, priceChip2, priceChip3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvToolName = itemView.findViewById(R.id.toolTitle);
            tvToolShorDescription = itemView.findViewById(R.id.toolDescription);
            ivToolImage = itemView.findViewById(R.id.toolImage);
            button = itemView.findViewById(R.id.btnVisit);

            priceChip1 = itemView.findViewById(R.id.chip1);
            priceChip2 = itemView.findViewById(R.id.chip2);
            priceChip3 = itemView.findViewById(R.id.chip3);

        }
    }


}
