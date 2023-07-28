/**
 * 登录 信息
 */

import { removeToken } from '@/utils/token'
import { resetRouter } from '@/router'

// 在需要重置状态时使用 getDefaultState() 函数来恢复初始值
const getDefaultState = () => {
  return {
    id: null,
    rid: null,
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
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_RID: (state, rid) => {
    state.rid = rid
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
  // 用户登录
  getLoginfo ({ commit }) {
    import('@/api/admin').then(getLoginfo => {
      getLoginfo().then(res => {
        const { data } = res.data
        commit('SET_ID', data.id)
        commit('SET_RID', data.rid)
        commit('SET_MENU', data.menu)
        commit('SET_PERMISSION', data.permission)
      })
    })
  },

  // 用户登出
  logout ({ commit }, id) {
    import('@/api/admin').then(logout => {
      logout(id).then(() => {
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
