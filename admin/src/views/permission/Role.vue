<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加权限角色"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :update="formDialogVisible"
      :hasExpand="true"
      :disabled="!auth(55)"
      ref="tableRef"
      @query="getRoleList"
      @add="addRoleDialog"
      @expand="getRightTree"
    >
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateRoleDialog(row)"
          :disabled="!auth(56)"
          >信息修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteRole(row.id)"
          :disabled="!auth(58)"
          >删除</el-button
        >
        <!-- 权限分配 -->
        <el-button
          size="mini"
          type="warning"
          icon="el-icon-setting"
          @click="authDialog(row.id)"
          :disabled="!auth(57)"
          >分配权限</el-button
        >
      </template>
      <template v-slot:expand="{ row }">
        <!-- 展开行的内容 -->
        <el-row
          v-for="item in roleRightList"
          :key="item.id"
          :class="['vcenter']"
        >
          <!-- 一级权限 -->
          <el-col :span="5"
            ><el-tag
              :closable="!auth(59)"
              @close="deleteAuth(row.id, item.id)"
              >{{ item.name }}</el-tag
            >
            <i class="el-icon-caret-right"
          /></el-col>
          <!-- 二级权限 -->
          <el-col :span="19">
            <el-row
              v-for="item2 in item.children"
              :key="item2.id"
              :class="['vcenter']"
            >
              <el-col :span="6"
                ><el-tag
                  type="success"
                  :closable="!auth(59)"
                  @close="deleteAuth(row.id, item2.id)"
                  >{{ item2.name }}</el-tag
                >
                <i class="el-icon-caret-right"
              /></el-col>
              <!-- 三级级权限 -->
              <el-col :span="18"
                ><el-tag
                  :closable="!auth(59)"
                  @close="deleteAuth(row.id, item3.id)"
                  type="warning"
                  v-for="item3 in item2.children"
                  :key="item3.id"
                  >{{ item3.name }}</el-tag
                ></el-col
              >
            </el-row>
          </el-col>
        </el-row>
      </template>
    </table-page>

    <!-- 权限 设置 -->
    <el-dialog
      title="权限设置"
      :visible.sync="authDialogVisible"
      width="50%"
      @close="authDialogClosed"
    >
      <!-- 内容主题区域 -->
      <el-tree
        :data="rightsTree"
        :props="treeProps"
        show-checkbox
        ref="treeRef"
        :render-after-expand="false"
        node-key="id"
        default-expand-all
        :default-checked-keys="defaultKeys"
      ></el-tree>

      <!-- 底部区域 -->
      <span slot="footer" class="dialog=footer">
        <el-button @click="authDialogVisible = false">取消</el-button>
        <el-button @click="updateAuth">确定</el-button>
      </span>
    </el-dialog>

    <!-- 弹窗区域 -->
    <form-dialog
      :visible.sync="formDialogVisible"
      :dialogTitle="formDialogTitle"
      :fields="formFields"
      :formData="form"
      @submit="handleFormSubmit"
    />
  </div>
</template>

<script>
import {
  getRoleList,
  addRole,
  updateRole,
  deleteRole,
  getAuthTree,
  updateAuth,
  getAuthList,
  deleteAuth
} from '@/api/permission'

import store from '@/store'

export default {
  name: 'RoleView',
  components: {
    'table-page': () => import('@/components/TableView.vue'),
    'form-dialog': () => import('@/components/FormDialog.vue')
  },
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tableFields: [
        { label: '角色ID', prop: 'id' },
        { label: '角色名称', prop: 'name' },
        { label: '角色描述', prop: 'desc' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '320px' }
      ],

      // 表单窗口
      formDialogTitle: '',
      formDialogVisible: false,
      formFields: [],
      form: {},
      handleFormSubmit: function () {},

      // 权限分配窗口
      selectedId: null,
      authDialogVisible: false,
      treeProps: {
        label: 'name',
        children: 'children'
      },
      rightsTree: [],
      defaultKeys: [],

      // 权限
      roleRightList: []
    }
  },
  methods: {
    // 权限校验
    auth (pid) {
      return store.getters.permission.includes(pid)
    },

    // 弹窗
    // 添加 管理
    addRoleDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加角色'
      this.formFields = [
        { label: '名称', prop: 'name' },
        { label: '描述', prop: 'desc' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addRole
    },
    // 添加 管理
    updateRoleDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改角色'
      this.formFields = [
        { label: '名称', prop: 'name' },
        { label: '描述', prop: 'desc' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateRole
    },
    // 分配权限
    authDialog (id) {
      this.selectedId = id
      this.getAuthTree()
      this.getAuthList(id)
      this.authDialogVisible = true
    },
    authDialogClosed () {},
    // 获取 权限
    getRightTree (id) {
      const param = { id: id }
      getAuthTree(3, param)
        .then(({ data: res }) => {
          this.roleRightList = res.data
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },

    // 请求
    // 获取 管理员 列表
    getRoleList (query) {
      getRoleList(query)
        .then(({ data: res }) => {
          this.tableList = res.data.list
          this.totalNum = res.data.total
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 添加 管理
    addRole (form) {
      addRole(form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改 管理
    updateRole (form) {
      updateRole(form.id, form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除 用户
    async deleteRole (id) {
      const result = await this.$confirm(
        '此操作将永久删除该用户，是否继续',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch((err) => err)

      if (result !== 'confirm') return this.$message.info('已取消删除')
      deleteRole(id)
        .then(() => {
          this.$refs.tableRef.handleQuery()
          this.$message.success('删除成功')
        })
        .catch(() => {
          this.$message.error('删除失败')
        })
    },
    // 获取权限树
    getAuthTree () {
      getAuthTree(3, null)
        .then(({ data: res }) => {
          this.rightsTree = res.data
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 获取权限列表
    getAuthList (id) {
      getAuthList(id)
        .then(({ data: res }) => {
          this.defaultKeys = res.data
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 分配权限
    updateAuth () {
      const rights = [
        ...this.$refs.treeRef.getCheckedKeys(),
        ...this.$refs.treeRef.getHalfCheckedKeys()
      ]
      updateAuth(this.selectedId, rights)
        .then(() => {
          this.getRightTree(this.selectedId)
          this.authDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除 授权
    async deleteAuth (rid, pid) {
      const result = await this.$confirm(
        '此操作将永久删除该权限，是否继续',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch((err) => err)

      if (result !== 'confirm') return this.$message.info('已取消删除')
      deleteAuth(rid, pid)
        .then(() => {
          this.getRightTree(rid)
          this.$refs.tableRef.handleQuery()
          this.$message.success('删除成功')
        })
        .catch(() => {
          this.$message.error('删除失败')
        })
    }
  }
}
</script>

<style lang="less" scoped>
.el-row {
  border-bottom: 1px solid #eee;

  .el-tag {
    margin: 7px;
  }
}
</style>
