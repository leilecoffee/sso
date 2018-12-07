/**
 *  用户请求数据配置.
 */
import fetch from 'common/fetch'
import {port_sso} from 'api/port_uri/index'

//登录
export function login(data) {
	return fetch({
		url: port_sso.login,
		method: 'post',
		data
	})
}
//登出
export function logout() {
	return fetch({
		url: port_sso.logout,
		method: 'post'
	})
}

//个人中心
export function userinfo() {
	return fetch({
		url: port_sso.userinfo,
		method: 'post'
	})
}

//个人中心修改
export function usermodify(data) {
	return fetch({
		url: port_sso.usermodify,
		method: 'post',
		data
	})
}

//个人中心修改
export function changepwd(data) {
	return fetch({
		url: port_sso.changepwd,
		method: 'post',
		data
	})
}