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

// 长度校验
export function validateLength (value, minLength, maxLength) {
  const trimmedValue = value.trim() // 去除首尾空格后的值

  if (minLength && trimmedValue.length < minLength) {
    return false
  }

  if (maxLength && trimmedValue.length > maxLength) {
    return false
  }

  return true
}

// 特殊字符校验
export function containsSpecialCharacters (value) {
  // 定义特殊字符的正则表达式，这里仅列举一些常见特殊字符，你可以根据需要添加或修改
  const specialCharactersRegex = /[!@#$%^&*()+={}|[\]\\:;"'<>,.?/~`]/
  return specialCharactersRegex.test(value)
}
