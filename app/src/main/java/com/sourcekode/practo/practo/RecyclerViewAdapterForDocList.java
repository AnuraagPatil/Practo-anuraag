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

import com.sourcekode.practo.practo.ModalData.DoctorsData;
import com.sourcekode.practo.practo.ModalData.Specialities;

import org.w3c.dom.Text;

import java.util.List;


public class RecyclerViewAdapterForDocList extends RecyclerView.Adapter<RecyclerViewAdapterForDocList.MyViewHolder> {

    private Context mContext ;
    private List<DoctorsData> mData ;

    public RecyclerViewAdapterForDocList(Context mContext, List<DoctorsData> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_doctors,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final DoctorsData doctorsData  = mData.get(position);

     /*   holder.profile_pic.setImageResource(mData.get(position).getProfile_pic());
        holder.doctor_name.setText(mData.get(position).getDoctor_name());
        holder.practise.setText(mData.get(position).getPractise());
        holder.fees.setText(mData.get(position).getFees());
        holder.locality.setText(mData.get(position).getLocality());
        holder.street.setText(mData.get(position).getStreet());
        holder.rating_val.setText(mData.get(position).getRating());*/


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on "+doctorsData.getDoctor_name(), Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(mContext,Profile.class);
               mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_name;
        ImageView profile_pic;
        TextView address;
        TextView city;
        View mView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            doctor_name = (TextView)itemView.findViewById(R.id.txt_doctor_name);
            profile_pic = (ImageView) itemView.findViewById(R.id.profile_image);
            address = itemView.findViewById(R.id.txt_address);
            city = itemView.findViewById(R.id.txt_city);




        }
    }


}