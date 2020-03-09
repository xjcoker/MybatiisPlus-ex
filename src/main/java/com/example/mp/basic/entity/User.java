package com.example.mp.basic.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 年龄
     */
    @TableField("AGE")
    private Integer age;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 是否删除
     */
    @TableField("DELETED")
    @TableLogic
    private String deleted;
}
