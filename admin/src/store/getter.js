const getters = {
  name: state => state.login.name,
  role: state => state.login.role,
  avatar: state => state.login.avatar,
  menu: state => state.login.menu,
  permission: state => state.login.permission
}

export default getters
