import axios from 'axios'
import request from '@/utils/request'

export function login(data){
    return request({
        url:'/login',
        headers: {
            isToken: false,
        },
        method:'post',
        data:data
    })
}

export function getInfo() {
    return request({
        url: '/getInfo',
        method: 'get'
    })
}

// export function loginout(){
//     return axios({
//         url:'/loginout',
//         method:'post',
//     })
// }