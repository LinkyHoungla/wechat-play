import service from '@/utils/request'

export function login (username, password) {
  return service({
    url: '/admin/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function logout (id) {
  return service({
    url: `/admin/logout/${id}`,
    method: 'get'
  })
}

export function getLoginfo () {
  return service({
    url: '/admin/loginfo',
    method: 'get'
  })
}
