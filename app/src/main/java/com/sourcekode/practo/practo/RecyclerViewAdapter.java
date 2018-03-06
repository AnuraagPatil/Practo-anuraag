package com.sourcekode.practo.practo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sourcekode.practo.practo.ModalData.Specialities;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public static final String SPECILITY_KEY = "specility_key";
    private Context mContext ;
    private List<Specialities> mData ;

    public RecyclerViewAdapter(Context mContext, List<Specialities> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Specialities speciality  = mData.get(position);

        holder.doctor_title.setText(mData.get(position).getTitle());
        holder.doctor_img.setImageResource(mData.get(position).getThumbnail());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on "+speciality.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,Doctors.class);
                intent.putExtra(SPECILITY_KEY,speciality.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_title;
        ImageView doctor_img;
        View mView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            doctor_title = (TextView)itemView.findViewById(R.id.title_id);
            doctor_img = (ImageView) itemView.findViewById(R.id.img_id);
        }
    }


}
