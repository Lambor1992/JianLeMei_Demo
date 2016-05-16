package com.jlm.app.jianlemei_demo.fragment.health.healthcase;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EyeProtectFragment extends Fragment {
    String str;
    int icon;
    String title;

    public EyeProtectFragment() {

    }
    @SuppressLint("ValidFragment")
    public EyeProtectFragment(String title, String str, int icon) {
        this.title = title;
        this.str = str;
        this.icon = icon;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_health_case_eye_1_1, container, false);
        ((ImageView) v.findViewById(R.id.health_case_eye_1_1_img)).setImageResource(icon);
        ((TextView) v.findViewById(R.id.health_case_eye_1_1_tv)).setText(str);
        ((TextView) v.findViewById(R.id.health_case_eye_1_title_tv)).setText(title);
        return v;
    }

}
