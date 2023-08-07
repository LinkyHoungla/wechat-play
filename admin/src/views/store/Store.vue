<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加店铺"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :update="formDialogVisible"
      @query="getStoreList"
      @add="addStoreDialog"
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
          @click="updateStoreDialog(row)"
          >修改</el-button
        >
        <!-- 删除按钮 -->
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deleteStore(row.id)"
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
import { getStoreList, addStore, deleteStore, updateStore } from '@/api/store'

export default {
  name: 'StoreView',
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
        { label: '店铺ID', prop: 'id' },
        { label: '头像', prop: 'avatar' },
        { label: '名称', prop: 'name' },
        { label: '描述', prop: 'desc' },
        { label: '所有人id', prop: 'oid' },
        { label: '联系人', prop: 'owner' },
        { label: '电话', prop: 'phone' },
        { label: '状态', prop: 'status', type: 'template' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // 标签
      tagFields: [
        { value: null, label: null, tag: '' },
        { value: 'ACTIVE', label: '正常', tag: 'success' },
        { value: 'BANNED', label: '封禁', tag: 'info' },
        { value: 'DELETED', label: '已删除', tag: 'danger' }
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
    addStoreDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加店铺'
      this.formFields = [
        { label: '名称', prop: 'name' },
        { label: '描述', prop: 'desc' },
        { label: '所有人id', prop: 'oid' },
        { label: '联系人', prop: 'owner' },
        { label: '电话', prop: 'phone' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addStore
    },
    // 添加 管理
    updateStoreDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改店铺'
      this.formFields = [
        { label: '名称', prop: 'name' },
        { label: '描述', prop: 'desc' },
        {
          label: '状态',
          prop: 'status',
          type: 'select',
          options: [
            { value: 'ACTIVE', label: '正常' },
            { value: 'BANNED', label: '封禁' }
          ]
        }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateStore
    },

    // 获取 Tag类型
    getFieldTagType (value) {
      const field = this.tagFields.find((item) => item.value === value)
      return field ? field.tag : ''
    },

    // 请求
    // 获取 管理员 列表
    getStoreList (query) {
      getStoreList(query)
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
    addStore (form) {
      addStore(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改 管理
    updateStore (form) {
      console.log(form)
      updateStore(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除 店铺
    async deleteStore (id) {
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
      deleteStore(id)
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
