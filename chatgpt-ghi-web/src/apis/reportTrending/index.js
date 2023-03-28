import request from '@/utils/request';

export function getRecord() {
    return request({
        url: '/api/v1/reportTrending/getRecord',
        method: 'get',
    })
}
