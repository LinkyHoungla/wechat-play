<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加订单"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :update="formDialogVisible"
      @query="getAdminList"
      @add="addAdminDialog"
    >
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateAdminDialog(row)"
        />
        <!-- 删除按钮 -->
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteAdmin(row.cid)"
        />
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

export default {
  name: 'AdminView',
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
        { label: '状态', prop: 'status' },
        { label: 'ip', prop: 'ip' },
        { label: '上次登录', prop: 'loginTime' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template' }
      ],

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
        { label: '身份', prop: 'rid' }
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
        { label: '身份', prop: 'rid' },
        { label: '状态', prop: 'status' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateAdmin
    },

    // 请求
    // 获取 管理员 列表
    getAdminList (query) {
      getAdminList(query)
        .then((response) => {
          const { data: res } = response.data
          this.tableList = res.list
          this.totalNum = res.total
          this.$message.success('获取成功')
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
