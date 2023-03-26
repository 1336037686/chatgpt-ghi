package com.ghi.module.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghi.module.repository.domain.Repository;
import com.ghi.module.repository.service.RepositoryService;
import com.ghi.module.repository.mapper.RepositoryMapper;
import org.springframework.stereotype.Service;

/**
* @author LGX
* @description 针对表【t_repository(项目仓库)】的数据库操作Service实现
* @createDate 2023-03-25 11:21:29
*/
@Service
public class RepositoryServiceImpl extends ServiceImpl<RepositoryMapper, Repository>
    implements RepositoryService{

}




