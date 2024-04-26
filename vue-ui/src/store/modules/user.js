import {getInfo, login} from "@/api/common/login";
import {getToken, setToken} from "@/utils/auth";

const user =   {
    state: {
        token: getToken(),
        roles: [],
        permissions:[],
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions
        },
    },
    // Action 提交的是 mutation，而不是直接变更状态。
    actions: {
        // 登录
        Login({commit},userInfo){
            // 没有return new Promise，外部调用 Login 方法时就无法使用 then 和 catch 来处理异步操作的结果，因为没有promise对象，不知道异步操作结果
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
            return new Promise((resolve, reject) => {
                console.log("在这捏")
            getInfo().then(res=>{
                console.log("222222"+JSON.stringify(res))
                const user=res.user
                if (res.roles && res.roles.length > 0){
                    commit('SET_ROLES', res.roles)
                    commit('SET_PERMISSIONS', res.permissions)
                    resolve()
                }
            }).catch(error=>{
                reject(error)
            })
            })
        }

    },
}
export default user
