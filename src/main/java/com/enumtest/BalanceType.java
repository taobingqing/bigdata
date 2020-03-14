package com.enumtest;

import java.util.ArrayList;
import java.util.List;

public enum BalanceType {
    /**
     * 枚举定义一个属性为 DRAFT("1"), 定义两个属性为 DRAFT("1","草稿"), 定义三个属性为 DRAFT("1","草稿","已存在") ,
     * 定义四个，五个以此类推，中间以逗号分割。
     */

    //本文已定义2个属性为例

    DRAFT("1", "草稿"), SUBMIT("2", "已提交"), TICKETING("3", "出票中"), TICKETED("4", "已出票"), CANCELLING("5", "取消中"),
    CANCELLED("6", "已取消"), REFUNDING("7", "退票中"), REFUNDED("8", "已退票"), DELETE("9", "已删除");

    //创建构造函数
    BalanceType(String value, String name) {
        this.name = name;
        this.value = value;
    }

    //定义私有方法，获取枚举值
    private String name;
    private String value;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

/**
 * 定义枚举对象
 */

class EnumOrderTypeVo {

    private static final long serialVersionUID = 1L;

    private String code; //类型编号
    private String desc;  //类型描述

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}


class Test {
    public static void main(String[] args) {
        //创建枚举对象集合
        List<EnumOrderTypeVo> list = new ArrayList<EnumOrderTypeVo>();
        //变量枚举类，为枚举对象赋值


        System.out.println("==============");
        for (BalanceType type : BalanceType.values()) {
            EnumOrderTypeVo t = new EnumOrderTypeVo();
            t.setCode(type.getValue());
            t.setDesc(type.getName());
            list.add(t);
        }
        //打印枚举对象集合
        for (EnumOrderTypeVo enumOrderTypeVo : list) {
            System.out.println("编号为：" + enumOrderTypeVo.getCode() + "的类型为：" + enumOrderTypeVo.getDesc());

        }

    }
}
