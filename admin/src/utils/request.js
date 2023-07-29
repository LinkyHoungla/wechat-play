/**
 * 异步请求 封装
 */

import axios from 'axios'

import { getToken } from './token'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'

// 添加一个标志变量，避免多个弹窗
let errorShown = false

const service = axios.create({
  baseURL: process.env.BASE_API,
  timeout: 5000
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 获取当前用户的权限ID列表（这里假设存储在vuex中）
    const userPermissions = store.state.log.permission
    // 获取请求所需的权限ID（这里假设存储在meta字段中）
    const requiredPermission = config.meta.rid

    // 判断用户是否有权限进行该请求
    if (requiredPermission && !userPermissions.includes(requiredPermission)) {
      // 没有权限，拒绝请求或者返回错误信息
      return Promise.reject(new Error('权限不足'))
    }
    config.headers.Authorization = getToken
    return config
  },
  error => {
    // 请求错误
    console.log('request:' + error)
    // 信息提示框
    Message.error('请求失败')
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => response,
  error => {
    // 451：未登录，452：令牌过期，453：令牌无效
    if (!errorShown && (error.code === 451 || error.code === 452 || error.code === 453)) {
      MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        store.dispatch('login/logout').then(() => {
          // 为了重新实例化vue-router对象 避免bug
          location.reload()
        })
      }).finally(() => { errorShown = false })

      // 设置标志变量为true，表示已经弹出了错误提示
      errorShown = true
    } else {
      console.log('response:' + error)
      Message.error(error)
    }
  }
)

export default service
