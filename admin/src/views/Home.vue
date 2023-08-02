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

    <div class="charts">
      <div class="chart-container">
        <div class="header">
          <h4>活跃人数</h4>
        </div>
        <div id="person" style="width: 650px; height: 400px;" />
      </div>
      <div class="chart-container">
        <div class="header">
          <h4>交易记录</h4>
        </div>
        <div id="income" style="width: 650px; height: 400px;" />
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/fonts/home/iconfont.css'

const Echarts = require('echarts')

export default {
  name: 'HomeView',
  mounted () {
    this.initECharts()
    this.setEChart(this.personEhart, this.personOption)
    this.setEChart(this.incomeEhart, this.incomeOption)
  },
  data () {
    return {
      personEhart: null,
      personOption: {
        xAxis: {
          data: ['A', 'B', 'C', 'D', 'E']
        },
        yAxis: {},
        series: [{
          name: '数据',
          type: 'bar',
          data: [5, 20, 36, 10, 10]
        }]
      },
      incomeEhart: null,
      incomeOption: {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line'
          }
        ]
      }
    }
  },
  methods: {
    initECharts () {
      // 初始化 ECharts 实例
      this.personEhart = Echarts.init(document.getElementById('person'))
      this.incomeEhart = Echarts.init(document.getElementById('income'))
    },
    setEChart (e, option) {
      // 绘制图表数据
      e.setOption(option)
    }
  }
}
</script>

<style lang="less" scoped >
[class$="container"] {
  padding: 10px 0;

  margin-bottom: 15px;

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

.charts {
  display: flex;
  justify-content: space-evenly;
  align-items: center;

  .header {
    width: 100%;
    margin: 0;
    padding: 5px 25px;
    border-bottom: 1px solid rgba(77,82,89,0.05);

    line-height: 1.5;
    color: #313944;
    letter-spacing: .5px;
  }
}
</style>
