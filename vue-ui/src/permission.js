import router from './router'
import {getToken} from "@/utils/auth";
import store from "@/store";
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login']

router.beforeEach((to, from, next) => {
    NProgress.start()
    console.log("还在吗")
    if (getToken()){
        // 登录之后用户想进入登录页时重定向到/sidebar
        if (to.path === '/login') {
            console.log("to.path === '/login'")
            next({ path: '/sidebar' })
            NProgress.done()
        }
        // 有token也要判断是否有权限访问
        else if (whiteList.indexOf(to.path) !== -1) {
            console.log("白名单")
            next()
        }
        else {
            // console.log("store.getters.roles"+store.getters.roles)
            if (store.getters.roles.length === 0){
                store.dispatch("GetInfo").then(()=>{
                    console.log("走的到吗")
                    store.dispatch("GenerateRoutes").then(res=>{
                        router.addRoutes(res)
                        // 继续导航到下一个路由
                        next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
                    }).catch(err=>{
                        next({ path: '/sidebar' })
                    })
                })
            }else {
                console.log("store.getters.roles.length !== 0")
                next()
            }
            // next()
        }
    }
    else {
        // 在免登录白名单，直接进入
        if (whiteList.indexOf(to.path) !== -1) {
            console.log("在免登录白名单，直接进入")
            next()
        }
        else {
        next(`/login?redirect=${encodeURIComponent(to.fullPath)}`)
            console.log("/login?redirect")
        NProgress.done()
        }
    }

})

router.afterEach(() => {
    NProgress.done()
})

