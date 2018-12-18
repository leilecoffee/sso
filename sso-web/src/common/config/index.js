/**
 * 定义全局配置文件
 */
export const app_name = 'sso'
//本地存储的前缀
export const storage_prefix = app_name + '_storage_'
//base64加密前缀
export const base64_prefix = app_name + '_base64_'
//接入服务器接口地址根目录
export const server_base_url = '/pf'

//消息状态选项
export const infoStateOptions = [{
	value: '0',
	label: '未上链'
}, {
	value: '1',
	label: '已上链'
}]