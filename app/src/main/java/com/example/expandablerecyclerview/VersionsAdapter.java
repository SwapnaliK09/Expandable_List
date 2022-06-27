package com.example.expandablerecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;

public class VersionsAdapter extends RecyclerView.Adapter<VersionsAdapter.versionVH> {

    Context context;

    public VersionsAdapter(Context context, ArrayList<DataModal> arrayList) {
        this.context = context;
        UserActivity.arrayList = arrayList;
    }

    @NonNull
    @Override
    public versionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_,parent,false);
        return new versionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull versionVH holder, int position) {

        DataModal dataModal = UserActivity.arrayList.get(position);
        holder.codeNameTxt.setText(dataModal.getName());
        holder.versionTxt.setText(dataModal.getJob());
        holder.apiLevelTxt.setText(dataModal.getId());
        holder.descriptionTxt.setText(dataModal.getCreatedAt());

        boolean is_Expandable = UserActivity.arrayList.get(position).isExpandable();
        holder.relativeLayout.setVisibility(is_Expandable? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return UserActivity.arrayList.size();
    }

    public class versionVH extends RecyclerView.ViewHolder {
        TextView codeNameTxt , versionTxt , apiLevelTxt, descriptionTxt;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public versionVH(@NonNull View itemView) {
            super(itemView);
            codeNameTxt= (itemView).findViewById(R.id.code_name);
            versionTxt = (itemView).findViewById(R.id.version);
            apiLevelTxt = (itemView).findViewById(R.id.api_level);
            descriptionTxt = (itemView).findViewById(R.id.description);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            relativeLayout = itemView.findViewById(R.id.expanded_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataModal versions = UserActivity.arrayList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
