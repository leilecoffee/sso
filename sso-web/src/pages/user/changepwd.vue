<template>
	<div class="panel">
		<panel-title :title="$route.meta.title"></panel-title>
		<div class="panel-body"
				v-loading="load_data"
				element-loading-text="拼命加载中">
			<el-row>
				<el-col :span="8">
					<el-form ref="pwdForm" :model="pwdForm" :rules="rules" label-width="100px">
						<el-form-item label="原始密码" prop="oldpwd">
							<el-input v-model="pwdForm.oldpwd"></el-input>
						</el-form-item>
						<el-form-item label="新密码" prop="newpwd">
							<el-input v-model="pwdForm.newpwd"></el-input>
						</el-form-item>
						<el-form-item label="确认密码" prop="cfpwd">
							<el-input v-model="pwdForm.cfpwd"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submitForm()">提交</el-button>
					</el-form-item>
					</el-form>
				</el-col>
			</el-row>
			
		</div>
	</div>		
</template>

<script type="text/javascript">
import { panelTitle } from 'components';
export default {
    data() {
				var validatePass = (rule, value, callback) => {            
						if (value === '') {
							callback(new Error('请再次输入密码'));
						} else if (value !== this.pwdForm.newpwd) {
							callback(new Error('两次输入密码不一致!'));
						} else {
							callback();
						}
				};
        return {
            load_data: false,
            pwdForm: {
							oldpwd:'',
							newpwd:'',
							cfpwd:'',
						},
            rules: {
                oldpwd: [
                  { required: true, message: '请输入原始密码', trigger: 'blur' },
                  { min: 4, max: 14, message: '长度4~14个字符', trigger: 'blur' }
                ],
                newpwd: [
                   { required: true, message: '请输入新密码', trigger: 'blur' },
                   { min: 4, max: 14, message: '长度4~14个字符', trigger: 'blur' },
									 { pattern: /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/, message: '密码由数字字母和特殊符号组成' }
                ],
								cfpwd:[
									{ required: true, message: '请输入确认密码', trigger: 'blur'},
									{ min: 4, max: 14, message: '长度4~14个字符', trigger: 'blur' },
									{ pattern: /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/, message: '密码由数字字母和特殊符号组成' },
									{validator: validatePass, trigger: 'blur' }
								]
            }
        }
    },
    created() {},
    methods: {
        submitForm() {
            this.$refs['pwdForm'].validate(valid => {
                if (!valid) {
                    return false;
                }
								this.load_data = true;
								let param ={"oldPwd":this.pwdForm.oldpwd,"newPwd":this.pwdForm.newpwd};
                this.$fetch.api.changepwd(param).then(({data}) => {
										this.load_data = false;
										this.$message({
											message: '操作成功',
											type: 'success'
										});
								}).catch(() => {
										this.load_data = false;
								});
            });
        }
    },
    components: {
        panelTitle
    }
};
</script>

<style>
</style>
