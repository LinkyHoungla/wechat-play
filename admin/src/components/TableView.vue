<template>
  <el-card class="box-card">
    <!-- 收索区域 -->
    <el-row :gutter="20">
      <el-col :span="10">
        <!-- 搜索框 -->
        <el-input
          placeholder="请输入内容"
          v-model="queryInfo.query"
          clearable
          @clear="handleQuery"
        >
          <el-button slot="append" icon="el-icon-search" @click="handleQuery" />
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="handleAdd">{{ tableTitle }}</el-button>
      </el-col>
    </el-row>

    <!-- 列表区域 -->
    <el-table :data="tableList">
      <el-table-column type="index" />
      <el-table-column
        v-for="field in tableFields"
        :key="field.prop"
        :label="field.label"
        :prop="field.prop"
      >
        <!-- 判断是否使用具名插槽 -->
        <template v-if="field.type === 'template'" v-slot="{ row }">
          <slot :name="field.prop" :row="row" />
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页区域 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNum"
      :page-sizes="[1, 2, 5, 10]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalNum"
    ></el-pagination>
  </el-card>
</template>

<script>
export default {
  name: 'TableView',
  props: {
    tableTitle: String,
    tableFields: Array,
    list: Array,
    total: Number,
    update: Boolean
  },
  watch: {
    list (newVal) {
      this.tableList = newVal
    },
    total (newVal) {
      this.totalNum = newVal
    },
    update (newVal) {
      if (newVal === false) this.handleQuery()
    }
  },
  data () {
    return {
      // 搜索框 参数
      queryInfo: {
        query: '',
        pageNum: 1,
        pageSize: 5
      },
      totalNum: 0,

      // 表格 列表
      tableList: []
    }
  },
  created () {
    this.handleQuery()
  },
  methods: {
    // 监听 pagesize 改变
    handleSizeChange (newSize) {
      this.queryInfo.pageSize = newSize
      this.handleQuery()
    },
    // 监听 页码值 改变
    handleCurrentChange (newPage) {
      this.queryInfo.pageNum = newPage
      this.handleQuery()
    },

    // 事件
    handleQuery () {
      this.$emit('query', this.queryInfo)
    },
    handleAdd () {
      this.$emit('add')
      this.handleQuery()
    }
  }
}
</script>

<style>
</style>