package adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doctappo.DetailsSalonActivity;
import com.doctappo.ListActivity;
import com.doctappo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Config.ConstValue;
import models.ActiveModels;
import models.BusinessModel;
import util.CommonClass;

/**
 * Created by LENOVO on 4/19/2016.
 */
public class BusinessListAdapter  extends RecyclerView.Adapter<BusinessListAdapter.ProductHolder>{
    private  Activity activity;
    private CommonClass common;
    private ArrayList<BusinessModel> postItems;

    public BusinessListAdapter(Activity con, ArrayList<BusinessModel> array){
        activity = con;
            postItems = array;
        common = new CommonClass(con);

    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_business_list,parent,false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductHolder holder, final int position) {
        final BusinessModel categoryModel = postItems.get(position);
        String path = categoryModel.getBus_logo();

        Picasso.with(activity).load(ConstValue.BASE_URL + "/uploads/business/"  + path).into(holder.imageView);

        holder.textName.setText(categoryModel.getBus_title());
        holder.textSubTitle.setText(categoryModel.getBus_google_street());
        holder.ratingbar.setRating(Float.parseFloat(categoryModel.getAvg_rating()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActiveModels.BUSINESS_MODEL = postItems.get(position);
                Intent intent = new Intent(activity,DetailsSalonActivity.class);
                activity.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return postItems.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textName;
        TextView textSubTitle;
        RatingBar ratingbar;
        public ProductHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textName = (TextView) itemView.findViewById(R.id.title);
            textSubTitle = (TextView) itemView.findViewById(R.id.subTitle);
            ratingbar = (RatingBar)itemView.findViewById(R.id.ratingBar1);
            textName.setTypeface(common.getCustomFont());
        }
    }




}
