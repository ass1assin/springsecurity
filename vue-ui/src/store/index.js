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
            login(userInfo).then(res => {
                setToken(res.data.token)
                commit('SET_TOKEN', res.data.token)
            })
        },

        // 获取用户信息
        GetInfo({commit,state}){
           getInfo().then(res=>{
               const user=res.data.user
               if (res.data.roles && res.data.roles.length > 0){
                   commit('SET_ROLES', res.data.roles)
               }
           })
        }

    },
    modules: {
    }
})