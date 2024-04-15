import Vue from 'vue'
import Vuex from 'vuex'
import {getInfo, login} from "@/api/common/login";
import {getToken, setToken} from "@/utils/auth";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: getToken(),
        roles: [],
    },
    getters: {
        roles: state => state.user.roles,
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
    },
    actions: {
        // 登录
        Login({commit},userInfo){
        // 没有这个会导致先触发路由守卫
        return new Promise((resolve, reject) => {
            login(userInfo).then(res => {
                console.log("res."+JSON.stringify(res.token))
                setToken(res.token)
                commit('SET_TOKEN', res.token)
                resolve()
            })
                .catch(error => {
                    reject(error)
                })
            })
        },

        // 获取用户信息
        GetInfo({commit,state}){
           getInfo().then(res=>{
               const user=res.user
               if (res.roles && res.roles.length > 0){
                   commit('SET_ROLES', res.roles)
               }
           })
        }

    },
    modules: {
    }
})