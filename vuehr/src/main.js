import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import store from "./store";

import {postKeyValueRequest, postRequest, deleteRequest, putRequest, getRequest} from "@/utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.deleteRequest = deleteRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.getRequest = getRequest
import {initMenu} from "@/utils/menu";
import 'font-awesome/css/font-awesome.min.css'


Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.prototype.$ELEMENT = {size: 'small'};

router.beforeEach((to, from, next) => {
    if (to.path === '/') {
        next()
    } else {
        if (window.sessionStorage.getItem("user")) {
            initMenu(router, store)
            next();
        } else {
            next('/?redirect='+to.path);
        }
    }
})

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')
