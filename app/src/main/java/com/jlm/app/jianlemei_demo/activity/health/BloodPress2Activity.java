package com.jlm.app.jianlemei_demo.activity.health;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.graphics.Color.GREEN;

public class BloodPress2Activity extends Activity {
    private LinearLayout layout;
    private Handler mHandler = new Handler();
    private Myadapter adapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_blood_press2);
        mListView = (ListView) findViewById(R.id.health_case_bloodpress_lavipeditum_listview);
        layout = (LinearLayout) findViewById(R.id.health_case_bloodpress_lavipeditum_layout);
        adapter = new Myadapter(this);
        mListView.setAdapter(adapter);
        setResult(0, getIntent().putExtra("back", true));
    }

    protected class Myadapter extends BaseAdapter {
        private ArrayList<Lavipeditum> list = new ArrayList<>();
        private LayoutInflater inflater;
        private static final String metarial1 = "将上列药物加水4000毫升煎煮取液,先薰足后浸足,每日一次,发作时每日2次,1剂可用2~3次,10天为1疗程。";
        private static final String metarial2 = "将上药加水4000毫升煎煮取液,先薰脚后温洗双足,每日一次,1剂可用2~3次,10天为1疗程。";
        private static final String metarial3 = "将上药加水4000ml煎煮取液,先熏脚后温洗双足,每日1次,发作时每日2次,1剂可用2~3次,10天为1疗程。";
        private static final String metarial4 = "将上药装入布袋加水4000ml煎煮取液,先熏脚后温洗双足,每日1次,1剂可用2~3次,一周为1疗程,连续4个疗程,血压稳定后可改为2~3日熏泡脚一次。";
        private static final String metarial5 = "将上药共研细末,每次取10g左右加米醋调为稀糊状,外敷于双足心肾反射区,1日1换,连续5~7天。";
        boolean[] b;
        public boolean tag;
        public int tag_;

        public Myadapter(Context context) {
            inflater = LayoutInflater.from(context);
            list.add(new Lavipeditum("桑叶芹菜足浴方", "桑叶,桑枝各30g,芹菜50g", metarial1, "清肝降压"));
            list.add(new Lavipeditum("钩藤桑叶足浴方", "钩藤20克,桑叶15克,菊花20克,夏枯草30克", metarial2, "平肝潜阳,清热安神"));
            list.add(new Lavipeditum("双桑茺蔚子足浴", "桑叶、桑枝各20g,茺蔚子15g", metarial3, "利尿降压。适用于高血压引起的头痛、目赤等症。"));
            list.add(new Lavipeditum("桑寄生桑枝足浴方", "桑寄生,怀牛膝,茺蔚子,桑叶,菊花各10g,钩藤,明矾各30g,桑枝20g", metarial4, "平肝阳,益肝阴,降血压"));
            list.add(new Lavipeditum("吴朱萸肉桂糊敷穴位降血压", "吴萸、肉桂各50g", metarial5, "温经通脉,疏肝下气"));
            b = new boolean[list.size()];
            setData();
        }

        public void setData() {
            notifyDataSetChanged();
        }

        public void refresh(int positon) {
            b[positon] = !b[positon];
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Lavipeditum getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_lavipeditum_layout, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tag_ = position;
                    refresh(position);
                }
            });
            holder.setView(getItem(position));
            tag = false;
            for (boolean i : b) {
                tag = tag | i;
            }
            if (tag) {
                if (b[position]) {
                    convertView.setVisibility(View.VISIBLE);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                            layout.setBackgroundColor(Color.rgb(255,0,0));
                        }
                    });
                } else {
                    convertView.setVisibility(View.GONE);
                }
            } else {
                convertView.setVisibility(View.VISIBLE);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
            return convertView;
        }

        protected class Lavipeditum {


            private String Title;
            private String Metarial;
            private String Usage;
            private String Effect;

            public Lavipeditum(String title, String metarial, String usage, String effect) {
                this.Title = title;
                this.Effect = effect;
                this.Metarial = metarial;
                this.Usage = usage;
            }

            public String getTitle() {
                return Title;
            }

            public String getMetarial() {
                return Metarial;
            }

            public String getUsage() {
                return Usage;
            }

            public String getEffect() {
                return Effect;
            }

        }

        class ViewHolder {
            @Bind(R.id.item_lavipedium_title)
            TextView Title;
            @Bind(R.id.item_lavipedium_metarial)
            TextView Metarial;
            @Bind(R.id.item_lavipedium_usage)
            TextView Usage;
            @Bind(R.id.item_lavipedium_effect)
            TextView Effect;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

            public void setView(Lavipeditum lavipeditum) {
                this.Metarial.setText(getMetarialSpan(lavipeditum.getMetarial()));
                this.Usage.setText(getUsageSpan(lavipeditum.getUsage()));
                this.Effect.setText(getEffectSpan(lavipeditum.getEffect()));
                this.Title.setText(lavipeditum.getTitle());
                this.Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.Metarial.getTextSize() * 1.2F);
            }

            public SpannableString getUsageSpan(String string) {
                SpannableString spannableString = new SpannableString("使用方法：" + string);
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }

            public SpannableString getMetarialSpan(String string) {
                SpannableString spannableString = new SpannableString("药材：" + string);
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }

            public SpannableString getEffectSpan(String string) {
                SpannableString spannableString = new SpannableString("功效：" + string);
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (adapter.tag) {
            adapter.refresh(adapter.tag_);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}