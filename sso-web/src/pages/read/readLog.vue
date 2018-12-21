<template>
	<div class="panel">
		<panel-title :title="$route.meta.title">
			<el-button @click.stop="on_refresh" size="medium">
				<i class="fa fa-refresh"></i>
			</el-button>
		</panel-title>
		<div class="panel-body">
				<el-form class="search" :model="searchForm" :inline="true">
					<el-form-item label="公司名称:">
						<el-input v-model="searchForm.companyName" placeholder="支持模糊查询"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" size="medium"  @click="query">查询</el-button>
					</el-form-item>
				</el-form>
			<el-row class="table-function">
				<el-col>
					<el-button type="primary"  size="medium"  @click="exportFile()">导出</el-button>
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
					align="center"
					:index="indexMethod"
					width="80">
				</el-table-column>
				<el-table-column
					prop="publishCompanyName"
					align="center"
					label="发布公司名称">
				</el-table-column>
				<el-table-column
					prop="publisher"
					align="center"
					label="发布人">
				</el-table-column>
				<el-table-column
					prop="reader"
					align="center"
					label="阅读人">
				</el-table-column>
				<el-table-column
					prop="readTime"
					align="center"
					label="阅读时间">
				</el-table-column>
				<el-table-column
					prop="infoType"
					label="授权表"
					align="center"
					width="120">
				</el-table-column>
				<el-table-column
					prop="infoId"
					align="center"
					label="信息ID"
					width="120">
				</el-table-column>
				<!-- <el-table-column
					:v-show="false"
					label="操作"
					width="180">
					<template slot-scope="props">
						<el-button type="text" size="small"  @click="detail(props.row)">信息详情</el-button>
					</template>
				</el-table-column> -->
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
	export default {
		data(){
			return {
				//查询表单
				searchForm:{},
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
				this.load_data = false;
				let param = this.searchForm;
				param["pageNum"] = this.currentPage;
				param["pageSize"] = this.length;
				this.$fetch.api.readlogRead(param).then(({data}) => {
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
				
			}
		}
	}
</script>

<style>

</style>
