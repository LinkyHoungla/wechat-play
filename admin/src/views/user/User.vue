<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加用户"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      ref="tableRef"
      :hasExpand="true"
      :tagOptions="statusTag"
      @query="getUserList"
      @add="addUserDialog"
      @expand="getUserInfo"
    >
      <template v-slot:expand >
          <info-view :content="userInfo" :field="infoField" @submit="updateUser" />
      </template>
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(statusTag, row.status)" size="mini">{{
          getFieldLable(statusTag, row.status)
        }}</el-tag>
      </template>
      <template v-slot:gender="{ row }">
        <el-tag :type="getFieldTagType(genderTag, row.gender)" size="mini">{{
          getFieldLable(genderTag, row.gender)
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          v-if="row.status !== 'DELETED'"
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateUserDialog(row)"
          >修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          v-if="row.status !== 'DELETED'"
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
import { getUserList, addUser, deleteUser, updateUser, getUserInfo, updateStatus } from '@/api/user'
import { TAG_GENDER, TAG_STATUS, getFieldTagType, getFieldLable } from '@/utils/tag'

export default {
  name: 'UserView',
  components: {
    'table-page': () => import('@/components/TableView.vue'),
    'form-dialog': () => import('@/components/FormDialog.vue'),
    'info-view': () => import('@/components/InfoView.vue')
  },
  created () {
    this.genderTag = TAG_GENDER
    this.statusTag = TAG_STATUS
  },
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tableFields: [
        { label: '用户ID', prop: 'id' },
        { label: '微信ID', prop: 'wxid' },
        { label: '权限角色', prop: 'role' },
        { label: '状态', prop: 'status', type: 'template' },
        { label: 'ip', prop: 'ip' },
        { label: '上次登录', prop: 'loginTime' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // 拓展信息
      userInfo: {},
      infoField: [
        { label: '头像', prop: 'avatar' },
        { label: '昵称', prop: 'name', edit: true },
        { label: '性别', prop: 'gender', type: 'select', edit: true, options: TAG_GENDER },
        { label: '年龄', prop: 'age', edit: true },
        { label: '生日', prop: 'birth', edit: true, type: 'date' },
        { label: '地区', prop: 'location', edit: true },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' }
      ],

      // 标签
      genderTag: TAG_GENDER,
      statusTag: TAG_STATUS,

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
    // 添加 用户
    addUserDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加用户'
      this.formFields = [
        { label: '微信ID', prop: 'wxid' },
        { label: '手机', prop: 'phone' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addUser
    },
    // 修改 用户
    updateUserDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改用户'
      this.formFields = [
        {
          label: '状态',
          prop: 'status',
          type: 'select',
          options: TAG_STATUS
        }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateStatus
    },

    // 获取 Tag类型
    // 获取 Tag类型
    getFieldTagType (list, value) {
      return getFieldTagType(list, value)
    },
    getFieldLable (list, value) {
      return getFieldLable(list, value)
    },

    // 请求
    // 获取 用户员 列表
    getUserList (query) {
      getUserList(query)
        .then((response) => {
          const { data: res } = response.data
          this.tableList = res.list
          this.totalNum = res.total
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 获取 信息
    getUserInfo (id) {
      getUserInfo(id).then(({ data: res }) => {
        this.userInfo = res.data
        this.userInfo.id = id
      })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 添加 用户
    addUser (form) {
      addUser(form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改状态
    updateStatus (form) {
      updateStatus(form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 修改 用户
    updateUser (form) {
      updateUser(form)
        .then(() => {
          this.getUserInfo(form.id)
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
