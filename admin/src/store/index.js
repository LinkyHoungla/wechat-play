import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getter'

import login from './modules/login'

Vue.use(Vuex)

export default new Vuex.Store({
  getters,
  modules: {
    login
  }
})
