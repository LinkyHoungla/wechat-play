import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getter'

import login from './modules/login'
import app from './modules/app'

Vue.use(Vuex)

export default new Vuex.Store({
  getters,
  modules: {
    app,
    login
  }
})
