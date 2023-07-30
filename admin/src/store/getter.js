const getters = {
  menu: state => state.login.menu,
  avatar: state => state.login.avatar,
  name: state => state.login.name,
  permission: state => state.login.permission,
  isCollapse: state => state.app.isCollapse
}

export default getters
