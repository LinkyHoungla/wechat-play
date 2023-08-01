<template>
  <div class="app-wrapper">
    <Sidebar class="side-container" />
    <div class="main-container" >
      <Navbar class="nav-container" />
      <AppMain class="app-container" />
      <div class="footer">Powered by Linky Houngla</div>
    </div>
  </div>
</template>

<script>
import { Sidebar, AppMain, Navbar } from '@/layout/components'

export default {
  name: 'LayoutView',
  components: {
    Sidebar,
    AppMain,
    Navbar
  }
}
</script>

<style lang="less" scoped>
.app-wrapper {
  position: relative;
  display: flex;
  justify-content: center;

  height: 100vh;
  width: 100%;
  background: url('@/assets/home-background.png');

  .side-container {
    position: absolute;
    top: 50%;
    left: 50px;
    z-index: 999;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    overflow: hidden;

    width: 130px;
    min-height: 750px;

    padding: 25px 10px;
    border-radius: 15px;

    background-color: #000b;
    color: #fffc;

    text-align: center;

    transform: translateY(-50%);
    transition: all 0.5s;
  }

  .side-container:hover {
    width: 260px;
  }

  .main-container {
    width: 80%;
    height: 100vh;

    background-color: transparent;

    .nav-container {
      display: flex;
      justify-content: center;
      align-items: center;

      height: 50px;
      border-radius: 10px;
      background-color: #1c3738;
    }

    .app-container {
      display: flex;
      flex-direction: column;
      align-content: center;

      width: 100%;
      height: 85%;
      margin-bottom: 20px;
    }

    .footer {
      text-align: center;
      font-size: .75rem;
      line-height: 1rem;
      color: #565869b0;
    }
  }

}
</style>
