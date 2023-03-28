import axios from 'axios';

function myAxios(axiosConfig) {
    const service = axios.create({
        baseURL: 'http://localhost:8082', // 设置统一的请求前缀
        timeout: 10000, // 设置统一的超时时长
    });

    return service(axiosConfig)
}

export default myAxios;
