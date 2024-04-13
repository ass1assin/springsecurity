import router from './router'
import {getToken} from "@/utils/auth";
import store from "@/store";

router.beforeEach((to, from, next) => {
    if (getToken()){
        if (store.getters.roles.length===0){
            store.dispatch('GetInfo').then(()=>{
                // 生成路由表

            })
        }
        else {
            next()
        }
    }

})


