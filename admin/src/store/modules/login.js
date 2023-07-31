/**
 * 登录 信息
 */

import { removeToken } from '@/utils/token'
import { resetRouter } from '@/router'

// 在需要重置状态时使用 getDefaultState() 函数来恢复初始值
const getDefaultState = () => {
  return {
    name: '',
    role: '',
    avatar: '',
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
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
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
  async getLoginfo ({ commit }) {
    try {
      const { getCurrent } = await import('@/api/admin')
      const { data } = await getCurrent()
      commit('SET_NAME', data.data.name)
      commit('SET_ROLE', data.data.role)
      commit('SET_AVATAR', data.data.avatar)
      commit('SET_MENU', data.data.menus)
      commit('SET_PERMISSION', data.data.permissions)
    } catch (error) {
      console.error(error)
    }
  },

  // 用户登出
  async logout ({ commit }) {
    try {
      const { logout } = await import('@/api/admin')
      await logout()
      removeToken()
      resetRouter()
      commit('RESET_STATE')
    } catch (error) {
      console.error(error)
    }
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
