package com.ghi.module.repositoryIntro.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ghi.common.model.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目仓库描述
 * @TableName t_repository_intro
 */
@TableName(value ="t_repository_intro")
@Data
@Accessors(chain = true)
public class RepositoryIntro extends BaseEntity implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 问题
     */
    @TableField(value = "issue")
    private String issue;

    /**
     * 回答
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 项目仓库ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @TableField(value = "repo_id")
    private Long repoId;

    /**
     * 回答结果 0=获取正常 1=获取异常
     */
    @TableField(value = "status")
    private String status = "0";

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}