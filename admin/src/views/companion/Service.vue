<template>
  <div>
    <!-- 表格区域 -->
    <table-page tableTitle="添加服务" :tableFields="tableFields" :total="totalNum" :list="tableList"
      :update="formDialogVisible" @query="getServiceList" @add="addServiceDialog">
      <template v-slot:type="{ row }">
        <el-tag :type="getFieldTagType(row.status)" size="mini">{{
          tagFields.find((item) => item.value === row.type).label
        }}</el-tag>
      </template>
      <template v-slot:gender="{ row }">
        <el-tag :type="getFieldTagType(row.status)" size="mini">{{
          tagFields.find((item) => item.value === row.gender).label
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="updateServiceDialog(row)">修改</el-button>
        <!-- 删除按钮 -->
        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteService(row.id)">删除</el-button>
      </template>
    </table-page>

    <!-- 弹窗区域 -->
    <form-dialog :visible.sync="formDialogVisible" :dialogTitle="formDialogTitle" :fields="formFields" :formData="form"
      @submit="handleFormSubmit" />
  </div>
</template>

<script>
import { getServiceList, addService, updateService, deleteService } from '@/api/companion'

export default {
  name: 'ServiceView',
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
        { label: 'ID', prop: 'id' },
        { label: '类型', prop: 'type', type: 'template' },
        { label: '名称', prop: 'name' },
        { label: '性别', prop: 'gender', type: 'template' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // TODO 标签独立文件
      // 标签
      tagFields: [
        { value: 'SERVICE', label: '服务', tag: 'success' },
        { value: 'VOICE', label: '音色', tag: 'info' },
        { value: 'NATURE', label: '性格', tag: 'danger' },
        { value: 'MALE', label: '男', tag: 'danger' },
        { value: 'FEMALE', label: '女', tag: 'success' },
        { value: 'ALL', label: '所有', tag: 'success' }
      ],

      // 表单窗口
      formDialogTitle: '',
      formDialogVisible: false,
      formFields: [],
      form: {},
      handleFormSubmit: function () { }
    }
  },
  methods: {
    // 弹窗
    // 添加
    addServiceDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加服务'
      this.formFields = [
        {
          label: '服务类型',
          prop: 'type',
          type: 'select',
          options: [
            { value: 'SERVICE', label: '服务' },
            { value: 'VOICE', label: '音色' },
            { value: 'NATURE', label: '性格' }
          ]
        },
        {
          label: '性别',
          prop: 'gender',
          type: 'select',
          options: [
            { value: 'MALE', label: '男' },
            { value: 'FEMALE', label: '女' },
            { value: 'ALL', label: '所有' }
          ]
        },
        { label: '名称', prop: 'name' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addService
    },
    // 修改
    updateServiceDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改服务'
      this.formFields = [
        {
          label: '性别',
          prop: 'gender',
          type: 'select',
          options: [
            { value: 'MALE', label: '男' },
            { value: 'FEMALE', label: '女' },
            { value: 'ALL', label: '所有' }
          ]
        },
        { label: '名称', prop: 'name' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateService
    },

    // 获取 Tag类型
    getFieldTagType (value) {
      const field = this.tagFields.find((item) => item.value === value)
      return field ? field.tag : ''
    },

    // 请求
    // 获取
    getServiceList (query) {
      getServiceList(query)
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
    // 添加
    addService (form) {
      addService(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改
    updateService (form) {
      console.log(form)
      updateService(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除
    async deleteService (id) {
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
      deleteService(id)
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

<style></style>
