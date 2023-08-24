<template>
  <div @mouseover="isOpen = true" @mouseout="isOpen = false">
    <!-- 头像 -->
    <div class="avatar">
      <div class="img">
        <img :src="avatar" />
      </div>
      <div class="con">
        <p>{{ name }}</p>
        <h3>{{ role }}</h3>
      </div>
    </div>

    <div class="line" />
    <!-- 菜单 -->
    <div class="menu">
      <p class="title">菜单</p>
      <el-menu
        :background-color="isOpen ? '#292626' : 'transparent'"
        text-color="#fff"
        active-text-color="#cae36db3"
        unique-opened
        :router="true"
        :default-active="$route.path"
      >
        <el-submenu
          :index="item.id + ''"
          v-for="item in menusList"
          :key="item.id"
        >
          <!-- 1级菜单模板区域 -->
          <template slot="title">
            <!-- 图标 -->
            <i :class="'iconfont ' + item.icon" />
            <!-- 文本 -->
            <span>{{ item.name }}</span>
          </template>

          <!-- 2级菜单 -->
          <el-menu-item
            :index="item.path + subItem.path"
            v-for="subItem in item.children"
            :key="subItem.id"
          >
            <template slot="title">
              <!-- 图标 -->
              <i class="el-icon-menu" />
              <!-- 文本 -->
              <span>{{ subItem.name }}</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </div>

    <div class="line" />
    <!-- 操作栏 -->
    <div class="operate">
      <div class="items">
        <div class="item" @click="gotoMy">
          <i class="iconfont icon-settings" />
          <span>个人设置</span>
        </div>
        <div class="item" @click="logout">
          <i class="iconfont icon-close" />
          <span>退出</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/fonts/sidebar/iconfont.css'
import store from '@/store'

export default {
  name: 'SideBar',
  data () {
    return {
      name: '名称',
      role: '身份',
      avatar: '',
      menusList: [],
      isOpen: false
    }
  },
  mounted () {
    this.getCurrent()
  },
  methods: {
    logout () {
      this.$store.dispatch('login/logout').then(() => {
        this.$router.push('/login')
      }).catch(() => {})
    },
    getCurrent () {
      this.$store.dispatch('login/getLoginfo').then(() => {
        this.name = store.getters.name
        this.role = store.getters.role
        this.avatar = process.env.VUE_APP_BASE_AVATAR + '/admin/' + store.getters.avatar
        this.menusList = store.getters.menu
      })
    },
    gotoMy () {
      this.$router.push('/my')
    }
  }
}
</script>

<style lang="less">
.side-container {
  .avatar {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .img {
      width: 75px;
      height: 75px;
      border-radius: 50%;
      border: 4px solid #fff5;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
        border: 50%;
        object-fit: cover;
      }
    }

    .con {
      margin: 10px 0;

      h3 {
        font-weight: 100;
        font-size: 14px;
      }

      p {
        margin-top: 3px;
        font-size: 20px;
        font-weight: 200;
        color: #adeb6b;
      }
    }
  }

  .line {
    width: 50px;
    height: 1px;
    background-color: #efea;
    margin: 10px 25px;
    transition: all 0.5s;
  }

  p.title {
    margin-top: 4px;
    margin-left: 0;
    font-size: 16px;
    color: #fff;
  }

  .iconfont {
    font-size: 25px;
  }

  .menu {
    transition: all 0.5s;

    .el-menu {
      margin-top: 10px;
      border-radius: 5px;
      border-right: none;
      overflow: hidden;
      transition: all 0.5s;

      .el-submenu__title {
        span {
          display: inline-block;

          width: 0;
          opacity: 0;
          overflow: hidden;

          transition: all 0.5s;
        }

        i.el-icon-arrow-down::before {
          display: inline-block;
          width: 0;
          opacity: 0;
          transition: all 0.5s;
        }
      }

      .el-menu--inline {
        margin: 0;
      }

      .el-menu-item > span {
        display: inline-block;
        text-align: left;
        margin-left: 0;
        width: 70px;

        transition: all 0.5s;
      }

      .el-menu-item:not(.is-active) {
        display: none;
      }
    }
  }

  .operate {
    display: flex;
    flex-direction: column;
    align-items: center;

    width: 200px;

    .items {
      display: flex;
      flex-direction: column;
      align-items: center;

      margin-top: 10px;
      width: 100%;
      border-radius: 5px;
      overflow: hidden;

      .item {
        display: flex;
        justify-content: center;
        align-items: center;

        width: 100%;
        height: 36px;
        cursor: pointer;
        overflow: hidden;

        span {
          display: inline-block;

          font-size: 14px;
          white-space: nowrap;
          color: #fff;
          pointer-events: none;

          width: 0px;
          opacity: 0;
          overflow: hidden;

          transition: all 0.5s;
        }
      }

      .item:hover {
        background-color: #292626;
      }
    }
  }
}

.side-container:hover {
  .menu {
    p.title {
      margin-left: 4px;
    }

    .el-submenu__title {
      span {
        width: 120px;
        opacity: 1;
      }

      i.el-icon-arrow-down::before {
        width: 12px;
        opacity: 1;
      }
    }

    .el-menu-item > span {
      margin-left: 5px;
      width: 80px;
    }

    .el-menu-item:not(.is-active) {
      display: list-item;
    }
  }

  .operate {
    .items > .item > span {
      width: 120px;
      opacity: 1;
    }
  }

  .line {
    width: 200px;
  }
}
</style>
