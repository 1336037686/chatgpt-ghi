package com.ghi.module.repository.domain;

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
 * 项目仓库
 * @TableName t_repository
 */
@TableName(value ="t_repository")
@Data
@Accessors(chain = true)
public class Repository extends BaseEntity implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 项目名称
     */
    @TableField(value = "repo_title")
    private String repoTitle;

    /**
     * 项目地址
     */
    @TableField(value = "repo_url")
    private String repoUrl;

    /**
     * 项目描述
     */
    @TableField(value = "repo_desc")
    private String repoDesc;

    /**
     * 项目语言
     */
    @TableField(value = "repo_language")
    private String repoLanguage;

    /**
     * 点赞
     */
    @TableField(value = "stars")
    private Integer stars;

    /**
     * 分支
     */
    @TableField(value = "forks")
    private Integer forks;

    /**
     * 是否描述（是否使用chatgpt处理）
     */
    @TableField(value = "is_intro")
    private String isIntro;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}