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
			<el-row style="text-align: right;margin-right: 10px;">
				<el-col>
					<el-button type="primary"  size="small"  @click="exportFile()">导出</el-button>
				</el-col>
			</el-row>
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
					label="公司编码">
				</el-table-column>
				<el-table-column
					prop="productName"
					label="产品名称">
				</el-table-column>
				<el-table-column
					prop="productName"
					label="产品编码">
				</el-table-column>
				<el-table-column
					prop="createTime"
					label="创建日期">
				</el-table-column>
				<el-table-column
					label="操作"
					width="180">
					<template slot-scope="props">
						<el-button type="text" size="small"  @click="detail(props.row)">详情</el-button>
					</template>
				</el-table-column>
			</el-table>
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
				//请求时的loading效果
				load_data: false,
			}
		},
		components: {
			panelTitle
		},
		created(){
			
		},
		methods: {
			//刷新操作
			on_refresh(){
				this.query();
			},
			query(){
				this.load_data = false;
				let param = this.searchForm;
				param.infoTypes = ['1'];
				this.$fetch.api.infoQuery(param).then(({data}) => {
						this.load_data = false;
						this.tableData = data[0].data;
				}).catch(() => {
						this.load_data = false;
				});
			},
			indexMethod(index){
				return index +1;
			},
			detail(row){
				let param ={"infoId":row.id,"infoType":1};
				this.$fetch.api.infoDetail(param).then(({data}) => {
						this.$message.success("操作成功");
						this.query();
				}).catch(() => {
						this.load_data = false;
				});
			},
			exportFile(){
				let param = this.searchForm;
				param.infoTypes = ['1'];
				this.$fetch.api.infoExport(param).then((res) => { // 处理返回的文件流
				}).catch(() => {
						this.load_data = false;
				});
			}
		}
	}
</script>

<style>

</style>
