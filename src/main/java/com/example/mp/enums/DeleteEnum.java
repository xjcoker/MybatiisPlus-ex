package com.example.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author xiangjun.song
 * @since 2020-03-09
 */
public enum DeleteEnum implements IEnum<Integer> {
    UN(0, "未删除"),
    HAVE(1, "已删除");

    @EnumValue    //标记数据库存的值是code
    private int value;

    @JsonValue    //标记响应json值
    private String desc;

    DeleteEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

//    @Override
//    public String toString() {
//        return "" + value + "";
//    }

    @Override public Integer getValue() {
        return this.value;
    }
}