package com.ghi.module.spiderRecord.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ghi.common.model.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 抓取记录
 * @TableName t_spider_record
 */
@TableName(value ="t_spider_record")
@Data
@Accessors(chain = true)
public class SpiderRecord extends BaseEntity implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 唯一key
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 抓取状态 0=失败 1=成功
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}