var account = {
	"username": "1056196129@qq.com",
	"password": "1234qwer"
}

var data = [{
		"name": "获取菜单",
		"method": "POST",
		"url": "/sys/menu",
		"param": {}
	},
	{
		"name": "获取权限",
		"method": "POST",
		"url": "/sys/menu",
		"param": {}
	},
	{
		"name": "用户分页数据",
		"method": "POST",
		"url": "/user/page",
		"param": {
		    "pageSize":"10",
		    "pageNum":"1"
		}
	},
	{
		"name": "新增用户",
		"method": "POST",
		"url": "/user/insert",
		"param": {
		    "username":"10",
		    "password":"1",
		    "name":"1",
		    "phone":"1"
		}
	},
	{
		"name": "删除用户",
		"method": "POST",
		"url": "/user/delete/{id}",
		"param": {}
	}
]
