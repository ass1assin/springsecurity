import axios from 'axios';

export function login(query){
    return axios({
        url:'/api/login',
        method:'post',
        params: query,
    })
}

export function loginout(){
    return axios({
        url:'/api/loginout',
        method:'post',
    })
}