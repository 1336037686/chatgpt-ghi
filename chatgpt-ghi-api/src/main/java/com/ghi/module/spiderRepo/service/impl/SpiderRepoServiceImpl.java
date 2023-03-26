package com.ghi.module.spiderRepo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghi.module.spiderRepo.domain.SpiderRepo;
import com.ghi.module.spiderRepo.service.SpiderRepoService;
import com.ghi.module.spiderRepo.mapper.SpiderRepoMapper;
import org.springframework.stereotype.Service;

/**
* @author LGX
* @description 针对表【tr_spider_repo(爬虫-项目中间表)】的数据库操作Service实现
* @createDate 2023-03-25 11:22:37
*/
@Service
public class SpiderRepoServiceImpl extends ServiceImpl<SpiderRepoMapper, SpiderRepo>
    implements SpiderRepoService{

}




