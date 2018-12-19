<template>
	<div class="panel">
		<panel-title :title="$route.meta.title"></panel-title>
		<div class="panel-body"
				v-loading="load_data"
				element-loading-text="拼命加载中">
			<el-row>
				<el-col :span="8">
					<el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="100px">
						<el-form-item label="公司名称:" prop="companyName">
							<el-autocomplete
								  v-model="infoForm.companyName"
								  :fetch-suggestions="queryCompanyAsync"
									@select="handleSelect"
								  placeholder="请输入公司名称"
									clearable
								></el-autocomplete>
						</el-form-item>
						<el-form-item label="授权表:" prop="permTable">
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
						<el-form-item label="授权字段:" v-if="showPermColumn" >
								<el-checkbox-group 
										v-model="infoForm.checkItems">
										<el-checkbox  v-for="item in permColumnOptions" :label="item.code" :key="item.code">{{item.name}}</el-checkbox><br/>
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
						checkPermTable:{},
						route_id: this.$route.params.id,
            infoForm: {
                companyName: '',
								permTable:'',
								checkItems:[]
            },
            rules: {
                companyName: [{ required: true, message: '请输入公司名称', trigger: 'blur' }],
								permTable: [{ required: true, message: '请选择授权表', trigger: 'change' }],
								permColumn: [{ required: true, message: '请选择授权字段', trigger: 'change' }]
            }
        };
    },
    created() {
			this.loadInfoPermList();
			this.route_id && this.init_data();
    },
    methods: {
				init_data(){
					this.infoForm={
						"companyName":"深圳前海金道贵金属有限公司",
						"permTable":1,
					};
					//this.infoForm.checkItems=["price","price_date"];
					//this.showPermColumn = true;
				},
				//获取公司信息
        queryCompanyAsync(queryString, cb) {
					  //如果查询参数不存在 不执行查询
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
				//选择公司选项处理
				handleSelect(item) {
						this.infoForm.companyId = item.companyId;
				},
				//获取信息授权列表
				loadInfoPermList(){
					this.$fetch.api.infoPermList().then(({ data }) => {
							if (data) {
									this.permTableOptions = data;
							}
					}).catch(() => {
					});
				},
				//授权表选项变化调用
				permTableChange(val){
					let checkItem = {};
					for(let item of this.permTableOptions){
						 if(item.infoPermId  === val){
							 checkItem = item;
							 break;
						 }
					}
					//如果授权表中授权列不存在则不显示授权列信息
					if(!checkItem.permColumn || !checkItem.permDesc){
						this.permColumnOptions = []
						this.showPermColumn = false;
						return;
					}
					//存放当前选择的授权表记录
					this.checkPermTable = checkItem;
					let permColumnOptions = []
					//拆分列信息
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
				//提交表单
        submitForm() {
					this.$refs['infoForm'].validate((valid) => {
					   if (valid) {
								this.$confirm('此操作将提交该数据, 是否继续?', '提示', {
									confirmButtonText: '确定',
									cancelButtonText: '取消',
									type: 'warning'
								}).then(() => {
									let checkColumn = this.infoForm.checkItems;
									//授权表选项
									let checkItem = this.checkPermTable;
									let permColumn = checkItem.permColumn.split(",");
									let permDesc = checkItem.permDesc.split(",");
									let checkColumnLabel = [];
									//判断选择的授权字段的说明
									for(let i=0;i<permColumn.length;i++){
										if(checkColumn.indexOf(permColumn[i])>-1){
											checkColumnLabel.push(permDesc[i]);
										}
									}
									//组装请求参数
									let param = {
										"companyId":this.infoForm.companyId,
										"infoPermId":this.infoForm.permTable,
										"permColumn":this.infoForm.checkItems.join(","),
										"permColumnDesc":checkColumnLabel.join(",")
									}
									this.$fetch.api.userInfoPermSave(param).then(({ data }) => {
											this.$message.success("保存成功");
											//返回上级路由
											setTimeout(this.$router.back(), 1000)
									}).catch(() => {});
								}).catch(() => {})
							}
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
