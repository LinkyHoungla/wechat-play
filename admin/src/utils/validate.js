/**
 * 输入校验
 */

// 是否 外部连接
export function isExternal (path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

// 是否 为空
export function isNotEmpty (value) {
  return value.trim() !== ''
}

// 邮箱 格式
export function isValidEmail (value) {
  const emailPattern = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/
  return emailPattern.test(value)
}

// 手机 格式
export function isValidPhoneNumber (value) {
  const phoneRegex = /^[1][3-9]\d{9}$/
  return phoneRegex.test(value)
}
