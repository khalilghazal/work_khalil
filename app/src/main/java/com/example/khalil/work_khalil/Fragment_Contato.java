package com.example.khalil.work_khalil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Fragment_Contato extends Fragment {
    public EditText hintName;
    public EditText email;
    public EditText fone;
    public Button sendBut;
    public CheckBox register;
    public HashMap<String, String> cell;
    public   ArrayList<HashMap<String, String>> cells = new ArrayList<HashMap<String, String>>();
    public Fragment_Contato() {
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
        View view = inflater.inflate(R.layout.fragment_contato, container, false);
        hintName = view.findViewById(R.id.nomecompleto);
        email = view.findViewById(R.id.email);
        fone = view.findViewById(R.id.fone);
        sendBut = view.findViewById(R.id.send);
        register=view.findViewById(R.id.register);

        try {
            JSONObject obj = new JSONObject(loadJSONCells());
            JSONArray m_jArry = obj.getJSONArray("cells");



            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("id"));
                int id = jo_inside.getInt("id");
                int type = jo_inside.getInt("type");
                String message = jo_inside.getString("message");
                String field = jo_inside.getString("typefield");
                Boolean hidden = jo_inside.getBoolean("hidden");
                double topSpacing = jo_inside.getDouble("topSpacing");
                String show = jo_inside.getString("show");
                Boolean required = jo_inside.getBoolean("required");

                //Add your values in your `ArrayList` as below:
                cell = new HashMap<String, String>();
                cell.put("id", id + "");
                cell.put("type", type + "");
                cell.put("message", message);
                cell.put("field", field + "");
                cell.put("hidden", hidden + "");
                cell.put("topSpacing", topSpacing + "");
                cell.put("show", show + "");
                cell.put("required", required + "");

                cells.add(cell);
            }
            setupContactPage();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return view;
    }

    public void setupContactPage(){

        hintName.setHint(cells.get(1).get("message"));
        email.setHint(cells.get(2).get("message"));
        fone.setHint(cells.get(3).get("message"));
        sendBut.setText(cells.get(5).get("message"));
        register.setText(cells.get(4).get("message"));
    }
    public String loadJSONCells () {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("cells.json");
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
