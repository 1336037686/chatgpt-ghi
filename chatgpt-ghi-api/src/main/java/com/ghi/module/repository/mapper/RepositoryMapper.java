package com.ghi.module.repository.mapper;

import com.ghi.module.repository.domain.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author LGX
* @description 针对表【t_repository(项目仓库)】的数据库操作Mapper
* @createDate 2023-03-25 11:21:29
* @Entity com.ghi.module.repository.domain.Repository
*/
@Mapper
public interface RepositoryMapper extends BaseMapper<Repository> {

}




