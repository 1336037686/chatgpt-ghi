package com.ghi.module.spiderRepo.mapper;

import com.ghi.module.spiderRepo.domain.SpiderRepo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author LGX
* @description 针对表【tr_spider_repo(爬虫-项目中间表)】的数据库操作Mapper
* @createDate 2023-03-25 11:22:37
* @Entity com.ghi.module.spiderRepo.domain.SpiderRepo
*/
@Mapper
public interface SpiderRepoMapper extends BaseMapper<SpiderRepo> {

}




