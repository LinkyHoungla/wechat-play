/**
 * 页面 控制
 */

const state = {
  isCollapse: false
}

const mutations = {
  SET_COLLAPSE: state => {
    state.isCollapse = !state.isCollapse
  }
}

const actions = {
  changeCollapse ({ commit }) {
    commit('SET_COLLAPSE')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
