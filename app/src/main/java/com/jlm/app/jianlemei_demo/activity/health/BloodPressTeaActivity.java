package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.User.Tea;

import java.util.ArrayList;

public class BloodPressTeaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_blood_press3);
        setResult(0, getIntent().putExtra("back", true));
        ListView mListView = (ListView) findViewById(R.id.health_case_bloodpress_tea);
        final ArrayList<Tea> teaArrayList = new ArrayList<>();
        teaArrayList.add(new Tea("决明子茶", R.drawable.health_case_tea_cassia_seed, "决明子性微寒，略带青草香味，枕着睡觉闻着味道，犹如睡在青草丛中。其种子坚硬，又可对头部和颈部穴位按摩。天然的按摩师...老年人饮用决明子茶不仅有助于大便通畅，还能起到明目、降压、调脂等保健功能。"));
        teaArrayList.add(new Tea("莲子心茶", R.drawable.health_case_tea_juemingzi, "莲子心茶的功效：莲子芯茶具有清心去热、涩精、止血、止渴等功效，可治疗心衰、休克、阳痿、心烦、口渴、吐血、遗精、目赤、肿痛等病症，清心火，平肝火，泻脾火，降肺火，消暑除烦，生津止渴，治目红肿。用莲子芯冲水喝，可以治疗便秘。 有史料记载，乾隆皇帝每到避暑山庄总要用荷叶露珠炮制莲子芯茶，以养心益智，调整元气，清心火与解毒。莲子中的钙、磷和钾含量非常丰富，除可以构成骨骼和牙齿的成分外，还有促进凝血，使某些酶活化，维持神经传导性，镇静神经，维持肌肉的伸缩性和心跳的节律等作用。丰富的磷还是细胞核蛋白的主要组成部分，帮助机体进行蛋白质、脂肪、糖类代谢，并维持酸碱平衡，对精子的形成也有重要作用。"));
        teaArrayList.add(new Tea("何首乌茶", R.drawable.health_case_tea_heshouwu, "何首乌是一味具有诸多传奇功效的中药，“返老还童”、“延年益寿”说的就是何首乌。但是，何首乌有生、制之分，何首乌挖出来晒干就是生何首乌，有解毒、截疟、润肠通便的功效，常用于治疗瘰疬疮痈、风疹瘙痒、肠燥便秘等症。这样的生何首乌是没有补益作用的。"));
        teaArrayList.add(new Tea("菊花茶", R.drawable.health_case_tea_chrysanthemum_tea, "菊花茶的药效：性甘、微寒，具有散风热、平肝明目之功效。通过饮用菊花茶可以对上火引起的青春痘起到一个舒缓的作用。菊花具有清肝明目降火的作用。"));
        teaArrayList.add(new Tea("山楂茶", R.drawable.health_case_tea_hawthorn_tea, "山楂也叫山里红、红果、胭脂果，为蔷薇科植物山里红或山楂的干燥成熟果实，质硬，果肉薄，酸甜适中，风味独特。山楂有很高的营养价值和医疗价值，老年人常吃山楂制品能增强食欲，改善睡眠，保持骨骼和血液中钙的恒定，预防动脉粥样硬化，因此山楂被视为“长寿食品”。"));
        teaArrayList.add(new Tea("荷叶茶", R.drawable.health_case_tea_heye, "荷叶茶中的荷叶碱中含有多种有效的化脂生物碱，能有效分解体内的脂肪，并且强劲排出体外。荷叶碱能强悍密布人体肠壁上，形成一层脂肪隔离膜，阻止脂肪吸收，防止脂肪堆积。可以改善油腻饮食习惯，其具有较强的油脂排斥功效从而让你对荤腥油腻的食物渐渐产生反感。所以具有优秀的减肥功效及卓越的降脂保健作用。"));
        teaArrayList.add(new Tea("三七花茶", R.drawable.health_case_tea_37flour, "田七花即三七花，质脆易碎，气微，味甘微苦，降血脂、降血压、抗癌，提高心肌供氧能力，增强机体免疫功能。含有人参多种皂甙、平清热肝、降压功效、防治高血压和咽炎。清热解毒、去痘除疮、平肝凉血、降压降脂，三七花性凉味甘，有清热、平肝、降压的功效。三七花总皂甙对中枢神经系统呈抑制作用，表现为镇静、安神功效。用于高血压，头昏、目眩、耳鸣，急性咽喉炎的治疗。降血压，降血脂，减肥，生津止渴，提神补气。"));
        teaArrayList.add(new Tea("槐花茶", R.drawable.health_case_tea_huaihua, "清热，凉血，止血。清热降压，治肠风便血，痔血，尿血，血淋，崩漏，衄血，赤白痢下，风热目赤，痈疽疮毒。并用于预防中风。有较强的凉血止血功效，收敛，消炎，镇痛，降血压，强化血管。注意事项：脾胃虚寒者慎服。"));
        teaArrayList.add(new Tea("葛根茶", R.drawable.health_case_tea_gegen_tea, "葛根具有改善脑部血液循环之效，对葛根茶具有清热、分解酒精、醒酒健胃、解酒护肝等功效。对降低血脂、血压、血糖,辅助治疗冠心病、心绞痛、神经性头痛等有明显效果.酒前泡服可使酒量大增,酒后泡服可促使酒精快速分解和排泄.将其作常规饮料泡服,久服可降血脂、降血糖、降血压,养肝护胃。因高血压引起的头痛、眩晕、耳鸣及腰酸腿痛等症状有较好的缓解功效。经常饮用葛根茶对治疗高血压具有明显的疗效，其制作方法为将葛根洗净切成薄片，每天30克，加水煮沸后当茶饮用。"));
        teaArrayList.add(new Tea("桑寄生茶", R.drawable.health_case_tea_sang_tea, "桑寄生茶：降压作用该品的水浸出液、乙醇-水浸出液、30%乙醇浸出液， 均有降低麻醉动物血压的作用．麻醉猫、狗以毛叶桑寄生的茎叶混合酊剂0.4～0.5g/kg灌胃，或 0.1～0.25g/kg 静脉注射， 都有降压作用，维持时间亦较长， 重复给药无急速耐受现象，切断迷走神经或注射阿托品只能减弱而不能完全消除其降压作用， 对肾上腺素无拮抗或增强作用，降压与窦神经无关。对此降压作用的原理， 有报告认为是中枢性或反射性的，即由于中枢镇静作用和降低了交感神经及血管运动中枢的兴奋性所致；或是作用于内感受器， 引起降压反射的结果。"));
        teaArrayList.add(new Tea("玉米须茶", R.drawable.health_case_tea_corn_tea, "玉米中的纤维素含量很高，可以保护肠胃，促进肠胃蠕动，玉米须味甘性平。其中其他的营养物质能增强人体新陈代谢、调整神经系统功能，能起到使皮肤细嫩光滑，抑制、延缓皱纹产生的作用。"));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(BloodPressTeaActivity.this, TeaActivity.class).putExtra("tea", teaArrayList.get(position)));
            }
        });
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return teaArrayList.size();
            }

            @Override
            public String getItem(int position) {
                return teaArrayList.get(position).getTeaName();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = LayoutInflater.from(BloodPressTeaActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                    convertView = view;
                    convertView.setTag(view);
                } else {
                    view = (View) convertView.getTag();
                }
                ((TextView) view).setText(getItem(position));
                return view;
            }

        });
    }
}
