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

//获取登录人发布的报价信息
export function quotedQuery(data){
	return fetch({
		url: port_sso.quotedQuery,
		method: 'post',
		data
	})
}

//登录人删除发布的报价信息
export function quotedDelete(data){
	return fetch({
		url: port_sso.quotedDelete,
		method: 'post',
		data
	})
}
//获取登录人授权的信息
export function userInfoPermQuery(data){
	return fetch({
		url: port_sso.userInfoPermQuery,
		method: 'post',
		data
	})
}

//获取公司选项
export function companyOptions(data){
	return fetch({
		url: port_sso.companyOptions+"/"+data.filter,
		method: 'post'
	})
}


export function infoPermList(){
	return fetch({
		url: port_sso.infoPermList,
		method: 'post'
	})
}

export function userInfoPermSave(data){
	return fetch({
		url: port_sso.userInfoPermSave,
		method: 'post',
		data
	})
}

export function userInfoPermDelete(data){
	return fetch({
		url: port_sso.userInfoPermDelete,
		method: 'post',
		data
	})
}
//获取发布者名单
export function companyPublisher(data){
	return fetch({
		url: port_sso.companyPublisher,
		method: 'post',
		data
	})
}

//授权申请 新增
export function applySave(data){
	return fetch({
		url: port_sso.applySave,
		method: 'post',
		data
	})
}

//登录人授权申请查询
export function applyQuery(data){
	return fetch({
		url: port_sso.applyQuery,
		method: 'post',
		data
	})
}

//授权申请  授权操作
export function applyAuthorization(data){
	return fetch({
		url: port_sso.applyAuthorization,
		method: 'post',
		data
	})
}

//申请驳回操作
export function applyReject(data){
	return fetch({
		url: port_sso.applyReject,
		method: 'post',
		data
	})
}

//获取阅读者名单
export function companyReader(data){
	return fetch({
		url: port_sso.companyReader,
		method: 'post',
		data
	})
}

//阅读者获取授权信息
export function infoQuery(data){
	return fetch({
		url: port_sso.infoQuery,
		method: 'post',
		data
	})
}

//阅读者获取授权信息详情
export function infoDetail(data){
	return fetch({
		url: port_sso.infoDetail,
		method: 'post',
		data
	})
}
