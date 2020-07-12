package com.dd.ddfgm.enums;

import lombok.Getter;

@Getter
public enum JobsEnum implements JobEnum{
    HEIANWUSHI("9_0", "黑暗武士"),
    GUIJIANSHI("0_0", "鬼剑士 (未转职)"),
    GUIJIANSHIZHUANZHI1("0_1", "鬼剑士 (剑魂)"),
    GUIJIANSHIZHUANZHI2("0_2", "鬼剑士 (鬼泣)"),
    GUIJIANSHIZHUANZHI3("0_3", "鬼剑士 (大红狗)"),
    GUIJIANSHIZHUANZHI4("0_4", "鬼剑士 (阿修罗)"),
    KUANGZHANSHI("0_19", "鬼剑士 (狱血魔神)"),
    JIANHUN("0_17", "鬼剑士 (剑圣)"),
    GUIQI("0_20", "鬼剑士 (大暗黑天)"),
    AXIULUO("0_18", "鬼剑士 (鬼泣)"),

    NVGEDOUJIA("1_0", "女格斗家 (未转职)"),
    NVGEDOUJIAZHUANZHI1("1_1", "女格斗家 (气功)"),
    NVGEDOUJIAZHUANZHI2("1_2", "女格斗家 (散打)"),
    NVGEDOUJIAZHUANZHI3("1_3", "女格斗家 (街霸)"),
    NVGEDOUJIAZHUANZHI4("1_4", "女格斗家 (柔道)"),
    NVQIGONG("1_19", "女格斗家 (毒王)"),
    NVWUSHEN("1_17", "女格斗家 (百花缭乱)"),
    NVDUWANG("1_20", "女格斗家 (暴风眼)"),
    NVROUDAO("1_18", "女格斗家 (武神)"),

    NANSHENQIANG("2_0", "男神枪手 (未转职)"),
    NANSHENQIANGZHUANZHI1("2_1", "男神枪手 (漫游)"),
    NANSHENQIANGZHUANZHI2("2_2", "男神枪手 (大炮)"),
    NANSHENQIANGZHUANZHI3("2_3", "男神枪手 (机械)"),
    NANSHENQIANGZHUANZHI4("2_4", "男神枪手 (弹药)"),
    NANMANYOU("2_17", "男神枪手 (枪神)"),
    NANDAQIANG("2_18", "男神枪手 (狂暴者)"),
    NANJIXIE("2_19", "男神枪手 (机械战神)"),
    NANDANYAO("2_20", "男神枪手 (大将军)"),

    NVMOFA("3_0", "女魔法师 (未转职)"),
    NVMOFAZHUANZHI1("3_1", "女魔法师 (元素)"),
    NVMOFAZHUANZHI2("3_2", "女魔法师 (召唤)"),
    NVMOFAZHUANZHI3("3_3", "女魔法师 (战法)"),
    NVMOFAZHUANZHI4("3_4", "女魔法师 (魔法师)"),
    NVYUANSU("3_17", "女魔法师 (大魔导师)"),
    NVZHAOHUAN("3_18", "女魔法师 (月之女皇)"),
    NVZHANFA("3_19", "女魔法师 (贝亚娜斗神)"),
    NVMODAO("3_20", "女魔法师 (魔术师)"),

    SHENGZHIZHE("4_0", "圣职者 (未转职)"),
    SHENGZHIZHEZHUANZHI1("4_1", "圣职者 (奶爸)"),
    SHENGZHIZHEZHUANZHI2("4_2", "圣职者 (蓝拳)"),
    SHENGZHIZHEZHUANZHI3("4_3", "圣职者 (驱魔)"),
    SHENGZHIZHEZHUANZHI4("4_4", "圣职者 (黑鬼)"),
    NAIBA("4_17", "圣职者 (天启者)"),
    LANQUAN("4_18", "圣职者 (神之手)"),
    QVMO("4_19", "圣职者 (龙斗士)"),
    FUCHOUZHE("4_20", "圣职者 (末日守护者)"),

    NVSHENQIANG("5_0", "女神枪手 (未转职)"),
    NVSHENQIANGZHUANZHI1("5_1", "女神枪手 (漫游)"),
    NVSHENQIANGZHUANZHI2("5_2", "女神枪手 (枪炮)"),
    NVSHENQIANGZHUANZHI3("5_3", "女神枪手 (机械)"),
    NVSHENQIANGZHUANZHI4("5_4", "女神枪手 (弹药)"),
    NVMANYOU("5_17", "女神枪手 (沾血蔷薇)"),
    NVDAQIANG("5_18", "女神枪手 (重炮掌控者)"),
    NVJIXIE("5_19", "女神枪手 (机械之心)"),
    NVDANYAO("5_20", "女神枪手 (战争女神)"),

    ANYESHIZHE("6_0", "暗夜使者 (未转职)"),
    ANYESHIZHEZHUANZHI1("6_1", "暗夜使者 (刺客)"),
    ANYESHIZHEZHUANZHI2("6_2", "暗夜使者 (已转职)"),
    CIKE("6_17", "暗夜使者 (银月)"),
    SILINGSHUSHI("6_18", "暗夜使者 (灵魂收割者)"),

    NANGEDOU("7_0", "男格斗家 (未转职)"),
    NANGEDOUZHUANZHI1("7_1", "男格斗家 (狂虎帝)"),
    NANGEDOUZHUANZHI2("7_2", "男格斗家 (武极)"),
    NANGEDOUZHUANZHI3("7_3", "男格斗家 (千手罗汉)"),
    NANGEDOUZHUANZHI4("7_4", "男格斗家 (风林火山)"),
    NANQIGONG("7_17", "男格斗家 (狂虎帝)"),
    NANSANDA("7_18", "男格斗家 (武极)"),
    NANJIEBA("7_19", "男格斗家 (千手罗汉)"),
    NANROUDAO("7_20", "男格斗家 (风林火山)"),

    NANMOFA("8_0", "男魔法师 (未转职)"),
    NANMOFAZHUANZHI("8_1", "男魔法师 (已转职)"),
    NANMOFAZHUANZHI2("8_2", "男魔法师 (已转职)"),
    NANYUANSU("8_17", "男魔法师 (元素爆破师)"),
    NANBINGDONG("8_18", "男魔法师 (冰冻之心)"),

    ;
    private String job;
    private String gameCareer;

    JobsEnum(String job, String gameCareer) {
        this.job = job;
        this.gameCareer = gameCareer;
    }

    @Override
    public String getJob() {
        return job;
    }
}
