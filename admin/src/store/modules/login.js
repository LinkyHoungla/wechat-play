/**
 * 登录 信息
 */

import { removeToken } from '@/utils/token'
import { resetRouter } from '@/router'

// 在需要重置状态时使用 getDefaultState() 函数来恢复初始值
const getDefaultState = () => {
  return {
    name: null,
    role: null,
    menu: '',
    permission: []
  }
}

// 共同维护的一个状态，state里面可以是很多个全局状态
const state = getDefaultState()

// 处理数据的唯一途径，state的改变或赋值只能在这里
const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_ROLE: (state, role) => {
    state.role = role
  },
  SET_MENU: (state, menu) => {
    state.menu = menu
  },
  SET_PERMISSION: (state, permission) => {
    state.permission = permission
  }
}

// 数据 获取
const actions = {
  // 获取用户信息
  getLoginfo ({ commit }) {
    import('@/api/admin').then(({ getInfo }) => {
      getInfo().then(res => {
        const { data } = res.data
        commit('SET_NAME', data.name)
        commit('SET_ROLE', data.role)
        commit('SET_MENU', data.menu)
        commit('SET_PERMISSION', data.permission)
      })
    })
  },

  // 用户登出
  logout ({ commit }) {
    import('@/api/admin').then(({ logout }) => {
      logout().then(() => {
        removeToken()
        resetRouter()
        commit('RESET_STATE')
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
