package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.jlm.app.jianlemei_demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home_SOSActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sos);
        listView = (ListView) findViewById(R.id.list_sos);
        final List<Map<String, Object>> list = getData();
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.item_sos,
                new String[]{"image", "text"},
                new int[]{R.id.image_sos, R.id.text_sos});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity((Intent) list.get(position).get("intent"));
            }
        });
    }

    private List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("image", R.drawable.xinzangzhouting_1);
        map.put("text", "心脏骤停");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.xinzangzhouting).putExtra("txt", "    心脏骤停的抢救必须争分夺秒，千万不要坐等救护车到来再送医院救治。要当机立断采取以下急救措施进行心肺复苏。\n" +
                "    \n" +
                "    1、叩击心前区：一手托病人颈后向上托，另一手按住病人前额向后稍推，使下颌上翘，头部后仰，有利于通气。用拳头底部多肉部分，在胸骨中段上方，离胸壁20～30厘米处，突然、迅速地捶击一次。若无反应，当即做胸外心脏按压。让病人背垫一块硬板，同时做口对口人工呼吸。观察病人的瞳孔，若瞳孔缩小（是最灵敏、最有意义的生命征象），颜面、口唇转红润，说明抢救有效。\n" +
                "    2、针刺人中穴或手心的劳宫穴、足心涌泉穴，起到抢救作用。\n" +
                "    3、迅速掏出咽部呕吐物，以免堵塞呼吸道或倒流入肺，引起窒息和吸入性肺炎。\n" +
                "    4、头敷冰袋降温。\n" +
                "    5、急送医院救治。"));
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("image", R.drawable.tangshang_1);
        map.put("text", "烫伤");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.tangshang).putExtra("txt", "      急救办法：一旦发生烫伤后，立即将被烫部位放置在流动的水下冲洗或是用凉毛巾冷敷，如果烫伤面积较大，伤者应该将整个身体浸泡在放满冷水的浴缸中。可以将纱布或是绷带松松地缠绕在烫伤处以保护伤口。\n" +
                "    禁止：不能采用冰敷的方式治疗烫伤，冰会损伤已经破损的皮肤导致伤口恶化。不要弄破水泡，否则会留下疤痕。也不随便将抗生素药膏或油脂涂抹在伤口处，这些黏糊糊的物质很容易沾染脏东西。\n" +
                "    注意事项：三级烫伤、触电灼伤以及被化学品烧伤务必到医院就医。另外，如果病人出现咳嗽、眼睛流泪或者呼吸困难，则需要专业医生的帮助。二级烫伤如果面积大于手掌的话，患者也应去医院看看，专业的处理方式可以避免留下疤痕。 "));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.shiwuzhongdu_1);
        map.put("text", "食物中毒");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.shiwuzhongdu).putExtra("txt", "    1、如进食的时间在一至两小时前，可采取用筷子或手指刺激咽部帮助催吐，尽快排出毒物。\n" +
                "    2、如进食中毒食物时间已超过两小时，但精神较好，则可服用泻药，促使中毒食物尽快排出体外。 \n" +
                "    3、大量饮水，稀释毒素浓度。 \n" +
                "    4、如果吃了变质的鱼、虾、蟹等而引起食物中毒，可取食醋100亳升，加水200亳升，稀释后一次服下。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.naoyixue_1);
        map.put("text", "脑溢血");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.naoyixue).putExtra("txt", "    1、家属要克制感情，马上拨打120，切勿为了弄醒病人而大声叫喊或猛烈摇动昏迷者，否则只会使病情迅速恶化。\n" +
                "    2、将病人平卧于床，由于脑压升高，此类患者极易发生喷射性呕吐，如不及时清除呕吐物，可能导致脑溢血昏迷者因呕吐物堵塞气道窒息而死。因此病人的头必须转向一侧，这样呕吐物就能流出口腔。\n" +
                "    3、可用冰袋或冷毛巾敷在病人前额，以利止血和降低脑压。\n" +
                "    4、平稳及时送往医院就医。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.nishui_1);
        map.put("text", "溺水");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.nishui).putExtra("txt", "    1、立即清除口鼻内的异物，保持呼吸道畅通。\n" +
                "    2、迅速进行控水。方法是：把溺者放在斜坡地上，使其头向低处俯卧，压其背部，将水控出。如无斜坡，救护者一腿跪地，另一腿屈膝，将患者腹部横置于屈膝的大腿上，头部下垂，按压其背部，将口、鼻、肺部及胃内积水倒出。\n" +
                "    3、对呼吸已停止的溺水者，应立即进行人工呼吸。方法是：将溺水者仰卧位放置，抢救者一手捏住溺水者的鼻孔，一手掰开溺水者的嘴，深吸一口气，迅速口对口吹气，反复进行，直到恢复呼吸。人工呼吸频率每分钟16-20次。\n" +
                "    4、如呼吸心跳均已停止，应立即做心肺复苏抢救。方法是：抬起溺水者的下巴，保证气道畅通，将一只手的掌根放在另一只手上置于胸骨中段进行心脏按压，垂直方向下压，下压要慢，放松时要快；成人保持至少100次/分的频率，下压深度为至少5厘米。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.meiqizhongdu_1);
        map.put("text", "煤气中毒");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.meiqizhongdu).putExtra("txt", "    急救法是立即打开门窗，关闭煤气阀门，把中毒者移到空气流通处，解开病人衣扣使呼吸流畅，注意卧床、保暖，防受凉形成肺炎。给他喝热茶，作深呼吸，没有缓解的迅速送医院抢救。 "));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.guzhe_1);
        map.put("text", "骨折");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.guzhe).putExtra("txt", "    急救措施：不要移动身体，尽快把伤到的肢体用夹板固定住。夹板可用木片或折叠起来的报纸或杂志制成，放在受伤的肢体下面或侧面，用三角形绷带、皮带或领带缠住夹板和受伤的肢体。不要缠得太用力，不要用纱布或细绳子，这些都可能阻碍血液循环。\n" +
                "    注意事项：凡有骨折可疑的病人，均应按骨折处理。首先抢救生命；如病人处于休克状态中，应以抗休克为首要任务；注意保温，有条件时应输血、输液。对处于昏迷的病人，应注意保证呼吸道通畅。闭合性骨折有穿破皮肤，损伤血管、神经的危险时，应尽量消除显著的移位，然后用夹板固定。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.fashao_1);
        map.put("text", "发烧");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.fashao).putExtra("txt", "    1、用稍凉的毛巾（约25℃）在额头、脸上擦拭。 \n" +
                "    2、将衣物解开，用温水（37℃左右）泡澡，可使皮肤的血管扩张，体热散出。每次泡澡约十至十五分钟，约四至六小时一次。 \n" +
                "    3、肛温38℃以上者可使用冷水枕，以利用较低的温度作局部散热。  \n" +
                "    4、用温水加上 70% 的酒精，以一比一的比例稀释，稀释后的水温约为 37℃ 至 40℃，再擦拭四肢及背部。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.dianxian_1);
        map.put("text", "癫痫");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.dianxian).putExtra("txt", "    1、需要患者置于平坦和周围没有障碍的平面，尽量减少发作时肢体抽动时导致骨折等意外出现。注意使患者保持侧卧位，有助于防止发作时口腔分泌物或者呕吐物阻塞呼吸道而引发窒息。\n" +
                "    2、应迅速解开衣服或去除领带，保持呼吸道通畅。\n" +
                "    3、抽搐发作时患者牙关紧闭，此时不要强行撬开患者的牙关，以免牙齿脱落阻塞呼吸道，可用力抵住患者的下颌，减少舌咬伤的机会。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.chudian_1);
        map.put("text", "触电");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.chudian).putExtra("txt", "    1、火速切断电源。立即拉下闸门或电源开关，拨掉插头，使触电者尽快脱离电源。施救者利用竹竿、扁担、木棍、塑胶制品、橡胶制品、皮制品等挑开接触病人的电源，使病人迅速脱离电源。 \n" +
                "    2、未切断电源之前，抢救者切忌用手直接拉碰触电者，这样会导致自己也立即触电而伤，因为人体是导体，极易传电。 \n" +
                "    3、如患者仍在漏电的机器上，应赶快用干燥的绝缘棉衣、棉被将病人推拉开。 \n" +
                "    4、确认触电者心跳停止时，急救者在用人工呼吸和胸外心脏挤压后，才可使用强心剂。 \n" +
                "    5、触电灼烧伤患者应合理包扎。在高空高压线触电抢救中，要注意再摔伤的可能性。 \n" +
                "    6、急救者宜穿胶鞋，跳在木板上保护自我。心跳呼吸停止的触电者可往静脉注射肾上腺素或异丙肾上腺素。血压低者可注射阿拉明、多巴胺。呼吸不规则者可注射尼可刹米、山梗菜碱。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.yiwuruyan_1);
        map.put("text", "异物入眼");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.yiwuruyan).putExtra("txt", "    1：不能揉眼睛，无论多么细小的异物都会划伤眼角膜并导致感染。如果异物进入眼部较深的位置，那么务必立即就医，请医生来处理。\n" +
                "    2：如果是腐蚀性液体溅入眼中，必须马上去医院进行诊治；倘若经过自我处理后眼部仍旧不适，出现灼烧、水肿或是视力模糊的情况，也需要请医生借助专业仪器来治疗，切不可鲁莽行事。\n" +
                "    3：首先是用力且频繁地眨眼，用泪水将异物冲刷出去。如果不奏效，就将眼皮捏起，然后在水龙头下冲洗眼睛。注意一定要将隐形眼镜摘掉。"));
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.shouzhiqieduan_1);
        map.put("text", "手指切伤");
        map.put("intent", new Intent(this, SOSDetailActivity.class).putExtra("pic", R.drawable.shouzhiqieduan).putExtra("txt", "    1、若出血较少且伤势并不严重，可在清洗之后，以创可贴覆于伤口。不主张在伤口上涂抹红药水或止血粉之类的药物，只要保持伤口干净即可。\n" +
                "    2、若伤口大且出血不止，应先止住流血，然后立刻赶往医院。具体止血方法是：伤口处用干净纱布包扎，捏住手指根部两侧并且高举过心脏，因为此处的血管是分布在左右两侧的，采取这种手势能有效止住出血。使用橡皮止血带效果会更加好，但要注意，每隔20-30分钟必须将止血带放松几分钟，否则容易引起手指缺血坏死。"));
        list.add(map);


        return list;
    }

}
