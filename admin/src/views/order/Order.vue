<template>
  <div>
    <!-- 表格区域 -->
    <table-page tableTitle="添加订单" :tableFields="tableFields" :total="totalNum" :list="tableList"
      :update="formDialogVisible" @query="getOrderList" @add="addOrderDialog">
      <template v-slot:status="{ row }">
        <el-tag :type="getFieldTagType(row.status)" size="mini">{{
          tagFields.find((item) => item.value === row.status).label
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

export default {
  name: 'OrderView',
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

      // TODO 标签独立文件
      // 标签
      tagFields: [
        { value: null, label: null, tag: '' },
        { value: 'CREATE', label: '创建', tag: 'info' },
        { value: 'DISPATCHED', label: '派发中', tag: 'info' },
        { value: 'PEND', label: '待确认', tag: 'info' },
        { value: 'CONFIRMED', label: '等待开始', tag: 'info' },
        { value: 'PROGRESS', label: '服务中', tag: 'sucess' },
        { value: 'ARCHIVED', label: '待评价', tag: 'info' },
        { value: 'COMPLETED', label: '完成', tag: 'sucess' },
        { value: 'CANCELLED', label: '取消', tag: 'info' },
        { value: 'REFUNDED', label: '退款', tag: 'danger' },
        { value: 'FAILED', label: '失败', tag: 'danger' },
        { value: 'HOLD', label: '暂停', tag: 'info' },
        { value: 'REVIEW', label: '归档', tag: 'danger' }
      ],

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
          options: [
            { value: 'MALE', label: '男' },
            { value: 'FEMALE', label: '女' },
            { value: 'ALL', label: '都行' }
          ]
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
          options: [
            { value: 'CREATE', label: '创建', disabled: true },
            { value: 'DISPATCHED', label: '派发中' },
            { value: 'PEND', label: '待确认' },
            { value: 'CONFIRMED', label: '等待开始' },
            { value: 'PROGRESS', label: '服务中' },
            { value: 'ARCHIVED', label: '待评价' },
            { value: 'COMPLETED', label: '完成' },
            { value: 'CANCELLED', label: '取消' },
            { value: 'REFUNDED', label: '退款' },
            { value: 'FAILED', label: '失败' },
            { value: 'HOLD', label: '暂停' },
            { value: 'REVIEW', label: '归档', tag: 'danger' }
          ]
        }
      ]
      this.form = temp
      this.handleFormSubmit = this.updateOrder
    },

    // 获取 Tag类型
    getFieldTagType (value) {
      const field = this.tagFields.find((item) => item.value === value)
      return field ? field.tag : ''
    },

    // 请求
    // 获取
    getOrderList (query) {
      getOrderList(query)
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
    addOrder (form) {
      addOrder(form)
        .then(() => {
          this.formDialogVisible = false
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
