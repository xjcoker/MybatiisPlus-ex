package com.example.mp.basic.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.example.mp.enums.DeleteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  用户实体类
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "USER", autoResultMap = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
     * 版本号
     * 当要[更新]一条记录的时候，希望这条记录没有被别人更新
     */
    @Version
    private Integer version;

    /**
     * 是否删除
     */
    @TableField("DELETED")
    @TableLogic
    private DeleteEnum deleted;

//    /**
//     * 预留填充字段
//     * 字段填充配置参见{@link com.example.mp.config.MyMetaObjectHandler}
//     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String fillField;
}
