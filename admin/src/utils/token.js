/**
 * Token 权限 存储
 */

const Token = 'token'

export function getToken () {
  return sessionStorage.getItem(Token)
}

export function setToken (val) {
  return sessionStorage.setItem(Token, val)
}

export function removeToken () {
  return sessionStorage.removeItem(Token)
}
