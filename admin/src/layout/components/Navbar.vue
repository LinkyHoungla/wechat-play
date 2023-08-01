<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>活动列表</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>

<style lang="less" >
.el-breadcrumb {
  font-size: 15px;

  .el-breadcrumb__inner {
    // color: #c5ff3f;
    color: #f4fff8;

    &.is-link {
      color: #f4fff8;
    }

    &.is-link:hover {
      color: #f3db4b;
    }

    &:not(.is-link) {
      pointer-events: none;
    }
  }

  :last-child .el-breadcrumb__inner {
    color: #6dccf2 !important;

    &:hover {
      color: #6dccf2 !important;
    }
  }
}
</style>
