import axios from 'axios';

export function login(data){
    return axios({
        url:'/api/login',
        method:'post',
        data: data
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
        url:'/api/loginout',
        method:'post',
    })
}