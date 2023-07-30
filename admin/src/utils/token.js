/**
 * Token 权限 存储
 */

import Cookies from 'js-cookie'

const Token = 'token'

export function getToken () {
  return Cookies.get(Token)
}

export function setToken (val) {
  return Cookies.set(Token, val)
}

export function removeToken () {
  return Cookies.remove(Token)
}
