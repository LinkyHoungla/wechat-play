export const TAG_STATUS = [
  { value: 'ACTIVE', label: '正常', tag: 'success' },
  { value: 'BANNED', label: '封禁', tag: 'info' },
  { value: 'DELETED', label: '已删除', tag: 'danger', disabled: true }
]

export const TAG_ORDER = [
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
]

export const TAG_GENDER = [
  { value: 'MALE', label: '男', tag: 'danger' },
  { value: 'FEMALE', label: '女', tag: 'success' },
  { value: 'ALL', label: '不限', tag: 'info', disabled: true }
]

export const TAG_GENDER_EN = [
  { value: 'MALE', label: '男', tag: 'danger' },
  { value: 'FEMALE', label: '女', tag: 'success' },
  { value: 'ALL', label: '不限', tag: 'info' }
]

export const TAG_GAME = [
  { value: 'SERVER', label: '区服', tag: 'success' },
  { value: 'GAME', label: '游戏', tag: 'info' },
  { value: 'RANK', label: '等级', tag: 'danger' },
  { value: 'MODE', label: '模式', tag: 'success' }
]

export const TAG_SERVICE = [
  { value: 'SERVICE', label: '服务', tag: 'success' },
  { value: 'VOICE', label: '音色', tag: 'info' },
  { value: 'NATURE', label: '性格', tag: 'danger' }
]

export function getFieldTagType (list, value) {
  const field = list.find((item) => item.value === value)
  return field ? field.tag : '-'
}

export function getFieldLable (list, value) {
  const field = list.find((item) => item.value === value)
  return field ? field.label : null
}
