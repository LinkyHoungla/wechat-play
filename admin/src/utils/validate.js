/**
 * 输入校验
 */

// 邮箱 格式
export const mailValid = (rule, value, callback) => {
  const regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/
  if (!regex.test(value)) {
    callback(new Error('错误邮箱格式'))
  }
  callback()
}

// 手机 格式
export const phoneValid = (rule, value, callback) => {
  const regex = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
  if (!regex.test(value)) {
    callback(new Error('错误手机号码格式'))
  }
  callback()
}

// 特殊字符校验
export const specialValid = (rule, value, callback) => {
  // 定义特殊字符的正则表达式，这里仅列举一些常见特殊字符，你可以根据需要添加或修改
  const regex = /[!@#$%^&*()+={}|[\]\\:;"'<>,.?/~`]/
  if (regex.test(value)) {
    callback(new Error('非法特殊字符'))
  }
  callback()
}

// 昵称校验
export const nicknameValid = (rule, value, callback) => {
  // 定义特殊字符的正则表达式，这里仅列举一些常见特殊字符，你可以根据需要添加或修改
  const regex = /^[A-Za-z0-9_\u4e00-\u9fa5]+$/
  if (!regex.test(value)) {
    callback(new Error('非法昵称'))
  }
  callback()
}

// 用户名校验
export const usernameValid = (rule, value, callback) => {
  // 定义特殊字符的正则表达式，这里仅列举一些常见特殊字符，你可以根据需要添加或修改
  const regex = /^[a-zA-Z0-9_]*$/
  if (!regex.test(value)) {
    callback(new Error('字段非法'))
  }
  callback()
}
