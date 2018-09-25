package com.example.khalil.work_khalil;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Fragment_Investimentos extends Fragment {


    public Fragment_Investimentos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_investimento, container, false);
        TextView title=view.findViewById(R.id.title);
        TextView fundname=view.findViewById(R.id.fundname);
        TextView whatis=view.findViewById(R.id.whatis);
        TextView definition=view.findViewById(R.id.definition);
        TextView risktitle=view.findViewById(R.id.risktitle);
        TextView riskvalue=view.findViewById(R.id.riskvalue);
        TextView infotitle=view.findViewById(R.id.infotitle);

        TextView mfund=view.findViewById(R.id.mfund);
        TextView mcdi=view.findViewById(R.id.mcdi);
        TextView yfund=view.findViewById(R.id.yfund);
        TextView ycdi=view.findViewById(R.id.ycdi);
        TextView fund12=view.findViewById(R.id.fund12);
        TextView cdi12=view.findViewById(R.id.cdi12);
        JSONObject obj = null;
        try {
            obj = new JSONObject(loadJSONFund());
            title.setText(obj.getJSONObject("screen").getString("title"));
            fundname.setText(obj.getJSONObject("screen").getString("fundName"));
            whatis.setText(obj.getJSONObject("screen").getString("whatIs"));
            definition.setText(obj.getJSONObject("screen").getString("definition"));
            risktitle.setText(obj.getJSONObject("screen").getString("riskTitle"));
            riskvalue.setText(obj.getJSONObject("screen").getString("risk"));
            infotitle.setText(obj.getJSONObject("screen").getString("infoTitle"));

            mfund.setText(obj.getJSONObject("screen").getJSONObject("moreInfo").getJSONObject("month").getString("fund"));
            mcdi.setText(obj.getJSONObject("screen").getJSONObject("moreInfo").getJSONObject("month").getString("CDI"));
            yfund.setText(obj.getJSONObject("screen").getJSONObject("moreInfo").getJSONObject("year").getString("fund"));
            ycdi.setText(obj.getJSONObject("screen").getJSONObject("moreInfo").getJSONObject("year").getString("CDI"));
            fund12.setText(obj.getJSONObject("screen").getJSONObject("moreInfo").getJSONObject("12months").getString("fund"));
            cdi12.setText(obj.getJSONObject("screen").getJSONObject("moreInfo").getJSONObject("12months").getString("CDI"));




        } catch (JSONException e) {
            e.printStackTrace();
        }

        String [] data=null;
        String [] names=null;
        int count=0;
        JSONArray DownInfoJsonArray = null;
        try {
            DownInfoJsonArray = obj.getJSONObject("screen").getJSONArray("downInfo");
            JSONArray InfoJsonArray = obj.getJSONObject("screen").getJSONArray("info");
            data =new String[InfoJsonArray.length()+DownInfoJsonArray.length()];
            names=new String[InfoJsonArray.length()+DownInfoJsonArray.length()];


            for (count = 0; count < InfoJsonArray.length(); count++) {
                JSONObject jo_inside = InfoJsonArray.getJSONObject(count);
                Log.d("Details1-->", jo_inside.getString("name"));
                names[count] = jo_inside.getString("name");
                data[count] = jo_inside.getString("data");


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {




            for (int i = 0; i < DownInfoJsonArray.length(); i++) {
                JSONObject jo_inside = DownInfoJsonArray.getJSONObject(i);
                Log.d("Details1-->", jo_inside.getString("name"));
                names[count] = jo_inside.getString("name");
                data[count] = jo_inside.getString("data");
                count++;


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView list=view.findViewById(R.id.listview);
        Investimento_adapter adapter = new Investimento_adapter(getActivity(), names,data);
        list.setDivider(null);
        list.setDividerHeight(0);
        list.setAdapter(adapter);



        return view;
    }

    public String loadJSONFund() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("fund.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
