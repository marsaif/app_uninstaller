package com.example.app_uninstaller.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_uninstaller.R;
import com.example.app_uninstaller.models.App;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

     List<App> list ;
     Context context ;

    public CustomAdapter(List<App> list , Context context) {
        this.list = list ;
        this.context = context ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewAppName().setText(list.get(position).getName());
        holder.getImageViewAppIcon().setImageDrawable(list.get(position).getIcon());
        holder.getTextViewAppDate().setText(list.get(position).getInstalledDate());
        holder.getTextViewAppVersion().setText(list.get(position).getAppVersion());

        holder.getImageViewDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("package:"+list.get(position).getAppPackage());
                Intent intent =new Intent(Intent.ACTION_UNINSTALL_PACKAGE, uri);
                context.startActivity(intent) ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textViewAppName ;
        private final ImageView imageViewAppIcon ;
        private final TextView textViewAppDate ;
        private final TextView textViewAppVersion ;
        private final ImageView imageViewDelete ;

        public ViewHolder(View view) {
            super(view);

            textViewAppName = (TextView) view.findViewById(R.id.app_name);
            imageViewAppIcon = (ImageView) view.findViewById(R.id.app_image) ;
            textViewAppDate = (TextView) view.findViewById(R.id.app_date) ;
            textViewAppVersion = (TextView) view.findViewById(R.id.app_version) ;
            imageViewDelete = (ImageView) view.findViewById(R.id.delete);

        }

        public TextView getTextViewAppName() {
            return textViewAppName;
        }

        public ImageView getImageViewAppIcon() {
            return imageViewAppIcon;
        }

        public TextView getTextViewAppDate() {
            return textViewAppDate;
        }

        public TextView getTextViewAppVersion() {
            return textViewAppVersion;
        }

        public ImageView getImageViewDelete() {
            return imageViewDelete;
        }

    }
}
