package com.someusefullApps.jobwebsites;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.SiteViewHolder> {

    private CustomItemClickListener listener;
    private List<SingleWebsite> websites;

    CustomAdaptor(List<SingleWebsite> websites, CustomItemClickListener listener) {
        this.websites = websites;
        this.listener = listener;
    }

    //-------------------------------------------------
    @Override
    public SiteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_website,
                viewGroup, false);
        final SiteViewHolder personViewHolder = new SiteViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, personViewHolder.getAdapterPosition());
            }
        });
        return personViewHolder;
    }

    //-----------------------------------------------
    @Override
    public void onBindViewHolder(SiteViewHolder siteViewHolder, int i) {
        siteViewHolder.siteName.setText(websites.get(i).getMwebSiteName());
    }

    //-----------------------------------------------------
    @Override
    public int getItemCount() {
        return websites.size();
    }

    //----------------------------------------------------------------------------
    static class SiteViewHolder extends RecyclerView.ViewHolder {
        TextView siteName;

        SiteViewHolder(View itemView) {
            super(itemView);
            siteName = itemView.findViewById(R.id.site_name);

        }
    }
    //-------------------------------------------------
}
