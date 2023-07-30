<template>
  <div class="body">
    <div class="login">
      <h2>后台管理登录</h2>
      <div class="login-box">
        <input
          type="text"
          required="required"
          @blur="validateUsername"
          v-model="loginForm.username"
        />
        <label>用户名</label>
        <span v-if="error.username !== ''">{{ error.username }}</span>
      </div>
      <div class="login-box">
        <input
          type="password"
          required="required"
          @blur="validatePassword"
          v-model="loginForm.password"
        />
        <label>密码</label>
        <span v-if="error.password !== ''">{{ error.password }}</span>
      </div>
      <!-- 提交 -->
      <a @click="login">
        登录
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </a>
    </div>
  </div>
</template>

<script>
import {
  isNotEmpty,
  validateLength,
  containsSpecialCharacters
} from '@/utils/validate'
import { setToken } from '@/utils/token'

export default {
  data () {
    return {
      // 登录表单数据
      loginForm: {
        username: '',
        password: ''
      },
      // 表单错误
      error: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    // 用户名校验
    validateUsername () {
      if (!isNotEmpty(this.loginForm.username)) {
        this.error.username = '不能为空'
        return false
      }

      if (!validateLength(this.loginForm.username, 3, 10)) {
        this.error.username = '长度为3-10'
        return false
      }

      if (containsSpecialCharacters(this.loginForm.username)) {
        this.error.username = '只能字母数字'
        return false
      }

      this.error.username = ''
      return true
    },
    // 密码校验
    validatePassword () {
      if (!isNotEmpty(this.loginForm.password)) {
        this.error.password = '不能为空'
        return false
      }

      if (!validateLength(this.loginForm.password, 6, 16)) {
        this.error.password = '长度为6-16'
        return false
      }

      if (containsSpecialCharacters(this.loginForm.password)) {
        this.error.password = '只能字母数字'
        return false
      }

      this.error.password = ''
      return true
    },
    // 表单校验
    validateForm () {
      let valid = true
      valid = this.validateUsername() && this.validatePassword()
      // 其他表单字段的验证逻辑

      // 返回表单验证结果
      return valid
    },
    // 登录
    login () {
      if (!this.validateForm()) {
        this.$message.error('输入非法')
        return
      }
      import('@/api/admin').then(({ login }) => {
        login(this.loginForm).then(({ data }) => {
          setToken(data.data.token)
          this.$router.push('/')
        }).catch(() => {})
      })
    }
  }
}
</script>

<style lang="less">
.body {
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100vh;
  background: linear-gradient(#141e30, #243b55);

  .login {
    display: flex;
    flex-direction: column;
    align-items: center;

    width: 400px;
    padding: 40px;
    background: rbga(0, 0, 0, 0.2);
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.4);

    h2 {
      color: #fff;
      margin-bottom: 30px;
    }

    .login-box {
      position: relative;
      width: 100%;

      input {
        // 清除轮廓
        outline: none;
        border: none;

        width: 100%;
        padding: 10px 0;
        margin-bottom: 30px;
        border-bottom: 1px solid #fff;

        color: #fff;
        font-size: 16px;

        background-color: transparent;
      }

      label {
        position: absolute;
        top: 0;
        left: 0;

        padding: 10px 0;
        color: #fff;

        pointer-events: none;

        transition: all 0.25s;
      }

      span {
        position: absolute;
        bottom: 50%;
        right: 0;

        padding: 5px 0;

        font-size: 10px;
        color: red;
      }

      input:focus + label,
      input:valid + label {
        top: -25px;
        color: #03e9f4;
        font-size: 12px;
      }
    }

    a {
      overflow: hidden;
      position: relative;
      padding: 10px 20px;
      color: #03e9f4;

      text-decoration: none;

      transition: all 0.5s;

      span {
        position: absolute;
      }

      span:first-child {
        top: 0;
        left: -100%;

        width: 100%;
        height: 2px;

        background: linear-gradient(to right, transparent, #03e9f4);
        animation: move1 1s linear infinite;
      }

      span:nth-child(2) {
        top: -100%;
        right: 0;

        width: 2px;
        height: 100%;

        background: linear-gradient(transparent, #03e9f4);
        animation: move2 1s linear 0.25s infinite;
      }

      span:nth-child(3) {
        bottom: 0;
        right: -100%;

        width: 100%;
        height: 2px;

        background: linear-gradient(to left, transparent, #03e9f4);
        animation: move3 1s linear 0.5s infinite;
      }

      span:nth-child(4) {
        bottom: -100%;
        left: 0;

        width: 2px;
        height: 100%;

        background: linear-gradient(#03e9f4, transparent);
        animation: move4 1s linear 0.75s infinite;
      }

      @keyframes move1 {
        0% {
          left: -100%;
        }
        50%,
        100% {
          left: 100%;
        }
      }

      @keyframes move2 {
        0% {
          top: -100%;
        }
        50%,
        100% {
          top: 100%;
        }
      }

      @keyframes move3 {
        0% {
          right: -100%;
        }
        50%,
        100% {
          right: 100%;
        }
      }

      @keyframes move4 {
        0% {
          bottom: -100%;
        }
        50%,
        100% {
          bottom: 100%;
        }
      }
    }

    a:hover {
      color: #fff;
      border-radius: 5px;
      background-color: #03e9f4;
      box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
        0 0 100px #03e9f4;
    }
  }
}
</style>
