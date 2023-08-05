import service from '@/utils/request'

// 获取 列表
export function getStoreList (pageParam) {
  return service({
    url: '/store/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addStore (data) {
  return service({
    url: '/store',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateStore (data) {
  return service({
    url: '/store',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除
export function deleteStore (id) {
  return service({
    url: `/store/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}

// 获取 列表
export function getBalanceList (pageParam) {
  return service({
    url: '/store/balance/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addBalance (data) {
  return service({
    url: '/store/balance',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateBalance (data) {
  return service({
    url: '/store/balance',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}
