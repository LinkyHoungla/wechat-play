<template>
  <div>
    <!-- 表格区域 -->
    <table-page
      tableTitle="添加权限"
      :tableFields="tableFields"
      :total="totalNum"
      :list="tableList"
      :update="formDialogVisible"
      @query="getPermissionTree"
      @add="addPermissionDialog(-1)"
    >
      <template v-slot:operate="{ row }">
        <!-- 添加按钮 -->
        <el-button v-if="row.level !== 3"
          icon="el-icon-circle-plus-outline"
          size="mini"
          @click="addPermissionDialog(row.id)"
        >添加子权限</el-button>
        <!-- 修改按钮 -->
        <el-button
          type="primary"
          icon="el-icon-edit"
          size="mini"
          @click="updatePermissionDialog(row)"
        >修改</el-button>
        <!-- 删除按钮 -->
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="deletePermission(row.id)"
        >删除</el-button>
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
import { getPermissionTree, addPermission, updatePermission, deletePermission } from '@/api/permission'

export default {
  name: 'PermissionList',
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
        { label: '权限ID', prop: 'id' },
        { label: '名称', prop: 'name' },
        { label: '路径', prop: 'path' },
        { label: '图标', prop: 'icon' },
        { label: '等级', prop: 'level' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '300px' }
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
    // 添加
    addPermissionDialog (id) {
      this.formDialogVisible = true
      this.formDialogTitle = '添加管理'
      this.formFields = [
        { label: '名称', prop: 'name' },
        { label: '路径', prop: 'path' },
        { label: '图标', prop: 'icon' }
      ]
      this.form = { pid: id }
      this.handleFormSubmit = this.addPermission
    },
    // 修改
    updatePermissionDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改管理'
      this.formFields = [
        { label: '名称', prop: 'name' },
        { label: '路径', prop: 'path' },
        { label: '图标', prop: 'icon' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updatePermission
    },

    // 请求
    // 获取
    getPermissionTree (query) {
      getPermissionTree(query)
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
    addPermission (form) {
      addPermission(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改
    updatePermission (form) {
      updatePermission(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除
    async deletePermission (id) {
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
      deletePermission(id)
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
