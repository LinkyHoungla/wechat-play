import Vue from 'vue'

import { Form, FormItem, Input, Submenu, MenuItem, Container, Header, Aside, Main, Button, MessageBox, Message, Menu, Loading } from 'element-ui'

// 容器布局
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)

// 表单
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)

Vue.use(Button)

// 菜单
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)

// 导入弹窗
Vue.prototype.$message = Message
// 弹出栏
Vue.prototype.$confirm = MessageBox.confirm
// 加载
Vue.prototype.$loading = Loading.service
