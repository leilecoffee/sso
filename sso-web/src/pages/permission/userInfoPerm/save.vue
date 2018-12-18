<template>
	<div class="panel">
		<panel-title :title="$route.meta.title"></panel-title>
		<div class="panel-body"
				v-loading="load_data"
				element-loading-text="拼命加载中">
			<el-row>
				<el-col :span="8">
					<el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="100px">
						<el-form-item label="公司名称" prop="companyName">
							<el-autocomplete
								  v-model="infoForm.companyId"
								  :fetch-suggestions="queryCompanyAsync"
								  placeholder="请输入内容"
									clearable
								></el-autocomplete>
						</el-form-item>
						<el-form-item label="授权表">
							<template>
							<el-select v-model="infoForm.permTable" clearable placeholder="请选择" @change="permTableChange" >
									<el-option
										v-for="item in permTableOptions"
										:key="item.infoPermId"
										:label="item.name"
										:value="item.infoPermId">
									</el-option>
							</el-select>
							</template>
						</el-form-item>
						<el-form-item label="授权字段" v-if="showPermColumn" >
								<el-checkbox-group 
										v-model="infoForm.checkItems"
										@change="handleCheckPermColumnChange">
										<el-checkbox v-for="item in permColumnOptions" :label="item.code" :key="item.code">{{item.name}}</el-checkbox><br/>
								</el-checkbox-group>
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

<script>
import { panelTitle } from 'components';
import {tools_date} from 'common/tools'
export default {
    data() {
        return {
            load_data: false,
            timeout: null,
						permTableOptions:[],
						showPermColumn:false,
						permColumnOptions:[],
            infoForm: {
                companyName: '',
								permTable:'',
								checkPermTable:{},
								checkItems:[]
            },
            rules: {
                companyName: [{ required: true, message: '请输入公司名称', trigger: 'blur' }],
            }
        };
    },
    created() {
			this.loadInfoPermList();
    },
    methods: {
				//获取公司信息
        queryCompanyAsync(queryString, cb) {
						if(!queryString){
							return;
						}
						this.$fetch.api.companyOptions({filter:queryString}).then(({ data }) => {
										if (data) {
												let result = [];
												for (let item of data) {
														result.push({
																value: item.companyName,
																companyId: item.companyId,
														});
												}
												cb(result);
										}
						}).catch(() => {
						});
        },
				loadInfoPermList(){
					this.$fetch.api.infoPermList().then(({ data }) => {
							if (data) {
									this.permTableOptions = data;
							}
					}).catch(() => {
					});
				},
				permTableChange(item){
					let infoPermId = this.infoForm.permTable
					let checkItem = {};
					for(let item of this.permTableOptions){
						 if(item.infoPermId  === infoPermId){
							 checkItem = item;
							 break;
						 }
					}
					if(!checkItem.permColumn || !checkItem.permDesc){
						this.permColumnOptions = []
						this.showPermColumn = false;
						return;
					}
					this.checkPermTable = checkItem;
					let permColumnOptions = []
					let permColumn = checkItem.permColumn.split(",");
					let permDesc = checkItem.permDesc.split(",");
					for(let i=0;i<permColumn.length;i++){
						permColumnOptions.push({
							"code":permColumn[i],
							"name":permDesc[i]
						})
					}
					this.permColumnOptions = permColumnOptions;
					this.showPermColumn = true;
				},
				handleCheckPermColumnChange(val){
					console.log(val);
				},
				//提交表单
        submitForm() {
					this.$confirm('此操作将提交该数据, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						debugger
						let checkColumnLabel = [];
						let checkColumn = this.infoForm.checkItems;
						let checkItem = this.checkPermTable;
						let permColumn = checkItem.permColumn.split(",");
						let permDesc = checkItem.permDesc.split(",");
						for(let i=0;i<permColumn.length;i++){
							let a = checkColumn.contains(permColumn[i]);
							if(a){
								checkColumnLabel.push(permDesc[i]);
							}
						}
						
						let param = {
							"companyId":this.infoForm.companyId,
							"infoPermId":this.infoForm.permTable,
							"permColumn":this.infoForm.checkItems.join(","),
							"permColumnDesc":checkColumnLabel.join(",")
						}
						console.log(param);
// 						this.$fetch.api.userInfoPermSave(param).then(({ data }) => {
// 								this.$message.success("保存成功");
// 								//返回上级路由
// 								setTimeout(this.$router.back(), 1000)
// 						}).catch(() => {
// 						});
					}).catch(() => {})
				}
    },
    components: {
        panelTitle
    }
};
</script>

<style>
</style>
