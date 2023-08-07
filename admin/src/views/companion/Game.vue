<template>
  <div>
    <!-- 表格区域 -->
    <table-page tableTitle="添加游戏" :tableFields="tableFields" :total="totalNum" :list="tableList" :tagOptions="tagOptions"
      :update="formDialogVisible" @query="getGameList" @add="addGameDialog(-1)">
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
        <!-- 添加子类别 -->
        <el-button v-if="row.type === 'GAME'" icon="el-icon-circle-plus-outline" size="mini"
          @click="addGameDialog(row.id)">添加子类别</el-button>
        <!-- 修改按钮 -->
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="updateGameDialog(row)">修改</el-button>
        <!-- 删除按钮 -->
        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteGame(row.id)">删除</el-button>
      </template>
    </table-page>

    <!-- 弹窗区域 -->
    <form-dialog :visible.sync="formDialogVisible" :dialogTitle="formDialogTitle" :fields="formFields" :formData="form"
      @submit="handleFormSubmit" />
  </div>
</template>

<script>
import { getGameList, addGame, updateGame, deleteGame } from '@/api/companion'

export default {
  name: 'GameView',
  components: {
    'table-page': () => import('@/components/TableView.vue'),
    'form-dialog': () => import('@/components/FormDialog.vue')
  },
  data () {
    return {
      // 表格
      tableList: [],
      totalNum: 0,
      tagOptions: [
        { value: 'SERVER', label: '区服' },
        { value: 'GAME', label: '游戏' },
        { value: 'RANK', label: '等级' },
        { value: 'MODE', label: '模式' }
      ],
      tableFields: [
        { label: 'ID', prop: 'id' },
        { label: 'pid', prop: 'pid' },
        { label: '类型', prop: 'type', type: 'template' },
        { label: '名称', prop: 'name' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '300px' }
      ],

      // TODO 标签独立文件
      // 标签
      tagFields: [
        { value: 'GAME', label: '游戏', tag: 'success' },
        { value: 'SERVER', label: '区服', tag: 'info' },
        { value: 'RANK', label: '等级', tag: 'danger' },
        { value: 'MODE', label: '模式', tag: 'danger' }
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
    addGameDialog (id) {
      this.formDialogVisible = true
      this.formDialogTitle = '添加游戏'
      this.form = { pid: id }
      if (id === -1) {
        this.form.type = 'GAME'
        this.formFields = [
          { label: '名称', prop: 'name' }
        ]
      } else {
        this.formFields = [
          {
            label: '类别',
            prop: 'type',
            type: 'select',
            options: [
              { value: 'SERVER', label: '区服' },
              { value: 'RANK', label: '等级' },
              { value: 'MODE', label: '模式' }
            ]
          },
          { label: '名称', prop: 'name' }
        ]
      }
      this.handleFormSubmit = this.addGame
    },
    // 修改
    updateGameDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改游戏'
      this.formFields = [
        { label: '名称', prop: 'name' }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateGame
    },

    // 获取 Tag类型
    getFieldTagType (value) {
      const field = this.tagFields.find((item) => item.value === value)
      return field ? field.tag : ''
    },

    // 请求
    // 获取
    getGameList (query) {
      getGameList(query)
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
    addGame (form) {
      addGame(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('添加成功')
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    // 修改
    updateGame (form) {
      console.log(form)
      updateGame(form)
        .then(() => {
          this.formDialogVisible = false
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    },
    // 删除
    async deleteGame (id) {
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
      deleteGame(id)
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
