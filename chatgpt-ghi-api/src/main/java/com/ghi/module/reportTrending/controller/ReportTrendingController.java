package com.ghi.module.reportTrending.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ghi.module.repository.domain.Repository;
import com.ghi.module.repository.service.RepositoryService;
import com.ghi.module.repositoryIntro.domain.RepositoryIntro;
import com.ghi.module.repositoryIntro.service.RepositoryIntroService;
import com.ghi.module.spiderRecord.domain.SpiderRecord;
import com.ghi.module.spiderRecord.service.SpiderRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: LGX
 * @Date: 2023/3/26 21:53
 * @Description: ReportTrendingController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("api/v1/reportTrending")
public class ReportTrendingController {

    @Resource
    private SpiderRecordService spiderRecordService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RepositoryIntroService repositoryIntroService;

    @GetMapping("getRecord")
    public ResponseEntity<List<SpiderRecord>> doGetSpiderRecord() {
        List<SpiderRecord> spiderRecords = spiderRecordService.list(new LambdaQueryWrapper<SpiderRecord>()
                .eq(SpiderRecord::getStatus, "1")
                .orderByDesc(SpiderRecord::getCreateTime)
        );
        return ResponseEntity.ok(spiderRecords);
    }

    @GetMapping("getRepository/{spiderRecordId}")
    public ResponseEntity<List<Repository>> doGetRepository(@PathVariable("spiderRecordId") Long spiderRecordId) {
        List<Repository> repositories = repositoryService.getRepositoryBySpiderRecordId(spiderRecordId);
        return ResponseEntity.ok(repositories);
    }


    @GetMapping("getIntro/{repoId}")
    public ResponseEntity<List<RepositoryIntro>> doGetIntro(@PathVariable("repoId") Long repoId) {
        List<RepositoryIntro> intros = repositoryIntroService.list(new LambdaQueryWrapper<RepositoryIntro>().eq(RepositoryIntro::getRepoId, repoId));
        return ResponseEntity.ok(intros);
    }


}
