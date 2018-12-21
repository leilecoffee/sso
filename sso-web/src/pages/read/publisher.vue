<template>
	<div class="panel">
		<panel-title :title="$route.meta.title">
			<el-button @click.stop="on_refresh" size="small">
				<i class="fa fa-refresh"></i>
			</el-button>
		</panel-title>
		<div class="panel-body">
				<el-form :model="searchForm" :inline="true">
					<el-form-item label="公司名称:">
						<el-input v-model="searchForm.companyName" placeholder="支持模糊查询"></el-input>
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
					label="操作"
					width="180">
					<template slot-scope="props">
						<el-button type="text" size="small"  @click="apply(props.row)">申请授权</el-button>
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
		</div>
	</div>
</template>

<script>
	import { panelTitle,bottomToolBar} from 'components';
	import {infoStateOptions} from 'common/config'
	import {tools_date} from 'common/tools'
	export default {
		data(){
			return {
				//查询表单
				searchForm:{},
				//信息状态选项
				infoStateOptions:infoStateOptions,
				tableData: null,
				//当前页码
				currentPage: 1,
				//数据总条目
				total: 0,
				//每页显示多少条数据
				length: 10,
				//请求时的loading效果
				load_data: true,
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
				this.query();
			},
			query(){
				this.load_data = true;
				let param = this.searchForm;
				param["pageNum"] = this.currentPage;
				param["pageSize"] = this.length;
				this.$fetch.api.companyPublisher(param).then(({data}) => {
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
			apply(row){
				this.$confirm('此操作将发起授权申请流程, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let param ={"companyId":row.companyId};
					this.$fetch.api.applySave(param).then(({data}) => {
							this.$message.success("申请成功");
							this.query();
					}).catch(() => {
							this.load_data = false;
					});
				}).catch(() => {})
			}
		}
	}
</script>

<style>

</style>
