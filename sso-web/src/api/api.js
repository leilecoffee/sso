/**
 *  用户请求数据配置.
 */
import fetch from 'common/fetch'
import {
	port_sso
} from 'api/port_uri/index'

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
//获取登录用户的公司信息
export function companyUserGet() {
	return fetch({
		url: port_sso.companyUserGet,
		method: 'post'
	})
}

//获取产品列表
export function productList(data) {
	return fetch({
		url: port_sso.productList,
		method: 'post',
		data
	})
}

//获取产品类别信息
export function productTypeGet(data){
	return fetch({
		url: port_sso.productTypeGet,
		method: 'post',
		data
	})
}

//报价信息保存
export function quotedSave(data){
	return fetch({
		url: port_sso.quotedSave,
		method: 'post',
		data
	})
}
