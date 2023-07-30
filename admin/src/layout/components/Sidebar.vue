<template>
  <div @mouseover="isOpen = true" @mouseout="isOpen = false">
    <!-- 头像 -->
    <div class="avatar">
      <div class="img">
        <img src="" alt="" />
      </div>
      <div class="con">
        <h3>用户名</h3>
        <p>身份</p>
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
      >
        <el-submenu
          :index="item.pageId + ''"
          v-for="item in menusList"
          :key="item.pageId"
        >
          <!-- 1级菜单模板区域 -->
          <template slot="title">
            <!-- 图标 -->
            <i :class="'iconfont ' + item.icon"></i>
            <!-- 文本 -->
            <span>{{ item.name }}</span>
          </template>

          <!-- 2级菜单 -->
          <el-menu-item
            :index="'/' + subItem.path"
            v-for="subItem in item.children"
            :key="subItem.pageId"
          >
            <template slot="title">
              <!-- 图标 -->
              <i class="el-icon-menu"></i>
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
        <div class="item">
          <i class="iconfont icon-settings" />
          <span>个人设置</span>
        </div>
        <div class="item">
          <i class="iconfont icon-exit" />
          <span>退出</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/fonts/iconfont.css'

export default {
  name: 'SideBar',
  data () {
    return {
      menusList: [
        {
          pageId: 101,
          name: '用户管理',
          path: 'users',
          icon: 'icon-user',
          parentId: -1,
          children: [
            {
              pageId: 1001,
              name: '管理员列表',
              path: 'admins',
              icon: null,
              parentId: 101,
              children: null
            },
            {
              pageId: 1002,
              name: '用户列表',
              path: 'users',
              icon: null,
              parentId: 101,
              children: null
            }
          ]
        },
        {
          pageId: 102,
          name: '权限管理',
          path: 'rights',
          icon: 'icon-permission',
          parentId: -1,
          children: [
            {
              pageId: 2001,
              name: '角色列表',
              path: 'roles',
              icon: null,
              parentId: 102,
              children: null
            },
            {
              pageId: 2002,
              name: '权限列表',
              path: 'rights',
              icon: null,
              parentId: 102,
              children: null
            }
          ]
        },
        {
          pageId: 103,
          name: '店铺管理',
          path: 'shops',
          icon: 'icon-shop',
          parentId: -1,
          children: [
            {
              pageId: 3001,
              name: '店铺列表',
              path: 'stores',
              icon: null,
              parentId: 103,
              children: null
            },
            {
              pageId: 3002,
              name: '员工列表',
              path: 'companions',
              icon: null,
              parentId: 103,
              children: null
            },
            {
              pageId: 10005,
              name: '服务类型',
              path: 'service',
              icon: null,
              parentId: 103,
              children: null
            }
          ]
        },
        {
          pageId: 104,
          name: '订单管理',
          path: 'orders',
          icon: 'icon-order',
          parentId: -1,
          children: [
            {
              pageId: 10006,
              name: '订单列表',
              path: 'orders',
              icon: null,
              parentId: 104,
              children: null
            }
          ]
        }
      ],
      isOpen: false
    }
  },
  methods: {
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
      width: 80px;
      height: 80px;
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
        font-weight: 550;
      }

      p {
        margin-top: 3px;
        font-size: 15px;
        font-weight: 200;
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
        overflow: hidden;

        span {
          display: inline-block;

          font-size: 14px;
          white-space: nowrap;
          color: #fff;

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
