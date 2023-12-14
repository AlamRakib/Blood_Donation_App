package com.example.blooddonationproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    ArrayList<Image> ImageArrayList;
    ViewPager2 viewPager2;

    ImageAdapter(ArrayList<Image> imageArrayList, ViewPager2 viewPager2)
    {
        this.ImageArrayList = imageArrayList;
        this.viewPager2 = viewPager2;
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder{


        RoundedImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_container,parent,false);

        return new ImageViewHolder(myView);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {


        holder.imageView.setImageResource(ImageArrayList.get(position).getImage());

        if(position == ImageArrayList.size() - 2)
        {
            viewPager2.post(runnable);
        }


    }

    @Override
    public int getItemCount() {
        return ImageArrayList.size();
    }





    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ImageArrayList.addAll(ImageArrayList);
            notifyDataSetChanged();
        }
    };








}
