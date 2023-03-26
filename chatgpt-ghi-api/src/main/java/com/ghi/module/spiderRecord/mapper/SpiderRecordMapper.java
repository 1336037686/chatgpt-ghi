package com.ghi.module.spiderRecord.mapper;

import com.ghi.module.spiderRecord.domain.SpiderRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author LGX
* @description 针对表【t_spider_record(抓取记录)】的数据库操作Mapper
* @createDate 2023-03-25 11:22:12
* @Entity com.ghi.module.spiderRecord.domain.SpiderRecord
*/
@Mapper
public interface SpiderRecordMapper extends BaseMapper<SpiderRecord> {

}




