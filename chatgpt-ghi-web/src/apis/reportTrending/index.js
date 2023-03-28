import request from '@/utils/request';

export function getRecordApi() {
    return request({
        url: '/api/v1/reportTrending/getRecord',
        method: 'get',
    })
}

export function getRepositoryApi(recordId) {
    return request({
        url: '/api/v1/reportTrending/getRepository/' + recordId,
        method: 'get',
    })
}

export function getIntroApi(repoId) {
    return request({
        url: '/api/v1/reportTrending/getIntro/' + repoId,
        method: 'get',
    })
}
