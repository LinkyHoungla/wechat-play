import Vue from 'vue'

import App from './App.vue'

import router from './router'
import store from './store'

import './plugins/element.js'

// 路由守卫，权限控制
import '@/router/permission.js'

import '@/assets/css/global.css'

// 自定义 组件
import FormDialog from '@/components/FormDialog.vue'
import TablePage from '@/components/TableView.vue'

Vue.component('table-page', TablePage)
Vue.component('form-dialog', FormDialog)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
