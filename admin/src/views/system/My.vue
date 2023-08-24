<template>
  <div class="my-container">
    <h2 class="title">信息修改</h2>
    <div class="form-box">
      <el-upload
        class="avatar-uploader"
        action="upload"
        :http-request="uploadAvatar"
        :show-file-list="false"
        :before-upload="beforeAvatarUpload"
      >
        <i class="el-icon-plus avatar-uploader-icon" />
        <span class="avatar-content">头像上传</span>
      </el-upload>
      <el-form
        :model="form"
        inline
        ref="formRef"
        size="mini"
        class="content"
        label-width="90px"
        :rules="rules"
      >
        <el-form-item label="昵称" prop="name">
          <el-input v-model="form.name" clearable />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" clearable />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" clearable />
        </el-form-item>
        <el-form-item label="确认密码" prop="confirm">
          <el-input type="password" v-model="form.confirm" clearable />
        </el-form-item>
      </el-form>
    </div>
    <div class="submit">
      <el-button @click="updateAdmin">提交</el-button>
    </div>
  </div>
</template>

<script>
import { updateAdmin, updateAvatar } from '@/api/admin'
import { nicknameValid, usernameValid } from '@/utils/validate'

export default {
  name: 'MyView',
  data () {
    const confirmValid = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }

    return {
      form: {},
      upload: process.env.VUE_APP_BASE_API + '/admin/avatar',

      // 验证规则
      rules: {
        name: [
          {
            min: 1,
            max: 20,
            message: '长度在 1 到 20 个字符',
            trigger: 'blur'
          },
          { validator: nicknameValid, trigger: 'blur' }
        ],
        username: [
          {
            min: 3,
            max: 10,
            message: '长度在 3 到 10 个字符',
            trigger: 'blur'
          },
          { validator: usernameValid, trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 6,
            max: 16,
            message: '长度在 6 到 16 个字符',
            trigger: 'blur'
          },
          { validator: usernameValid, trigger: 'blur' }
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: confirmValid, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }

      return isJPG && isLt2M
    },

    updateAdmin () {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          updateAdmin(this.form)
            .then(() => {
              this.$message.success('修改成功')
            })
            .catch(() => {
              this.$message.error('修改失败')
            })
        }
      })
    },
    uploadAvatar (param) {
      const formData = new FormData()
      formData.append('file', param.file)
      updateAvatar(param)
        .then(() => {
          this.$message.success('上传成功')
        })
        .catch(() => {
          this.$message.error('上传失败')
        })
    }
  }
}
</script>

<style lang="less">
.my-container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  overflow: hidden;

  min-height: 800px;

  padding: 25px;

  border-radius: 4px;
  border: 1px solid #ebeef5;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background-color: #fff;

  .form-box {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;

    position: relative;

    width: 95%;
    min-height: 400px;

    .avatar-uploader {
      .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
      }

      .el-upload:hover {
        border-color: #409eff;
      }

      .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
      }

      .avatar-content {
        position: absolute;
        top: 50%;
        right: 50%;

        color: #8c939d;

        transform: translate(50%, 25px);
      }
    }

    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .content {
      position: relative;
      top: 0;
      left: 5%;

      font-size: 0;

      .el-form-item {
        margin-right: 0;
        margin-bottom: 20px;
        width: 50%;

        label {
          color: #50545a;
        }
      }
    }
  }
}
</style>
