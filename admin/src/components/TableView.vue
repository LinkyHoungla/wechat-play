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
        <el-select v-model="queryInfo.tag" clearable >
            <el-option
              v-for="option in tagOptions"
              :key="option.value"
              :label="option.label"
              :value="option.value"
            />
          </el-select>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="handleAdd" v-if="tableTitle !== undefined" :disabled="disabled" >{{
          tableTitle
        }}</el-button>
      </el-col>
    </el-row>

    <!-- 列表区域 -->
    <el-table
      :cell-style="{'text-align':'center'}"
      :header-cell-style="{'text-align':'center'}"
      :data="tableList"
      :row-key="(row) => row.id"
      :expand-row-keys="expandRowKeys"
      @expand-change="handleExpandChange"
    >
      <el-table-column v-if="hasExpand" type="expand" v-slot="{ row }" >
        <slot name="expand" :row="row" />
      </el-table-column>
      <el-table-column
        v-for="field in tableFields"
        :key="field.prop"
        :label="field.label"
        :prop="field.prop"
        :width="field.width"
      >
        <!-- 判断是否使用具名插槽 -->
        <template v-if="field.type === 'template'" v-slot="{ row }" >
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
    tagOptions: Array,
    list: Array,
    total: Number,
    hasExpand: Boolean,
    disabled: Boolean
  },
  watch: {
    list (newVal) {
      this.tableList = newVal
    },
    total (newVal) {
      this.totalNum = newVal
    },
    'queryInfo.tag': function (newVal) {
      this.handleQuery()
    }
  },
  data () {
    return {
      // 搜索框 参数
      queryInfo: {
        query: '',
        tag: '',
        pageNum: 1,
        pageSize: 5
      },
      totalNum: 0,

      // 表格 列表
      tableList: [],

      // 展开行
      expandRowKeys: []
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
    // 监听 展开行
    handleExpandChange (row) {
      if (row && this.expandRowKeys.indexOf(row.id)) {
        this.expandRowKeys = [row.id]
        this.$emit('expand', row.id)
      } else {
        this.expandRowKeys = []
      }
    },

    // 事件
    handleQuery () {
      this.$emit('query', this.queryInfo)
    },
    handleAdd () {
      this.$emit('add')
    }
  }
}
</script>

<style lang="less" scoped>
.el-table {
  height: 680px;

  overflow-y: auto;

  margin-top: 7px;
  font-size: 12px;
}

.el-pagination {
  margin-top: 10px;
}

</style>
