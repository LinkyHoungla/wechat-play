import Vue from 'vue'

import { Form, FormItem, Input, Submenu, MenuItem, Container, Header, Aside, Main, Button, MessageBox, Message, Menu } from 'element-ui'

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

Vue.use(MessageBox)
Vue.use(Message)

Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)

// 导入弹窗
Vue.prototype.$message = Message
