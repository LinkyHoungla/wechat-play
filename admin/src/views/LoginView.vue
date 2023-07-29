<template>
  <div class="body">
    <div class="login">
      <h2>后台管理登录</h2>
      <div class="login_box">
        <input type="text" required="required" /><label for="">用户名</label>
      </div>
      <div class="login_box">
        <input type="password" required="required" /><label for="">密码</label>
      </div>
      <a href="javascript:void(0)">
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
export default {}
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
    background: rbga(0,0,0,0.2);
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.4);

    h2 {
      color: #fff;
      margin-bottom: 30px;
    }

    .login_box {
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
      box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4, 0 0 100px #03e9f4;
    }
  }
}
</style>
