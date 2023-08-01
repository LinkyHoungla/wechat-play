<template>
  <div>
    <div class="card-container">
      <div class="card bg-1">
        <div class="left">
          <i class="iconfont icon-transaction top" />
        </div>
        <div class="right">
          <p class="title">今日订单量</p>
          <p class="con">1245</p>
        </div>
      </div>
      <div class="card bg-2">
        <div class="left">
          <i class="iconfont icon-user top" />
        </div>
        <div class="right">
          <p class="title">今日用户量</p>
          <p class="con">432</p>
        </div>
      </div>
      <div class="card bg-3">
        <div class="left">
          <i class="iconfont icon-Dollar top" />
        </div>
        <div class="right">
          <p class="title">今日营业额</p>
          <p class="con">6482.34</p>
        </div>
      </div>
    </div>

    <div class="chart-container">

    </div>
  </div>
</template>

<script>
import '@/assets/fonts/home/iconfont.css'

export default {
  name: 'HomeView'
}
</script>

<style lang="less" scoped >
[class$="container"] {
  width: 100%;
  padding: 20px 0;
  margin: 25px 0;

  border-radius: 4px;
  border: 1px solid #ebeef5;
  background-color: #fff;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.card-container {
  display: flex;
  justify-content: space-around;
  align-items: center;

  .card {
    position: relative;
    height: 110px;
    width: 330px;

    .left {
      position: absolute;
      left: 0;
      top: 50%;

      transform: translate(40px, -50%);

      .top {
        font-size: 40px;
        color: #fff;
      }

      &::before {
        content: "";

        display: block;
        position: absolute;
        top: -10px;
        left: -10px;
        z-index: -1;

        width: 60px;
        height: 60px;
        border-radius: 50%;
        background-color: #ffffff2d;
      }
    }

    .right {
      position: absolute;
      right: 0;
      top: 50%;

      transform: translate(-65px, -50%);

      color: #fff;
      text-align: center;

      p.title {
        font-size: 14px;
      }

      p.con {
        margin-top: 10px;
        font-size: 24px;
      }
    }
  }

  .bg-1 {
    background-color: #33cabb;
  }

  .bg-2 {
    background-color: #f96868;
  }

  .bg-3 {
    background-color: #926dde;
  }
}
</style>
