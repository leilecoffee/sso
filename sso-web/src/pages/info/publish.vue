<template>
	<div class="panel">
		<panel-title :title="$route.meta.title"></panel-title>
		<div class="panel-body"
				v-loading="load_data"
				element-loading-text="拼命加载中">
			<el-row>
				<el-col :span="8">
					<el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="100px">
						<el-form-item label="公司代码" prop="companyCode" >
							<el-input v-model="infoForm.username" disabled></el-input>
						</el-form-item>
						<el-form-item label="公司名称" prop="companyName">
							<el-input v-model="infoForm.username" ></el-input>
						</el-form-item>
						<el-form-item label="产品代码" prop="productCode" >
							<el-input v-model="infoForm.productCode" disabled></el-input>
						</el-form-item>
						<el-form-item label="产品名称" prop="productName">
							<el-input v-model="infoForm.productName"></el-input>
						</el-form-item>
						<el-form-item label="价格" prop="productPrice">
							<template>
							<el-input-number v-model="infoForm.productPrice" :precision="2" :step="0.1" ></el-input-number>
							</template>
						</el-form-item>
						<el-form-item label="价格日期" prop="priceDate">
							<el-input v-model="infoForm.priceDate"></el-input>
						</el-form-item>
						<el-form-item label="产品类型" prop="productType">
							<el-select v-model="infoForm.productType" clearable  placeholder="请选择产品类型">
							  <el-option label="区域一" value="shanghai"></el-option>
							  <el-option label="区域二" value="beijing"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="产品描述" prop="productDesc">
							<el-input type="textarea" :rows="2" v-model="infoForm.productDesc"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submitForm()">提交</el-button>
							<el-button @click="resetForm()">重置</el-button>
					</el-form-item>
					</el-form>
				</el-col>
			</el-row>
			
		</div>
	</div>		
</template>

<script>
	import { panelTitle } from 'components';
	export default {
		data() {
			return {
				infoForm:{
					productPrice:'0',
					productType:''
				},
				rules:{
					companyCode: [
						 { required: true, message: '请输入公司代码', trigger: 'blur' }
					],
					companyName: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					],
					productCode: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					],
					productName: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					],
					productPrice: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					], 
					priceDate: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					],
					productType: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					],
					productDesc: [
						{ required: true, message: '请输入公司代码', trigger: 'blur' }
					]
				}
			};
		},
		created() {
			
		},
		methods: {
			submitForm(){
				
			},
			resetForm(){
				this.$refs['infoForm'].resetFields();
			}
		},
		components: {
				panelTitle
		}
	}
</script>

<style>

</style>
