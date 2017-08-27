package fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.doctappo.MapActivity;
import com.doctappo.R;
import com.doctappo.TimeSlotActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Config.ApiParams;
import adapters.DoctorAdapter;
import adapters.ServiceChargeAdapter;
import dialogues.DoctorInfoDialog;
import models.ActiveModels;
import models.BusinessModel;
import models.DoctorModel;
import models.ServicesModel;
import util.CommonClass;
import util.RecyclerItemClickListener;
import util.VJsonRequest;

/**
 * Created by LENOVO on 7/10/2016.
 */
public class DetailsFragment extends Fragment   {
    CommonClass common;
    Activity act;
    Bundle args;
    BusinessModel selected_business;

    public static ArrayList<DoctorModel> mDoctorArray;
    DoctorAdapter doctorAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_details, container, false);
        act = getActivity();
        common = new CommonClass(act);
            selected_business = ActiveModels.BUSINESS_MODEL;
            mDoctorArray = new ArrayList<DoctorModel>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        doctorAdapter = new DoctorAdapter(getActivity(),mDoctorArray);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                DoctorInfoDialog doctorInfoDialog = new DoctorInfoDialog(getActivity(),mDoctorArray.get(position));
                doctorInfoDialog.show();
            }
        }));
        loadData();
            TextView textDescription = (TextView)rootView.findViewById(R.id.textDescription);
            textDescription.setText(Html.fromHtml(selected_business.getBus_description()));

            TextView txtPhone = (TextView)rootView.findViewById(R.id.textPhone);
            TextView txtAddress = (TextView)rootView.findViewById(R.id.textAddress);

            txtPhone.setText(selected_business.getBus_contact());
            txtAddress.setText(selected_business.getBus_google_street());

        args = this.getArguments();

        Button locationBtn = (Button)rootView.findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return  rootView;
    }
    public void loadData(){
        HashMap<String,String> params = new HashMap<>();
        params.put("bus_id",selected_business.getBus_id());

        VJsonRequest vJsonRequest = new VJsonRequest(getActivity(), ApiParams.GET_DOCTORS,params,
                new VJsonRequest.VJsonResponce(){
                    @Override
                    public void VResponce(String responce) {

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<DoctorModel>>() {
                        }.getType();
                        mDoctorArray.clear();
                        mDoctorArray.addAll((Collection<? extends DoctorModel>) gson.fromJson(responce, listType));
                        doctorAdapter.notifyDataSetChanged();
                        //progressBar1.setVisibility(View.GONE);

                    }
                    @Override
                    public void VError(String responce) {
                        //progressBar1.setVisibility(View.GONE);
                    }
                });


    }
}
