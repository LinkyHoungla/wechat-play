import Vue from 'vue'

import { Form, FormItem, Input, Submenu, MenuItem, Button, MessageBox, Message, Menu, Loading, Breadcrumb, BreadcrumbItem, Card, Tag, Dialog, Col, Row, Tree, Pagination, Table, TableColumn, Select, Option, DatePicker } from 'element-ui'

// 容器布局
// Vue.use(Container)
// Vue.use(Header)
// Vue.use(Aside)
// Vue.use(Main)

// Layout布局
Vue.use(Col)
Vue.use(Row)

// 表单
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Select)
Vue.use(Option)

// 弹出框
Vue.use(Dialog)

// 标签
Vue.use(Tag)

Vue.use(Button)

// 菜单
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)

// 面包屑
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)

// 卡片
Vue.use(Card)

// 日期选择
Vue.use(DatePicker)

// 树形结构
Vue.use(Tree)

// 表格
Vue.use(Table)
Vue.use(TableColumn)

// 页码栏
Vue.use(Pagination)

// 导入弹窗
Vue.prototype.$message = Message
// 弹出栏
Vue.prototype.$confirm = MessageBox.confirm
// 加载
Vue.prototype.$loading = Loading.service
