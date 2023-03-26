package com.ghi.module.repository.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghi.module.repository.domain.Repository;
import com.ghi.module.repository.service.RepositoryService;
import com.ghi.module.repository.mapper.RepositoryMapper;
import com.ghi.module.spiderRepo.domain.SpiderRepo;
import com.ghi.module.spiderRepo.service.SpiderRepoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author LGX
* @description 针对表【t_repository(项目仓库)】的数据库操作Service实现
* @createDate 2023-03-25 11:21:29
*/
@Service
public class RepositoryServiceImpl extends ServiceImpl<RepositoryMapper, Repository>
    implements RepositoryService{

    @Resource
    private SpiderRepoService spiderRepoService;

    @Override
    public List<Repository> getRepositoryBySpiderRecordId(Long spiderRecordId) {
        List<SpiderRepo> spiderRepos = spiderRepoService.list(new LambdaQueryWrapper<SpiderRepo>().eq(SpiderRepo::getSpiderRecordId, spiderRecordId));
        List<Long> reportIds = spiderRepos.stream().map(SpiderRepo::getRepoId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(reportIds)) return new ArrayList<>();
        List<Repository> repositories = list(new LambdaQueryWrapper<Repository>().in(Repository::getId, reportIds));
        return repositories;
    }
}




