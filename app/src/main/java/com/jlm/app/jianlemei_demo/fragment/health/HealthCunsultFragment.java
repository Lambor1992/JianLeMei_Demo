package com.jlm.app.jianlemei_demo.fragment.health;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.HealthConsultAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthCunsultFragment extends Fragment {
    private static HealthCunsultFragment fragment;
    @Bind(R.id.listview)
    ListView listview;

    public HealthCunsultFragment() {
    }

    public static HealthCunsultFragment newInstance() {
        if (fragment == null) return new HealthCunsultFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        ButterKnife.bind(this, view);
        listview.setAdapter(new HealthConsultAdapter(getActivity()));
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
