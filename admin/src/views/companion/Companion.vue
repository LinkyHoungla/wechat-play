<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加陪玩"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      ref="tableRef"
      :update="formDialogVisible"
      @query="getCompanionList"
      @add="addCompanionDialog"
    >
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(statusTag, row.status)" size="mini">{{
          getFieldLabel(statusTag, row.status)
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button
          v-if="row.status !== 'DELETED'"
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updateCompanionDialog(row)"
          >修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          v-if="row.status !== 'DELETED'"
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteCompanion(row.id)"
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
import {
  getCompanionList,
  addCompanion,
  updateCompanion,
  deleteCompanion
} from '@/api/companion'
import { getFieldTagType, getFieldLable, TAG_STATUS } from '@/utils/tag'

export default {
  name: 'CompanionView',
  components: {
    'table-page': () => import('@/components/TableView.vue'),
    'form-dialog': () => import('@/components/FormDialog.vue')
  },
  created () {
    this.statusTag = TAG_STATUS
  },
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tableFields: [
        { label: 'ID', prop: 'id' },
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' },
        { label: '状态', prop: 'status', type: 'template' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // 标签
      statusTag: [],

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
    // 添加
    addCompanionDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加陪玩'
      this.formFields = [
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addCompanion
    },
    // 修改
    updateCompanionDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改陪玩'
      this.formFields = [
        {
          label: '状态',
          prop: 'status',
          type: 'select',
          options: [
            { value: 'ACTIVE', label: '正常' },
            { value: 'BANNED', label: '封禁' },
            { value: 'DELETED', label: '已删除', disabled: true }
          ]
        }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateCompanion
    },

    // 获取 Tag类型
    getFieldTagType (list, value) {
      return getFieldTagType(list, value)
    },
    getFieldLabel (list, value) {
      return getFieldLable(list, value)
    },

    // 请求
    // 获取
    getCompanionList (query) {
      getCompanionList(query)
        .then((response) => {
          const { data: res } = response.data
          this.tableList = res.list
          this.totalNum = res.total
        })
        .catch(() => {
          this.$message.error('获取失败')
        })
    },
    // 添加
    addCompanion (form) {
      addCompanion(form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改
    updateCompanion (form) {
      updateCompanion(form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除
    async deleteCompanion (id) {
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
      deleteCompanion(id)
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

<style></style>
