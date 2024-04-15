import axios from 'axios'
import request from '@/utils/request'

export function login(data){
    return request({
        url:'/login',
        method:'post',
        data:data
    })
}

export function getInfo() {
    return axios({
        url: '/getInfo',
        method: 'get'
    })
}

export function loginout(){
    return axios({
        url:'/loginout',
        method:'post',
    })
}