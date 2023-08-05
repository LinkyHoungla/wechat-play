<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加用户"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :update="formDialogVisible"
      @query="getUserList"
      @add="addUserDialog"
    >
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(row.status)" size="mini">{{
          tagFields.find((item) => item.value === row.status).label
        }}</el-tag>
      </template>
      <template v-slot:gender="{ row }">
        <el-tag :type="getFieldTagType(row.gender)" size="mini">{{
          tagFields.find((item) => item.value === row.gender).label
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateUserDialog(row)"
          >修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteUser(row.id)"
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
import { getUserList, addUser, deleteUser, updateUser } from '@/api/user'

export default {
  name: 'UserView',
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tableFields: [
        { label: '用户ID', prop: 'id' },
        { label: '头像', prop: 'avatar' },
        { label: '昵称', prop: 'nickname' },
        { label: '微信ID', prop: 'wxid' },
        { label: '权限角色', prop: 'role' },
        { label: '性别', prop: 'gender', type: 'template' },
        { label: '年龄', prop: 'age' },
        { label: '生日', prop: 'birth' },
        { label: '地区', prop: 'location' },
        { label: '状态', prop: 'status', type: 'template' },
        { label: 'ip', prop: 'ip' },
        { label: '上次登录', prop: 'loginTime' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // 标签
      tagFields: [
        { value: null, label: null, tag: '' },
        { value: 'ACTIVE', label: '正常', tag: 'success' },
        { value: 'BANNED', label: '封禁', tag: 'info' },
        { value: 'DELETED', label: '已删除', tag: 'danger' },
        { value: 'MALE', label: '男', tag: 'danger' },
        { value: 'FEMALE', label: '女', tag: 'success' }
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
    addUserDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加用户'
      this.formFields = [
        { label: '昵称', prop: 'nickname' },
        { label: '微信ID', prop: 'wxid' },
        { label: '手机', prop: 'phone' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addUser
    },
    // 添加 管理
    updateUserDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改用户'
      this.formFields = [
        { label: '昵称', prop: 'nickname' },
        {
          label: '性别',
          prop: 'gender',
          type: 'select',
          options: [
            { value: 'MALE', label: '男' },
            { value: 'FEMALE', label: '女' }
          ]
        },
        { label: '年龄', prop: 'age' },
        { label: '生日', prop: 'birth', type: 'date' },
        { label: '地区', prop: 'location' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateUser
    },

    // 获取 Tag类型
    getFieldTagType (value) {
      const field = this.tagFields.find((item) => item.value === value)
      return field ? field.tag : ''
    },

    // 请求
    // 获取 管理员 列表
    getUserList (query) {
      getUserList(query)
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
    addUser (form) {
      addUser(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改 管理
    updateUser (form) {
      console.log(form)
      updateUser(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除 用户
    async deleteUser (id) {
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
      deleteUser(id)
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
