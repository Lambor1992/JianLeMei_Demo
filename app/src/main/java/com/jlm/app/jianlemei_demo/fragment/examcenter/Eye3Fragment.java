package com.jlm.app.jianlemei_demo.fragment.examcenter;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Eye3Fragment extends Fragment {

    private RadioGroup resultChoose;
    private RadioButton result1, result2;
    private Button sure;
    private boolean tijiao = false;


    public Eye3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eye_3, container, false);

        resultChoose = (RadioGroup) view.findViewById(R.id.resultChoose_Group);
        result1 = (RadioButton) view.findViewById(R.id.resultChoose_Button1);
        result1.setChecked(true);
        result2 = (RadioButton) view.findViewById(R.id.resultChoose_Button2);
        sure = (Button) view.findViewById(R.id.sure);

        resultChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Log.e("checkID", "----------------------" + checkedId);
                if (checkedId == result1.getId()) {
                    tijiao = false;
                } else if (checkedId == result2.getId()) {
                    tijiao = true;
                }
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tijiao) {
                    Toast.makeText(getActivity(), "您有散光的症状。", Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(getActivity(), "恭喜您，你的视力很正常。", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}
