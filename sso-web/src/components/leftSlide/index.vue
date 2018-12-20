<template>
  <div class="left-side">
    <div class="left-side-inner">
      <router-link to="/" class="logo block">
        <img src="./images/logo.png" alt="小样">
      </router-link>
      <el-menu class="menu-box" theme="dark" router :default-active="$route.path">
        <div v-for="(item, index) in nav_menu_data" :key="index">
          <el-menu-item
            class="menu-list"
            v-if="typeof item.child === 'undefined'"
            :index="item.path">
            <i class="icon fa" :class="item.icon"></i>
            <span v-text="item.title" class="text"></span>
          </el-menu-item>
          <el-submenu :index="item.path" v-else>
            <template slot="title">
              <i class="icon fa" :class="item.icon"></i>
              <span v-text="item.title" class="text"></span>
            </template>
            <el-menu-item
              class="menu-list"
              v-for="(sub_item, sub_index) in item.child"
              :index="sub_item.path"
              :key="sub_index">
              <!--<i class="icon fa" :class="sub_item.icon"></i>-->
              <span v-text="sub_item.title" class="text"></span>
            </el-menu-item>
          </el-submenu>
        </div>
      </el-menu>
    </div>
  </div>
</template>
<script type="text/javascript">
export default {
    name: 'slide',
    data() {
        return {
            nav_menu_data: [
                {
                    title: '主页',
                    path: '/home',
                    icon: 'fa-home'
                },
                {
                    title: '信息管理',
                    path: '/table',
                    icon: 'fa-table',
                    child: [
                        {
                            title: '报价信息',
                            path: '/info/quoted'
                        },{
													title: '阅读者名单',
													path: '/info/reader'
												},
												{
														title: '阅读日志',
														path: '/table/sort'
												},
												{
														title: '上链日志',
														path: '/table/sort'
												}
                    ]
                },{
                    title: '授权管理',
                    path: '/perm',
                    icon: 'fa-table',
                    child: [
												{
														title: '授权处理',
														path: '/permission/apply'
												},{
                            title: '权限查询',
                            path: '/permission/userInfoPerm'
												}
										]
                },{
                    title: '阅读管理',
                    path: '/read',
                    icon: 'fa-table',
                    child: [
											{
													title: '报价信息',
													path: '/read/quoted'
											},
											{
													title: '发布者名单',
													path: '/read/publisher'
											},{
													title: '上链信息',
													path: '/table/base'
											},{
													title: '阅读日志',
													path: '/table/base'
											}
                    ]
                },
                {
                    title: '统计管理',
                    path: '/charts',
                    icon: 'fa-bar-chart-o',
                    child: [
                        {
                            title: '统计',
                            path: '/charts/bar'
                        }
                    ]
                }
            ]
        };
    },
    created() {
    },
    methods: {
		}
};
</script>
