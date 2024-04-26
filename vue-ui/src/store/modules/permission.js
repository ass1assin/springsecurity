import {getRouters} from "@/api/common/menu";
import router, {dynamicRoutes,constantRoutes} from "@/router";
import auth from "@/plugins/auth";

const permission = {
    state: {
        routes: [],
        addRoutes: [],
        sidebarRouters: []
    },
    mutations: {
        SET_ROUTES: (state, routes) => {
            state.addRoutes = routes
            state.routes = constantRoutes.concat(routes)
        },
        SET_SIDEBAR_ROUTERS: (state, routes) => {
            state.sidebarRouters = routes
        },
    },
    actions: {
        // 生成路由
        GenerateRoutes({ commit }) {
            return new Promise(resolve => {
                // 向后端请求路由数据
                getRouters().then(res => {
                    // rewriteRoutes.push({ path: '*', redirect: '/404', hidden: true })
                    const rdata = JSON.parse(JSON.stringify(res.data))
                    const sideRoutes = filterAsyncRouter(rdata, false, true)
                    const asyncRoutes = filterDynamicRoutes(dynamicRoutes);
                    router.addRoutes(asyncRoutes);//添加路由配置
                    commit('SET_ROUTES',sideRoutes)
                    resolve()
                })
            })
        }
    }
}

//从拿到后端数据转换为路由对象
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
    return asyncRouterMap.filter(route => {
        if (route.component) {
                route.component = route.component = (resolve) => require([`@/view/${route.component}`], resolve)
        }
        return true
    })
}


// 动态路由遍历，验证是否具备权限
export function filterDynamicRoutes(routes) {
    const res = []
    routes.forEach(route => {
        // 判断权限
        if (route.permissions) {
            if (auth.hasPermiOr(route.permissions)) {
                res.push(route)
            }
        }
        // 判断角色
        else if (route.roles) {
            if (auth.hasRoleOr(route.roles)) {
                res.push(route)
            }
        }
    })
    return res
}


export default permission