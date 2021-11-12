import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: './table.json',
        method: 'get',
        params: query
    });
};

export const userInfoData = query => {
    return request({
        url: './userInfo.json',
        method: 'get',
        params: query
    });
};
