package com.ghi.module.issue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghi.module.issue.domain.Issue;
import com.ghi.module.issue.service.IssueService;
import com.ghi.module.issue.mapper.IssueMapper;
import org.springframework.stereotype.Service;

/**
* @author LGX
* @description 针对表【t_issue(用户问题表)】的数据库操作Service实现
* @createDate 2023-03-25 11:20:42
*/
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue>
    implements IssueService{

}




