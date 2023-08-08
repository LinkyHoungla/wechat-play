<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加管理员"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :tagOptions="statusTag"
      ref="tableRef"
      @query="getAdminList"
      @add="addAdminDialog"
    >
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(statusTag, row.status)" size="mini">{{
         getFieldLable(statusTag, row.status)
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          v-if="row.rid !== 0 && row.status !== 'DELETED'"
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateAdminDialog(row)"
          >修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          v-if="row.rid !== 0 && row.status !== 'DELETED'"
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteAdmin(row.id)"
          >删除</el-button
        >
      </template>
    </table-page>

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
import { getAdminList, addAdmin, deleteAdmin, updateAdmin } from '@/api/admin'
import { getRoles } from '@/api/permission'

import { getFieldTagType, TAG_STATUS, getFieldLable } from '@/utils/tag'

export default {
  name: 'AdminView',
  components: {
    'table-page': () => import('@/components/TableView.vue'),
    'form-dialog': () => import('@/components/FormDialog.vue')
  },
  created () {
    this.getRoles()
  },
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tableFields: [
        { label: '用户ID', prop: 'id' },
        { label: '头像', prop: 'avatar' },
        { label: '用户名', prop: 'username' },
        { label: '用户昵称', prop: 'name' },
        { label: '权限角色', prop: 'role' },
        { label: '状态', prop: 'status', type: 'template' },
        { label: 'ip', prop: 'ip' },
        { label: '上次登录', prop: 'loginTime' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // 标签
      statusTag: TAG_STATUS,

      // 下拉菜单
      roles: [],

      // 表单窗口
      formDialogTitle: '',
      formDialogVisible: false,
      formFields: [],
      form: {},
      handleFormSubmit: function () {}
    }
  },
  methods: {
    // 弹窗
    // 添加 管理
    addAdminDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加管理'
      this.formFields = [
        { label: '昵称', prop: 'name' },
        { label: '身份', prop: 'rid', type: 'select', options: this.roles }
      ]
      this.form = {}
      this.handleFormSubmit = this.addAdmin
    },
    // 添加 管理
    updateAdminDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改管理'
      this.formFields = [
        { label: '昵称', prop: 'name' },
        { label: '身份', prop: 'rid', type: 'select', options: this.roles },
        { label: '状态', prop: 'status', type: 'select', options: TAG_STATUS }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateAdmin
    },

    // 获取 Tag类型
    getFieldTagType (list, value) {
      return getFieldTagType(list, value)
    },
    getFieldLable (list, value) {
      return getFieldLable(list, value)
    },

    // 请求
    // 获取 管理员 列表
    getAdminList (query) {
      getAdminList(query)
        .then((response) => {
          const { data: res } = response.data
          this.tableList = res.list
          this.totalNum = res.total
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 获取 管理员 列表
    getRoles () {
      getRoles()
        .then(({ data: res }) => {
          this.roles = res.data.map((item) => {
            if (item.id === 0) {
              return { label: item.name, value: item.id, disabled: true }
            } else {
              return { label: item.name, value: item.id }
            }
          })
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 添加 管理
    addAdmin (form) {
      addAdmin(form)
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
    updateAdmin (form) {
      updateAdmin(form.id, form)
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
    async deleteAdmin (id) {
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
      deleteAdmin(id)
        .then(() => {
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

<style>
</style>
