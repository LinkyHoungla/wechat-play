<template>
  <el-dialog
    :title="dialogTitle"
    :visible.sync="localVisible"
    width="50%"
    @close="handleClose"
  >
    <el-form :model="form" ref="formRef">
      <el-form-item
        v-for="field in fields"
        :key="field.prop"
        :label="field.label"
        :prop="field.prop"
      >
        <template v-if="field.type === undefined">
          <el-input v-model="form[field.prop]" />
        </template>
        <template v-if="field.type === 'select'">
          <el-select v-model="form[field.prop]">
            <el-option
              v-for="option in field.options"
              :key="option.value"
              :label="option.label"
              :value="option.value"
            />
          </el-select>
        </template>
        <template v-if="field.type === 'date'">
          <el-date-picker v-model="form[field.prop]" type="date" placeholder="选择日期" />
        </template>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handleSubmit">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  props: {
    visible: {
      type: Boolean,
      required: true,
      // 添加 .sync 修饰符，保持数据双向绑定
      sync: true
    },
    dialogTitle: String,
    fields: Array,
    formRef: String,
    formData: Object
  },
  watch: {
    // 监听父组件传递的 prop 变化，更新子组件中的局部属性
    visible (newVal) {
      this.localVisible = newVal
    },
    formData (newVal) {
      this.form = JSON.parse(JSON.stringify(newVal))
    }
  },
  data () {
    return {
      // 显示
      localVisible: this.visible,
      // 表单数据对象，用于存储表单项的值
      form: this.formData
    }
  },
  methods: {
    handleClose () {
      // 触发关闭对话框的事件
      this.$refs.formRef.resetFields()
      this.$emit('update:visible', false)
    },
    handleCancel () {
      // 触发取消按钮点击事件
      this.$refs.formRef.resetFields()
      this.$emit('update:visible', false)
    },
    handleSubmit () {
      // 触发确定按钮点击事件
      this.$emit('submit', this.form)
    }
  }
}
</script>
