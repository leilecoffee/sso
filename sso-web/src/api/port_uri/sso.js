/**
 * 定义访问的url
 */
//用户登录
export const login = "login"
//用户登出
export const logout = "logout"
//获取当前用户菜单
export const menu = "sys/menu"
//获取当前用户权限
export const permcode = "sys/permcode"
//个人中心
export const userinfo = "user/info"
//个人中心修改
export const usermodify = "user/modify"
//修改密码
export const changepwd = "user/changepwd"
//获取登录用户的公司信息
export const companyUserGet = "company/user/get"
//获取产品列表
export const productList = "product/list"
//获取产品类型
export const productTypeGet = "productType/get"

export const quotedQuery = "quoted/query"

export const quotedSave = "quoted/save"

export const quotedDelete = "quoted/delete"
//获取登录人授权的信息
export const userInfoPermQuery = "userInfoPerm/query"
//获取公司选项
export const companyOptions = "company/options"
//获取授权表列表
export const infoPermList = "infoPerm/list"

export const userInfoPermSave="userInfoPerm/save"

export const userInfoPermDelete ="userInfoPerm/delete"
//获取发布者名单
export const companyPublisher ="company/publisher"
//授权申请 新增
export const applySave = "apply/save"
//登录人授权申请查询
export const applyQuery="apply/query"
//授权申请  授权操作
export const applyAuthorization="apply/authorization"
//申请驳回操作
export const applyReject="apply/reject"
//获取阅读者名单
export const companyReader="company/reader"
//阅读者获取授权信息
export const infoQuery = "info/query"
//阅读者获取授权信息详情
export const infoDetail = "info/detail"
//阅读者导出授权信息
export const infoExport = "info/export"
//发布者-阅读日志
export const readlogPublish = "readlog/publish"
//阅读者-阅读日志
export const readlogRead = "readlog/read"