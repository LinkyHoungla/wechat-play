<template>
  <div class="info-view-box">
    <el-form label-position="left" inline class="info-table-expand">
      <el-form-item v-for="item in field" :key="item.label" :label="item.label">
        <!-- 展示 -->
        <template v-if="!isEdit || item.edit === undefined">
          <span v-if="item.type === undefined || item.type === 'date' ">{{ form[item.prop] }}</span>
          <el-tag
            v-else-if="item.type === 'select'"
            :type="getFieldTagType(item.options, form[item.prop])"
            size="mini"
            >{{ getFieldLable(item.options, form[item.prop]) }}</el-tag
          >
          <div v-else-if="item.type === 'img'" class="info-view-avatar">
            <img :src="form[item.prop]" />
          </div>
        </template>

        <template v-else >
          <el-input size="mini" v-if="item.type === undefined" v-model="form[item.prop]" />
          <el-select size="mini" v-if="item.type === 'select'" v-model="form[item.prop]" >
            <el-option
              v-for="option in item.options"
              :key="option.value"
              :label="option.label"
              :value="option.value"
              :disabled="option.disabled"
            />
          </el-select>
          <el-date-picker size="mini" v-if="item.type === 'date'" v-model="form[item.prop]" type="date" placeholder="选择日期" />
          <template v-if="item.type === 'img'">
            <slot name="img" />
          </template>
        </template>

        <!-- 修改 -->
      </el-form-item>
    </el-form>
    <div class="update-box">
      <el-button size="mini" v-show="!isEdit" @click="isEdit = true" :disabled="infoDisabled"
        >修改</el-button
      >
      <el-button
        size="mini"
        v-show="isEdit"
        @click="handleSubmit"
        type="success"
        >完成</el-button
      >
    </div>
  </div>
</template>

<script>
import { getFieldLable, getFieldTagType, TAG_GENDER } from '@/utils/tag'
export default {
  name: 'InfoView',
  props: {
    field: Array,
    content: Object,
    infoDisabled: Boolean
  },
  data () {
    return {
      genderTag: [],
      isEdit: false,
      form: this.content
    }
  },
  created () {
    this.genderTag = TAG_GENDER
  },
  watch: {
    content (newVal) {
      this.form = JSON.parse(JSON.stringify(newVal))
    }
  },
  methods: {
    getFieldTagType (list, value) {
      return getFieldTagType(list, value)
    },

    getFieldLable (list, value) {
      return getFieldLable(list, value)
    },

    handleSubmit () {
      this.isEdit = false
      this.$emit('submit', this.form)
    }
  }
}
</script>

<style lang="less">
.info-view-box {
  padding: 0 15px;

  .info-table-expand {
    font-size: 0;

    .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;

      label {
        width: 90px;
        color: #99a9bf;
      }
    }
  }

  .info-view-avatar {
    width: 36px;
    height: 36px;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }

  .update-box {
    display: flex;
    justify-content: center;
    align-items: center;

    height: 36px;
    margin: 5px;

    .el-button {
      margin: 0;
    }
  }
}
</style>
