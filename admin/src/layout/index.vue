<template>
  <div class="app-wrapper">
    <Sidebar class="side-container" />
    <AppMain class="main-container" />
  </div>
</template>

<script>
import { Sidebar, AppMain } from '@/layout/components'

export default {
  name: 'LayoutView',
  components: {
    Sidebar,
    AppMain
  }
}
</script>

<style lang="less" scoped>
.app-wrapper {
  position: relative;
  display: flex;
  align-items: center;

  padding: 5px;

  height: 100vh;
  width: 100%;
  background-color: #bee;

  .side-container {
    position: absolute;
    top: 50%;
    left: 64px;

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

}
</style>
