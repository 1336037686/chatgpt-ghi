package com.ghi.module.issue.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ghi.common.model.BaseEntity;
import lombok.Data;

/**
 * 用户问题表
 * @TableName t_issue
 */
@TableName(value ="t_issue")
@Data
public class Issue extends BaseEntity implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 唯一key
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 问题
     */
    @TableField(value = "issue")
    private String issue;

    /**
     * 答案
     */
    @TableField(value = "answer")
    private String answer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}