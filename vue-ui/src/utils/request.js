import axios from 'axios'
import {getToken} from "@/utils/auth";
import {Message} from "element-ui";

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: process.env.VUE_APP_BASE_API,
    // 超时
    timeout: 10000
})

service.interceptors.request.use(config=>{
    if (getToken()){
        config.headers['Authorization']='Bearer'+getToken()
    }
    return config
},
        error => {
        console.log(error)
    }
)

// 响应拦截器
service.interceptors.response.use(res => {
    const code=res.data.code|| 200;
    const msg=res.data.msg;
    if (code === 500){
        Message({ message: msg, type: 'error' })
        return Promise.reject(new Error(msg))
    }
    else if (code !== 200) {
        Notification.error({ title: msg })
        return Promise.reject('error')
    } else {
        return res.data
    }
},
)



export default service