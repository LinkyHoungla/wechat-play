import Vue from 'vue'

import App from './App.vue'

import router from './router'
import store from './store'

import './plugins/element.js'

// 路由守卫，权限控制
import '@/router/permission.js'

import '@/assets/css/global.css'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
