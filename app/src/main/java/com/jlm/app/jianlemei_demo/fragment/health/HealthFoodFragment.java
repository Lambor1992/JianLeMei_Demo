package com.jlm.app.jianlemei_demo.fragment.health;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.HealthCookFoodAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HealthFoodFragment extends Fragment {
    private static HealthFoodFragment fragment;
    @Bind(R.id.listview)
    ListView listview;

    public HealthFoodFragment() {
    }

    public static HealthFoodFragment newInstance() {
        if (fragment == null) return new HealthFoodFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        ButterKnife.bind(this, view);
        listview.setAdapter(new HealthCookFoodAdapter(getActivity(),false));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
