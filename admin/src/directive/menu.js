/**
 * 菜单自定义指令
 */

export const directive = {
  inserted (el, binding, vnode) {
    const subMenu = el.querySelector('.el-submenu__title')
    const menu = el.querySelector('.el-menu')

    if (subMenu && menu) {
      subMenu.addEventListener('mouseenter', () => {
        menu.style.display = 'block'
      })
      subMenu.addEventListener('mouseleave', () => {
        menu.style.display = 'none'
      })
    }
  }
}
