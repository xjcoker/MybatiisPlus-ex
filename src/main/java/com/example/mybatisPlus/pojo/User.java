package com.example.mybatisPlus.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiangjun.song
 * @date 2020/2/29
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = -8708317832074012736L;
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
