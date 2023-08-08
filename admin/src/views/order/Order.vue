<template>
  <div>
    <!-- 表格区域 -->
    <table-page tableTitle="添加订单" :tableFields="tableFields" :total="totalNum" :list="tableList" ref="tableRef" :tagOptions="orderTag"
      :update="formDialogVisible" @query="getOrderList" @add="addOrderDialog">
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(orderTag, row.status)" size="mini">{{
          getFieldLable(orderTag, row.status)
        }}</el-tag>
      </template>
      <template v-slot:operate="{ row }">
        <!-- 修改按钮 -->
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="updateOrderDialog(row)">修改</el-button>
      </template>
    </table-page>

    <!-- 弹窗区域 -->
    <form-dialog :visible.sync="formDialogVisible" :dialogTitle="formDialogTitle" :fields="formFields" :formData="form"
      @submit="handleFormSubmit" />
  </div>
</template>

<script>
import { getOrderList, addOrder, updateOrder } from '@/api/order'
import { TAG_ORDER, getFieldLable, getFieldTagType, TAG_GENDER_EN } from '@/utils/tag'

export default {
  name: 'OrderView',
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
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' },
        { label: '总价格', prop: 'totalPrice' },
        { label: '开始时间', prop: 'startTime' },
        { label: '服务时长', prop: 'serveDuration' },
        { label: '结束时间', prop: 'endTime' },
        { label: '状态', prop: 'status', type: 'template' },
        { label: '创建时间', prop: 'createTime' },
        { label: '更新时间', prop: 'updateTime' },
        { label: '操作', prop: 'operate', type: 'template', width: '180px' }
      ],

      // 标签
      orderTag: TAG_ORDER,

      // TODO 下订单
      // 需求列表
      serverList: [
        { value: 1, label: '英雄联盟' },
        { value: 2, label: '王者荣耀' }
      ],
      voiceList: [
        { value: 3, label: '御姐' },
        { value: 4, label: '甜妹' },
        { value: 5, label: '奶狗' },
        { value: 6, label: '大叔' }
      ],
      natureList: [
        { value: 7, label: '温柔' },
        { value: 8, label: '高冷' },
        { value: 9, label: '毒蛇' },
        { value: 10, label: '话痨' },
        { value: 11, label: '逗逼' }
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
    addOrderDialog () {
      this.formDialogVisible = true
      this.formDialogTitle = '添加订单'
      this.formFields = [
        { label: '店铺ID', prop: 'sid' },
        { label: '用户ID', prop: 'uid' },
        {
          label: '服务类型',
          prop: 'serveType',
          type: 'select',
          options: this.serverList
        },
        {
          label: '性别',
          prop: 'gender',
          type: 'select',
          options: TAG_GENDER_EN
        },
        {
          label: '音色',
          prop: 'voice',
          type: 'checkbox',
          options: this.voiceList
        },
        {
          label: '性格',
          prop: 'nature',
          type: 'checkbox',
          options: this.natureList
        },
        { label: '备注', prop: 'note' }
      ]
      this.form = {}
      this.handleFormSubmit = this.addOrder
    },
    // 修改
    updateOrderDialog (temp) {
      this.formDialogVisible = true
      this.formDialogTitle = '修改订单'
      this.formFields = [
        { label: 'ID', prop: 'id', disabled: true },
        {
          label: '状态',
          prop: 'status',
          type: 'select',
          options: TAG_ORDER
        }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateOrder
    },

    // 获取 Tag类型
    getFieldTagType (list, value) {
      return getFieldTagType(list, value)
    },
    getFieldLable (list, value) {
      return getFieldLable(list, value)
    },

    // 请求
    // 获取
    getOrderList (query) {
      getOrderList(query)
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
    addOrder (form) {
      addOrder(form)
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
    updateOrder (form) {
      console.log(form)
      updateOrder(form)
        .then(() => {
          this.formDialogVisible = false
          this.$refs.tableRef.handleQuery()
          this.$message.success('修改成功')
        })
        .catch(() => {
          this.$message.error('修改失败')
        })
    }
  }
}
</script>

<style></style>
