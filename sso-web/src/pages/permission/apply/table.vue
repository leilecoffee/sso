<template>
	<div class="panel">
		<panel-title :title="$route.meta.title">
			<el-button @click.stop="on_refresh" size="small">
				<i class="fa fa-refresh"></i>
			</el-button>
		</panel-title>
		<div class="panel-body">
				<el-form :model="searchForm" :inline="true">
					<el-form-item label="申请状态:">
						<el-select v-model="searchForm.state" placeholder="请选择">
							<el-option
								v-for="item in applyStateOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="query">查询</el-button>
					</el-form-item>
				</el-form>	
			<el-table
				:data="tableData"
				v-loading="load_data"
				element-loading-text="拼命加载中"
				border
				style="width: 100%;">
				<el-table-column
					label="序号"
					type="index"
					:index="indexMethod"
					width="80">
				</el-table-column>
				<el-table-column
					prop="companyName"
					label="公司名称">
				</el-table-column>
				<el-table-column
					prop="companyCode"
					label="公司代码">
				</el-table-column>
				<el-table-column
					prop="applyPerson"
					label="申请人"
					width="150">
				</el-table-column>
				<el-table-column
					prop="applyPhone"
					label="联系电话"
					width="150">
				</el-table-column>
				<el-table-column
					prop="state"
					label="状态"
					width="150">
					<template slot-scope="props">
						<span v-text="props.row.state == 0 ? '审核中' : (props.row.state == 1 ?'已审核':'驳回')"></span>
					</template>
				</el-table-column>
				<el-table-column
					prop="reason"
					label="原因"
					width="150">
				</el-table-column>
				<el-table-column
					prop="createTime"
					label="创建时间"
					width="150">
				</el-table-column>
				<el-table-column
					label="操作"
					width="180">
					<template slot-scope="props" v-if="props.row.state == 0">
						<el-button type="text" size="small"  @click=" authorization(props.row)">授权</el-button>
						<el-button type="text" size="small"  @click="reject(props.row)">驳回</el-button>
					</template>
					
				</el-table-column>
			</el-table>
			<bottom-tool-bar>
				<div slot="page">
					<el-pagination
						@current-change="handleCurrentChange" 
						@size-change="handleSizeChange"
						:current-page="currentPage"
						:page-sizes="[10, 20, 50, 100]"
						layout="total, sizes, prev, pager, next"
						prev-text="上一页"
						next-text="下一页"
						:total="total">
					</el-pagination>
				</div>
			</bottom-tool-bar>
			<el-dialog
			  title="授权"
			  :visible.sync="dialogVisible"
			  width="30%"
			  @open="authDialogOpen()"
			  :before-close="authDialogClose">
			  <el-form label-width="80px" :model="authForm">
				  <el-form-item label="授权表:">
					<el-select v-model="authForm.permTable" clearable placeholder="请选择" @change="permTableChange" >
						<el-option
							v-for="item in permTableOptions"
							:key="item.infoPermId"
							:label="item.name"
							:value="item.infoPermId">
						</el-option>
					</el-select>
				  </el-form-item>
				  <el-form-item label="授权字段:"  v-if="showPermColumn" >
					<el-checkbox-group 
						v-model="authForm.permColumn">
							<el-checkbox  v-for="item in permColumnOptions" :label="item.code" :key="item.code">{{item.name}}</el-checkbox>
					</el-checkbox-group>
				  </el-form-item>
				</el-form>
			  <span slot="footer" class="dialog-footer">
				<el-button @click="authDialogClose()">取 消</el-button>
				<el-button type="primary" @click="authSubmit">确 定</el-button>
			  </span>
			</el-dialog>
		</div>
	</div>
</template>

<script>
	import { panelTitle,bottomToolBar} from 'components';
	import {applyStateOptions} from 'common/config'
	import {tools_date} from 'common/tools'
	export default {
		data(){
			return {
				//查询表单
				searchForm:{},
				//信息状态选项
				applyStateOptions:applyStateOptions,
				tableData: null,
				//当前页码
				currentPage: 1,
				//数据总条目
				total: 0,
				//每页显示多少条数据
				length: 10,
				//请求时的loading效果
				load_data: true,
				dialogVisible:false,
				authForm:{
					permColumn:[]
				},
				//操作的申请记录
				operationApplyId:'',
				//授权表集合
				permTableOptions:[],
				//授权字段集合
				permColumnOptions:[],
				//当前选中的授权表
				checkPermTable:{},
				//是否显示授权字段
				showPermColumn:false
			}
		},
		components: {
			panelTitle,
			bottomToolBar
		},
		created(){
			this.query();
		},
		methods: {
			//刷新操作
			on_refresh(){
				query();
			},
			query(){
				this.load_data = true;
				let param = this.searchForm;
				param["pageNum"] = this.currentPage;
				param["pageSize"] = this.length;
				this.$fetch.api.applyQuery(param).then(({data}) => {
						this.load_data = false;
						this.tableData = data.rows;
						this.total = data.total;
				}).catch(() => {
						this.load_data = false;
				});
			},
			
			handleCurrentChange(val){
				this.currentPage = val;
				this.query();
			},
			handleSizeChange(val){
				this.currentPage =1;
				this.length = val;
				this.query();
			},
			indexMethod(index){
				return index +1;
			},
			//授权按钮点击事件
			authorization(row){
				this.operationApplyId = row.applyId;
				this.dialogVisible = true;
			},
			//授权弹窗打开回调
			authDialogOpen(){
				this.$fetch.api.infoPermList().then(({ data }) => {
						if (data) {
								this.permTableOptions = data;
						}
				}).catch(() => {
				});
			},
			//授权弹窗 关闭事件
			authDialogClose(done){
				this.operationApplyId ='';
				this.authForm.permTable = '';
				this.permColumnOptions = [];
				this.permColumnOptions = [];
				this.showPermColumn = false;
				this.checkPermTable = {};
				this.dialogVisible = false;
				if(done) done();
			},
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
			authSubmit(){
				let checkColumn = this.authForm.permColumn;
				//授权表选项
				let checkPermTable = this.checkPermTable;
				let permColumn = checkPermTable.permColumn.split(",");
				let permDesc = checkPermTable.permDesc.split(",");
				let checkColumnLabel = [];
				//判断选择的授权字段的说明
				for(let i=0;i<permColumn.length;i++){
					if(checkColumn.indexOf(permColumn[i])>-1){
						checkColumnLabel.push(permDesc[i]);
					}
				}
				//组装请求参数
				let param = {
					"applyId":this.operationApplyId,
					"infoPermId":this.authForm.permTable,
					"permColumn":this.authForm.permColumn.join(","),
					"permColumnDesc":checkColumnLabel.join(",")
				}
				this.$fetch.api.applyAuthorization(param).then(({ data }) => {
						this.$message.success("授权成功");
						//关闭弹窗
						this.authDialogClose();
						//重新刷新授权申请列表
						this.query();
				}).catch(() => {});
			},
			reject(row){
				this.$prompt('请输入驳回原因', '提示', {
				  confirmButtonText: '确定',
				  cancelButtonText: '取消',
				  inputPattern:/\S/,
				  inputErrorMessage: '请输入驳回原因'
				}).then(({ value }) => {
					let param = {"applyId":row.applyId,"reason":value};
					this.$fetch.api.applyReject(param).then(({ data }) => {
							this.$message.success("操作成功");
							//重新刷新授权申请列表
							this.query();
					}).catch(() => {});
				}).catch(() => {})
			}
		}
	}
</script>

<style>

</style>
