import service from '@/utils/request'

// 获取 列表
export function getCompanionList (pageParam) {
  return service({
    url: '/companion/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addCompanion (data) {
  return service({
    url: '/companion',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateCompanion (data) {
  return service({
    url: '/companion',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除
export function deleteCompanion (id) {
  return service({
    url: `/companion/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}
