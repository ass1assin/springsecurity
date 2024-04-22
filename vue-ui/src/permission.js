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
        if (to.path === '/login') {
            console.log("有token")
            next({ path: '/sidebar' })
            NProgress.done()
        }
        // 有token也要判断是否有权限访问
        else if (whiteList.indexOf(to.path) !== -1) {
            NProgress.done()
            console.log("在这1")
            next()
        }
        else {
            NProgress.done()
            console.log("在这2")
            next()
        }
    }
    else {
        if (whiteList.indexOf(to.path) !== -1) {
            // 在免登录白名单，直接进入
            console.log("白名单")
            next()
        }
        else {
        console.log("没有token")
        next(`/login?redirect=${encodeURIComponent(to.fullPath)}`)
        NProgress.done()
        }
    }

})

router.afterEach(() => {
    NProgress.done()
})

