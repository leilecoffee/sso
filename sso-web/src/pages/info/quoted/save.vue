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
							<el-input v-model="infoForm.companyCode" disabled></el-input>
						</el-form-item>
						<el-form-item label="公司名称" prop="companyName">
							<el-input v-model="infoForm.companyName" disabled></el-input>
						</el-form-item>
						<el-form-item label="产品名称" prop="productName">
							<el-autocomplete
								  v-model="infoForm.productName"
								  :fetch-suggestions="queryProductAsync"
								  placeholder="请输入内容"
								  @select="handleSelect"
									clearable
								></el-autocomplete>
						</el-form-item>
						<el-form-item label="产品代码" prop="productCode" >
							<el-input v-model="infoForm.productCode" disabled></el-input>
						</el-form-item>
						<el-form-item label="产品类型" prop="productType">
							<el-input v-model="infoForm.productType" disabled></el-input>
						</el-form-item>
						<el-form-item label="价格" prop="productPrice">
							<template>
							<el-input-number v-model="infoForm.productPrice" controls-position="right" :precision="2"  :min="0" :step="0.1" ></el-input-number>
							</template>
						</el-form-item>
						<el-form-item label="价格日期" prop="priceDate">
							<el-date-picker
								v-model="infoForm.priceDate"
								type="date"
								placeholder="选择日期">
							</el-date-picker>
						</el-form-item>
						<el-form-item label="产品描述" prop="productDesc">
							<el-input type="textarea" :rows="6" v-model="infoForm.productDesc" disabled></el-input>
						</el-form-item>
						<el-form-item label="授权访问时间" >
								<el-date-picker
								v-model="infoForm.visitDate"
								type="daterange"
								range-separator="至"
								start-placeholder="开始日期"
								end-placeholder="结束日期">
							</el-date-picker>
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
            infoForm: {
                companyId: '',
                companyCode: '',
                companyName: '',
                productCode: '',
                productName: '',
                productPrice: '0',
                priceDate: '',
                productType: '',
								visitDate:''
            },
            rules: {
                companyCode: [{ required: true, message: '请输入公司代码', trigger: 'blur' }],
                companyName: [{ required: true, message: '请输入公司名称', trigger: 'blur' }],
                productCode: [{ required: true, message: '请输入产品代码', trigger: 'blur' }],
                productName: [{ required: true, message: '请输入产品名称', trigger: 'blur' }],
                productPrice: [{ required: true, message: '请输入价格', trigger: 'blur' }],
                priceDate: [{ required: true,type:'date', message: '请输入价格日期', trigger: 'blur' },],
                productType: [{ required: true, message: '请输入产品类型', trigger: 'blur' }],
                productDesc: [{ required: true, message: '请输入产品描述', trigger: 'blur' }]
            }
        };
    },
    created() {
        this.companyUserGet();
				this.infoForm.priceDate  = tools_date.formatDateToString(new Date(),"YYYY-MM-DD");
    },
    methods: {
        //获取登录用户的公司信息
        companyUserGet() {
            this.$fetch.api
                .companyUserGet()
                .then(({ data }) => {
                    if (data) {
                        this.infoForm.companyId = data.companyId;
                        this.infoForm.companyCode = data.code;
                        this.infoForm.companyName = data.name;
                    }
                })
                .catch(() => {
                });
        },
				//获取产品信息
        queryProductAsync(queryString, cb) {
						this.$fetch.api.productList({filter:queryString}).then(({ data }) => {
										if (data) {
												let result = [];
												for (let item of data) {
														result.push({
																value: item.name,
																productCode: item.code,
																productDesc: item.description,
																productId: item.productId,
																productTypeId: item.productTypeId
														});
												}
												cb(result);
										}
						}).catch(() => {
						});
        },
				//选择产品列表处理
        handleSelect(item) {
						this.infoForm.productName = item.value;
            this.infoForm.productId = item.productId;
            this.infoForm.productCode = item.productCode;
						this.infoForm.productDesc = item.productDesc;
						this.loadProductType(item.productTypeId)
        },
        //获取产品类型
        loadProductType(productTypeId) {
            this.$fetch.api.productTypeGet({"productTypeId":productTypeId}).then(({ data }) => {
								if (data) {
										this.infoForm.productType = data.name;
								}
						}).catch(() => {
						});
        },
				//提交表单
        submitForm() {
					
					this.$confirm('此操作将提交该数据, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						let param = {
							"productId":this.infoForm.productId,
							"price":this.infoForm.productPrice,
							"priceDate":this.infoForm.priceDate
						}
						if(this.infoForm.visitDate){
								param["visitStartTime"] = this.infoForm.visitDate[0]
								param["visitEndTime"] = this.infoForm.visitDate[1]
						}
						this.$fetch.api.quotedSave(param).then(({ data }) => {
								this.$message.success("保存成功");
								//返回上级路由
								setTimeout(this.$router.back(), 1000)
						}).catch(() => {
						});
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
