package com.ghi.module.reportTrending.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ghi.module.repository.domain.Repository;
import com.ghi.module.repository.service.RepositoryService;
import com.ghi.module.repositoryIntro.domain.RepositoryIntro;
import com.ghi.module.repositoryIntro.service.RepositoryIntroService;
import com.ghi.module.spiderRecord.domain.SpiderRecord;
import com.ghi.module.spiderRecord.service.SpiderRecordService;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: LGX
 * @Date: 2023/3/26 21:53
 * @Description: ReportTrendingController
 * @Version 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/reportTrending")
public class ReportTrendingController {

    @Resource
    private SpiderRecordService spiderRecordService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RepositoryIntroService repositoryIntroService;
    @Resource
    private Cache<String, Object> caffeineCache;

    @GetMapping("getRecord")
    public ResponseEntity<List<SpiderRecord>> doGetSpiderRecord() {
        if (caffeineCache.asMap().containsKey("spiderRecords")) {
            List<SpiderRecord> spiderRecords = (List<SpiderRecord>) caffeineCache.asMap().get("spiderRecords");
            return ResponseEntity.ok(spiderRecords);
        }
        List<SpiderRecord> spiderRecords = spiderRecordService.list(new LambdaQueryWrapper<SpiderRecord>()
                .eq(SpiderRecord::getStatus, "1")
                .orderByDesc(SpiderRecord::getCreateTime)
        );
        caffeineCache.put("spiderRecords", spiderRecords);
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
