<template>
  <div class="menu-right" v-if="get_user_info.login">
    <div class="notification-menu">
      <el-dropdown trigger="click" class="notification-list">
        <div class="notification-btn">
          <span v-text="name"></span>
          <span class="icon"></span>
        </div>
        <el-dropdown-menu slot="dropdown" class="dropdown-menu">
					<el-dropdown-item class="dropdown-list">
						<a href="javascript:" class="dropdown-btn" @click="userinfo()">
							<i class="icon fa fa-user"></i>
							<span>个人中心</span>
						</a>
					</el-dropdown-item>
          <el-dropdown-item class="dropdown-list">
            <a href="javascript:" class="dropdown-btn" @click="changePwd()">
              <i class="icon fa fa-user"></i>
              <span>修改密码</span>
            </a>
          </el-dropdown-item>
          <!-- <el-dropdown-item class="dropdown-list">
            <a href="javascript:" class="dropdown-btn" @click="setting()">
              <i class="icon fa fa-cog"></i>
              <span>设置</span>
            </a>
          </el-dropdown-item> -->
          <el-dropdown-item class="dropdown-list">
            <a href="javascript:" class="dropdown-btn" @click="logout()">
              <i class="icon fa fa-sign-out"></i>
              <span>安全退出</span>
            </a>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script type="text/javascript">
  import {mapGetters, mapActions} from 'vuex'
  import {GET_USER_INFO} from 'store/getters/type'
  import {SET_USER_INFO} from 'store/actions/type'

  export default{
		data() {
			return {
				name:""
			}
		},
    computed: {
      ...mapGetters({
        get_user_info: GET_USER_INFO
      })
    },
		created(){
			this.name = this.get_user_info.user.name;
			this.$root.Bus.$on("name", content => { 
				this.name = this.get_user_info.user.name
			}); 
		},
    methods: {
      ...mapActions({
        set_user_info: SET_USER_INFO
      }),
			
			userinfo() {
				this.$router.replace({name: "userInfo"});
			},
			changePwd() {
				this.$router.replace({name: "changePwd"});
			},
			setting() {
				//设置
			},
      //退出
      logout(){
        this.$confirm('此操作将退出登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$fetch.api.logout()
            .then(({msg}) => {
              this.$message.success(msg)
              this.set_user_info(null)
              setTimeout(this.$router.replace({name: "login"}), 500)
            })
        }).catch(() => {

        })
      }
    }
  }
</script>
