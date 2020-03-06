package com.example.mp;

import com.baomidou.mybatisplus.annotation.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * mp表、字段相关注解
 * @author xiangjun.song
 * @date 2020/2/29
 */
@TableName(value = "表名", schema = "schema", keepGlobalPrefix = false, resultMap = "xmlMapId", autoResultMap = false)
public class TestAnotation implements Serializable{
    private static final long serialVersionUID = 6515452541288753207L;
    @TableId(value = "id", type = IdType.AUTO)//NONE INPUT ASSIGN_ID ASSIGN_UUID
    private Long id;
    @TableField(value = "name", exist = true, condition = "备注名", select = true, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, fill = FieldFill.INSERT_UPDATE)
    private String name;
    @TableField(value = "version", exist = true, condition = "%s=#{%s}", update = "%s+1", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private String version;
    @TableField(value = "time", keepGlobalFormat = true, jdbcType = JdbcType.TIMESTAMP, typeHandler = UnknownTypeHandler.class)
    private LocalDateTime time;
    @TableField(value = "num", numericScale = "2")
    private Double num;
//    @Version
//    @EnumValue
//    @TableLogic
//    @SqlParser
//    @KeySequence
}
