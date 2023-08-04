<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="(item, index) in list" :key="item.path">
        <span
          v-if="index == list.length - 1"
          class="end"
          >{{ item.meta.title }}</span
        >
        <span
          v-else-if="item.redirect !== undefined"
          class="no-redirect"
          >{{ item.meta.title }}</span
        >
        <a v-else @click.prevent="handleLink(item)" class="link">{{ item.meta.title }}</a>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  data () {
    return {
      list: []
    }
  },
  watch: {
    $route () {
      this.getBreadcrumb()
    }
  },
  created () {
    this.getBreadcrumb()
  },
  methods: {
    // 获取路径
    getBreadcrumb () {
      let matched = this.$route.matched.filter(
        (item) => item.meta && item.meta.title
      )
      const first = matched[0]

      if (!this.isDashboard(first)) {
        matched = [{ path: '/home', meta: { title: '首页' } }].concat(matched)
      }

      this.list = matched.filter((item) => item.meta && item.meta.title)
    },
    // 是否为首页
    isDashboard (route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Home'.toLocaleLowerCase()
    },
    // 点击跳转
    handleLink (item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(path)
    }
  }
}
</script>

<style lang="less" scoped>
.el-breadcrumb {
  display: inline-block;
  font-size: 15px;
  line-height: 50px;
  margin-left: 8px;

  .end {
    color: #6dccf2;
    cursor: text;
  }

  .no-redirect {
    color: #f4fff8;;
  }

  .link {
    color: #f4fff8;
  }

  .link:hover {
    color: #f3db4b;
  }
}
</style>
