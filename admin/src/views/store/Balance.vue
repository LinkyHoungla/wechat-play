<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加预存"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :update="formDialogVisible"
      @query="getBalanceList"
      @add="addBalanceDialog"
    >
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(row.status)" size="mini">{{
          tagFields.find((item) => item.value === row.status).label
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateBalanceDialog(row)"
          >修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteBalance(row.id)"
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
import { getBalanceList, addBalance, deleteBalance, updateBalance } from '@/api/store'

export default {
  name: 'BalanceView',
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tableFields: [
        { label: 'ID', prop: 'id' },
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' },
        { label: '余额', prop: 'balance' },
        { label: '信息', prop: 'content' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
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
    addBalanceDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加预存'
      this.formFields = [
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addBalance
    },
    // 添加 管理
    updateBalanceDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改预存'
      this.formFields = [
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' },
        { label: '余额', prop: 'balance' },
        { label: '信息', prop: 'content' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateBalance
    },

    // 获取 Tag类型
    getFieldTagType (value) {
      const field = this.tagFields.find((item) => item.value === value)
      return field ? field.tag : ''
    },

    // 请求
    // 获取 管理员 列表
    getBalanceList (query) {
      getBalanceList(query)
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
    addBalance (form) {
      addBalance(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改 管理
    updateBalance (form) {
      console.log(form)
      updateBalance(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除 店铺
    async deleteBalance (id) {
      const result = await this.$confirm(
        '此操作将永久删除该店铺，是否继续',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch((err) => err)

      if (result !== 'confirm') return this.$message.info('已取消删除')
      deleteBalance(id)
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
