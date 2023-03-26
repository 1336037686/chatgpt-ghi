package com.ghi.module.spiderRepo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ghi.common.model.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 爬虫-项目中间表
 * @TableName tr_spider_repo
 */
@TableName(value ="tr_spider_repo")
@Data
@Accessors(chain = true)
public class SpiderRepo extends BaseEntity implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 爬虫记录ID
     */
    @TableField(value = "spider_record_id")
    private Long spiderRecordId;

    /**
     * 项目仓库ID
     */
    @TableField(value = "repo_id")
    private Long repoId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}