<template>
  <div class="left-side">
    <div class="left-side-inner">
			<div class="left-header">
					<img src="~assets/images/left_header_log.jpg" class="logo"><br/>
					<span class="title" v-text="user.companyName"></span><br>
					<span v-text="user.name"></span><br>
					<span v-text="user.loginTime"></span>
			</div>
      <el-menu class="menu-box" theme="dark" router :default-active="$route.path" :collapse-transition="false">
        <div class="menu" v-for="(item, index) in nav_menu_data" :key="index">
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
              <span v-text="sub_item.title" class="subText"></span>
            </el-menu-item>
          </el-submenu>
        </div>
      </el-menu>
    </div>
  </div>
</template>
<script type="text/javascript">
	import {mapGetters, mapActions} from 'vuex'
	import {GET_USER_INFO} from 'store/getters/type'
export default {
    name: 'slide',
    data() {
        return {
						user:{
							name:"",
							companyName:"",
							loginTime:""
						},
            nav_menu_data: []
        };
    },
		computed: {
		  ...mapGetters({
		    get_user_info: GET_USER_INFO
		  })
		},
    created() {
			let user = this.get_user_info.user;
			this.user.name = user.name;
			this.user.companyName = user.companyName;
			this.user.loginTime = user.loginTime;
			this.menu();
    },
    methods: {
			menu(){
					this.$fetch.api.menu().then(({data}) => {
							if(data){
								this.nav_menu_data = data.child;
							}
					}).catch(() => {});
			}
		}
};
</script>
