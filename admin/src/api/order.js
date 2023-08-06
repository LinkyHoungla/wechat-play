import service from '@/utils/request'

// 获取 列表
export function getOrderList (pageParam) {
  return service({
    url: '/order/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addOrder (data) {
  return service({
    url: '/order',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateOrder (data) {
  return service({
    url: '/order',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 钱包
// 获取 列表
export function getWalletList (pageParam) {
  return service({
    url: '/order/wallet/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateWallet (data) {
  return service({
    url: '/order/wallet',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}
