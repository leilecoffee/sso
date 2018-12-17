<template>
	<div class="panel">
		<panel-title :title="$route.meta.title"></panel-title>
		<div class="panel-body"
				v-loading="load_data"
				element-loading-text="拼命加载中">
			<el-row>
				<el-col :span="8">
					<el-form ref="userForm" :model="userInfo" :rules="rules" label-width="100px">
						<el-form-item label="用户名" >
							<el-input v-model="userInfo.username" disabled></el-input>
						</el-form-item>
						<el-form-item label="所属公司" >
							<el-input v-model="userInfo.companyName" disabled></el-input>
						</el-form-item>
						<el-form-item label="姓名" prop="name">
							<el-input v-model="userInfo.name"></el-input>
						</el-form-item>
						<el-form-item label="手机号" prop="phone">
							<el-input v-model="userInfo.phone"></el-input>
						</el-form-item>
						
						<el-form-item label="状态">
								  <el-radio-group v-model="userInfo.isDelete">
										<el-radio :label="0">正常</el-radio>
										<el-radio :label="1">禁用</el-radio>
									</el-radio-group>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
							<el-button @click="resetForm('ruleForm')">重置</el-button>
					</el-form-item>
					</el-form>
				</el-col>
			</el-row>
			
		</div>
	</div>		
</template>

<script type="text/javascript">
import { panelTitle } from 'components';
import {mapActions} from 'vuex'
import {SET_USER_INFO} from 'store/actions/type'

export default {
    data() {
        return {
            load_data: false,
            userInfo: {
							username:'',
							name:'',
							phone:'',
							isDelete:'0'
						},
            rules: {
                name: [
                  { required: true, message: '请输入姓名', trigger: 'blur' },
                  { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                phone: [
                   { required: true, message: '请输入手机号码', trigger: 'blur' },
                   { min: 11, max: 11, message: '长度为11位', trigger: 'blur' }
                ],
								isDelete:[
									{ required: true, message: '请选择状态', trigger: 'change' }
								]
            }
        };
    },
    created() {
        this.getUserInfo();
    },
    methods: {
				...mapActions({
					set_user_info: SET_USER_INFO
				}),
        getUserInfo() {
					this.load_data = true;
					this.$fetch.api.userinfo().then(({ data }) => {
									this.load_data = false;
									this.userInfo = data;
					})
					.catch(() => {
							this.load_data = false;
					});
        },
        submitForm() {
            this.$refs['userForm'].validate(valid => {
                if (!valid) {
                    return false;
                }
								this.load_data = true;
                this.$fetch.api.usermodify(this.userInfo).then(({data}) => {
										this.load_data = false;
										//存储新的用户信息到cookie
										this.set_user_info({
											user: data,
											login: true
										});
										this.getUserInfo();
										this.$root.Bus.$emit("name",true);
										this.$message({
											message: '操作成功',
											type: 'success'
										});
								}).catch(() => {
										this.load_data = false;
								});
            });
        },
        resetForm() {
            this.getUserInfo();
        }
    },
    components: {
        panelTitle
    }
};
</script>

<style>
</style>
