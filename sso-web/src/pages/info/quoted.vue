<template>
	<div class="panel">
		<panel-title :title="$route.meta.title">
			<el-button @click.stop="on_refresh" size="small">
				<i class="fa fa-refresh"></i>
			</el-button>
			<router-link :to="{name: 'infoQuotedPublish'}" tag="span">
				<el-button type="primary" icon="plus" size="small">发布信息</el-button>
			</router-link>
		</panel-title>
		<div class="panel-body">
				<el-form :model="searchForm" :inline="true">
					<el-form-item label="信息状态:">
						<el-select v-model="searchForm.state" placeholder="请选择">
							<el-option
							  v-for="item in infoStateOptions"
							  :key="item.value"
							  :label="item.label"
							  :value="item.value">
							</el-option>
						 </el-select>
				  </el-form-item>
				  <el-form-item>
					<el-button type="primary" @click="quotedUserQuery">查询</el-button>
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
					label="公司名称"
					width="300">
				</el-table-column>
				<el-table-column
					prop="productName"
					label="产品名称">
				</el-table-column>
				<el-table-column
					prop="price"
					label="价格"
					width="120">
				</el-table-column>
				<el-table-column
					prop="priceDate"
					label="价格日期"
					width="120">
				</el-table-column>
				<el-table-column
					prop="createTime"
					label="创建日期"
					width="120">
				</el-table-column>
				<el-table-column
					label="操作"
					width="180">
					<template slot-scope="props">
						<router-link :to="{name: 'tableUpdate', params: {id: props.row.id}}" tag="span">
							<el-button type="info" size="small" icon="edit">详情</el-button>
						</router-link>
						<el-button type="danger" size="small" icon="delete" @click="deleteRow(props.row)">删除</el-button>
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
				load_data: false,
			}
		},
		components: {
			panelTitle,
			bottomToolBar
		},
		created(){
		},
		methods: {
			//刷新操作
			on_refresh(){
				quotedUserQuery();
			},
			quotedUserQuery(){
				this.load_data = true;
				let param ={"pageNum":this.currentPage,"pageSize":this.length,"state":this.searchForm.state};
				this.$fetch.api.quotedQuery(param).then(({data}) => {
						this.load_data = false;
						this.tableData = data.rows;
						this.total = data.total;
				}).catch(() => {
						this.load_data = false;
				});
			},
			
			handleCurrentChange(val){
				this.currentPage = val;
				this.quotedUserQuery();
			},
			handleSizeChange(val){
				this.currentPage =1;
				this.length = val;
				this.quotedUserQuery();
			},
			indexMethod(index){
				return index +1;
			},
			deleteRow(row){
				this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let param ={"id":row.id};
					this.$fetch.api.quotedDelete(param).then(({data}) => {
							this.$message.success("刪除成功");
							this.quotedUserQuery()
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
