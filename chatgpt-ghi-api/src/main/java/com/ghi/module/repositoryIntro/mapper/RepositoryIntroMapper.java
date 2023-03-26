package com.ghi.module.repositoryIntro.mapper;

import com.ghi.module.repositoryIntro.domain.RepositoryIntro;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author LGX
* @description 针对表【t_repository_intro(项目仓库描述)】的数据库操作Mapper
* @createDate 2023-03-25 11:21:55
* @Entity com.ghi.module.repositoryIntro.domain.RepositoryIntro
*/
@Mapper
public interface RepositoryIntroMapper extends BaseMapper<RepositoryIntro> {

}




