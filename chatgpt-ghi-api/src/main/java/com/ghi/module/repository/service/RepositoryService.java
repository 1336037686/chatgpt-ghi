package com.ghi.module.repository.service;

import com.ghi.module.repository.domain.Repository;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author LGX
* @description 针对表【t_repository(项目仓库)】的数据库操作Service
* @createDate 2023-03-25 11:21:29
*/
public interface RepositoryService extends IService<Repository> {

    public List<Repository> getRepositoryBySpiderRecordId(Long spiderRecordId);

}
