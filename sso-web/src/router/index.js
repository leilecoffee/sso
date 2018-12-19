/**
 * Created by zzmhot on 2017/3/23.
 *
 * 路由Map
 *
 * @author: zzmhot
 * @github: https://github.com/zzmhot
 * @email: zzmhot@163.com
 * @Date: 2017/3/23 18:30
 * @Copyright(©) 2017 by zzmhot.
 *
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import store from 'store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

//import components
//view page warp component
import viewPageComponent from 'pages/App'

//home
import homeComponent from 'pages/home'
//404
import noPageComponent from 'pages/error/404'
//login
import loginComponent from 'pages/user/login'

Vue.use(VueRouter)

//使用AMD方式加载
// component: resolve => require(['pages/home'], resolve),
const routes = [{
  path: '/404',
  name: 'notPage',
  component: noPageComponent
}, {
  path: '*',
  redirect: '/404'
}, {
  path: '/login',
  name: 'login',
  component: loginComponent
}, {
  path: '/',
  redirect: '/home',
  component: viewPageComponent,
  children: [{
    path: '/home',
    name: 'home',
    component: homeComponent,
    meta: {
      title: "主页",
      auth: true
    }
  },{
    path: '/user/info',
    name: 'userInfo',
    component: resolve => require(['pages/user/info'], resolve),
    meta: {
      title: "个人中心",
      auth: true
    }
  },{
    path: '/user/changepwd',
    name: 'changePwd',
    component: resolve => require(['pages/user/changepwd'], resolve),
    meta: {
      title: "修改密码",
      auth: true
    }
  }, {
    path: '/info/quoted',
    name: 'infoQuoted',
    component: resolve => require(['pages/info/quoted/table'], resolve),
    meta: {
      title: "报价信息",
      auth: true
    }
  },{
    path: '/info/quotedSave',
    name: 'infoQuotedSave',
    component: resolve => require(['pages/info/quoted/save'], resolve),
    meta: {
      title: "发布信息",
      auth: true
    }
  },{
    path: '/permission/apply',
    name: 'permissionApply',
    component: resolve => require(['pages/permission/apply/table'], resolve),
    meta: {
      title: "授权处理",
      auth: true
    }
  },{
    path: '/permission/userInfoPerm',
    name: 'userInfoPerm',
    component: resolve => require(['pages/permission/userInfoPerm/table'], resolve),
    meta: {
      title: "权限查询",
      auth: true
    }
  }, {
    path: '/permission/userInfoPermSave',
    name: 'userInfoPermSave',
    component: resolve => require(['pages/permission/userInfoPerm/save'], resolve),
    meta: {
      title: "添加授权信息",
      auth: true
    }
  }, {
    path: '/read/publisher',
    name: 'readPublisher',
    component: resolve => require(['pages/read/publisher'], resolve),
    meta: {
      title: "发布者名单",
      auth: true
    }
  }, {
    path: '/info/reader',
    name: 'infoReader',
    component: resolve => require(['pages/info/reader'], resolve),
    meta: {
      title: "阅读者名单",
      auth: true
    }
  }]
}]

const router = new VueRouter({
  routes,
  mode: 'hash', //default: hash ,history
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return {x: 0, y: 0}
    }
  }
})

//全局路由配置
//路由开始之前的操作
router.beforeEach((to, from, next) => {
  NProgress.done().start()
  let toName = to.name
  // let fromName = from.name
  let is_login = store.state.user_info.login

  if (!is_login && toName !== 'login') {
    next({
      name: 'login'
    })
  } else {
    if (is_login && toName === 'login') {
      next({
        path: '/'
      })
    } else {
      next()
    }
  }
})

//路由完成之后的操作
router.afterEach(route => {
  NProgress.done()
})

export default router
