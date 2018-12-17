<template>
	<div class="panel">
		<panel-title :title="$route.meta.title">
			<el-button @click.stop="on_refresh" size="small">
				<i class="fa fa-refresh"></i>
			</el-button>
			<router-link :to="{name: 'tableAdd'}" tag="span">
				<el-button type="primary" icon="plus" size="small">添加数据</el-button>
			</router-link>
		</panel-title>
		<div class="panel-body">
			<el-table
				:data="tableData"
				v-loading="load_data"
				element-loading-text="拼命加载中"
				border
				@selection-change="on_batch_select"
				style="width: 100%;">
				<el-table-column
					type="selection"
					width="55">
				</el-table-column>
				<el-table-column
					prop="id"
					label="id"
					width="80">
				</el-table-column>
				<el-table-column
					prop="name"
					label="姓名"
					width="120">
				</el-table-column>
				<el-table-column
					prop="sex"
					label="性别"
					width="100">
					<template scope="props">
						<span v-text="props.row.sex == 1 ? '男' : '女'"></span>
					</template>
				</el-table-column>
				<el-table-column
					prop="age"
					label="年龄"
					width="100">
				</el-table-column>
				<el-table-column
					prop="birthday"
					label="生日"
					width="120">
				</el-table-column>
				<el-table-column
					prop="zip"
					label="邮编"
					width="120">
				</el-table-column>
				<el-table-column
					prop="address"
					label="地址">
				</el-table-column>
				<el-table-column
					label="操作"
					width="180">
					<template scope="props">
						<router-link :to="{name: 'tableUpdate', params: {id: props.row.id}}" tag="span">
							<el-button type="info" size="small" icon="edit">详情</el-button>
						</router-link>
						<el-button type="danger" size="small" icon="delete" @click="delete_data(props.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<bottom-tool-bar>
				<el-button
					type="danger"
					icon="delete"
					size="small"
					:disabled="batchSelect.length === 0"
					@click="on_batch_del"
					slot="handler">
					<span>批量删除</span>
				</el-button>
				<div slot="page">
					<el-pagination
						@current-change="handleCurrentChange"
						:current-page="currentPage"
						:page-size="10"
						layout="total, prev, pager, next"
						:total="total">
					</el-pagination>
				</div>
			</bottom-tool-bar>
		</div>
	</div>
</template>

<script>
	export default {
		data(){
			return {
				tableData: null,
				//当前页码
				currentPage: 1,
				//数据总条目
				total: 0,
				//每页显示多少条数据
				length: 15,
				//请求时的loading效果
				load_data: true,
				//批量选择数组
				batchSelect: []
			}
		},
		components: {
			panelTitle,
			bottomToolBar
		},
		created(){
		},
		methods: {
		}
	}
</script>

<style>

</style>
