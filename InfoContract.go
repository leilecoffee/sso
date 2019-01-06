package main

import (
	"encoding/json"
	"fmt"
	"strconv"

	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
)

type InfoContract struct {
}

func main() {
	err := shim.Start(new(InfoContract))
	if err != nil {
		fmt.Printf("Error creating new info Contract: %s", err)
	}
}

func (t *InfoContract) Init(stub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

func (t *InfoContract) Invoke(stub shim.ChaincodeStubInterface) sc.Response {
	function, args := stub.GetFunctionAndParameters()

	if function == "insertProduct" {
		return t.insertProduct(stub, args)
	} else if function == "updateProduct" {
		return t.updateProduct(stub, args)
	} else if function == "richQueryProduct" {
		return t.richQueryProduct(stub, args)
	} else if function == "queryProduct" {
		return t.queryProduct(stub, args)
	} else if function == "insertCompany" {
		return t.insertCompany(stub, args)
	} else if function == "updateCompany" {
		return t.updateCompany(stub, args)
	} else if function == "richQueryCompany" {
		return t.richQueryCompany(stub, args)
	} else if function == "queryCompany" {
		return t.queryCompany(stub, args)
	} else if function == "insertApply" {
		return t.insertApply(stub, args)
	} else if function == "updateApply" {
		return t.updateApply(stub, args)
	} else if function == "richQueryApply" {
		return t.richQueryApply(stub, args)
	} else if function == "queryApply" {
		return t.queryApply(stub, args)
	} else if function == "insertProductInfo" {
		return t.insertProductInfo(stub, args)
	} else if function == "updateProductInfo" {
		return t.updateProductInfo(stub, args)
	} else if function == "richQueryProductInfo" {
		return t.richQueryProductInfo(stub, args)
	} else if function == "queryProductInfo" {
		return t.queryProductInfo(stub, args)
	} else if function == "insertProductStock" {
		return t.insertProductStock(stub, args)
	} else if function == "updateProductStock" {
		return t.updateProductStock(stub, args)
	} else if function == "richQueryProductStock" {
		return t.richQueryProductStock(stub, args)
	} else if function == "queryProductStock" {
		return t.queryProductStock(stub, args)
	}
	return shim.Error("Invalid Info Contract function name.")
}

type Product struct {
	PrdId   string `json:"prdid"`
	Prdname string `json:"prdname"`
	Prdtype string `json:"prdtype"`
	PrdDesc string `json:"prddesc"`
	valid   int    `json:"valid"`
}

func (t *InfoContract) insertProduct(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 5 {
		return shim.Error("Incorrect number of arguments. Expecting 5")
	}

	args4, error := strconv.Atoi(args[4])
	if error != nil {
		fmt.Println("String conversion integer failed!")
		return shim.Error("Request params error!")
	}

	productAsBytes, _ := stub.GetState("Product" + args[0])
	if productAsBytes != nil {
		fmt.Println("Product has exists! id:%s" + args[0])
		return shim.Error("Product has exists!")
	}
	var newProduct = Product{PrdId: args[0], Prdname: args[1], Prdtype: args[2], PrdDesc: args[3], valid: args4}
	newProductAsBytes, _ := json.Marshal(newProduct)
	stub.PutState("Product"+args[0], newProductAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) updateProduct(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 5 {
		return shim.Error("Incorrect number of arguments. Expecting 6")
	}

	args4, error := strconv.Atoi(args[4])
	if error != nil {
		fmt.Println("String conversion integer failed!")
		return shim.Error("Request params error!")
	}

	var newProduct = Product{PrdId: args[0], Prdname: args[1], Prdtype: args[2], PrdDesc: args[3], valid: args4}
	newProductAsBytes, _ := json.Marshal(newProduct)
	stub.PutState("Product"+args[0], newProductAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) queryProduct(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	postAsBytes, _ := stub.GetState("Project" + args[0])
	return shim.Success(postAsBytes)
}
func (t *InfoContract) richQueryProduct(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	return shim.Success(nil)
}

type Company struct {
	ComId   string `json:"comid"`
	ComName string `json:"comname"`
}

func (t *InfoContract) insertCompany(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting 2")
	}

	companyAsBytes, _ := stub.GetState("Company" + args[0])
	if companyAsBytes != nil {
		fmt.Println("Company has exists! id:%s" + args[0])
		return shim.Error("Company has exists!")
	}
	var newCompany = Company{ComId: args[0], ComName: args[1]}
	newCompanyAsBytes, _ := json.Marshal(newCompany)
	stub.PutState("Company"+args[0], newCompanyAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) updateCompany(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting 2")
	}

	var newCompany = Company{ComId: args[0], ComName: args[1]}
	newCompanyAsBytes, _ := json.Marshal(newCompany)
	stub.PutState("Company"+args[0], newCompanyAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) richQueryCompany(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	return shim.Success(nil)
}

func (t *InfoContract) queryCompany(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	postAsBytes, _ := stub.GetState("Company" + args[0])
	return shim.Success(postAsBytes)
}

type Apply struct {
	Id             string `json:"id"`
	ComId          string `json:"comId"`
	ComName        string `json:"comname"`
	FromComId      string `json:"comId"`
	FromComName    string `json:"comname"`
	PermTable      string `json:"permtable"`
	State          int    `json:"state"`
	VisitBeginTime string `json:"visitbegintime"`
	VisitEndTime   string `json:"visitendtime"`
	CreateTime     string `json:"createTime"`
}

func (t *InfoContract) insertApply(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 10 {
		return shim.Error("Incorrect number of arguments. Expecting 10")
	}

	args6, error := strconv.Atoi(args[6])

	if error != nil {
		fmt.Println("String conversion integer failed!")
		return shim.Error("String conversion integer failed!")
	}
	applyAsBytes, _ := stub.GetState("Apply" + args[0])
	if applyAsBytes != nil {
		fmt.Println("Apply has exists! id:%s" + args[0])
		return shim.Error("Apply has exists!")
	}
	var newApply = Apply{Id: args[0], ComId: args[1], ComName: args[2], FromComId: args[3], FromComName: args[4], PermTable: args[5], State: args6, VisitBeginTime: args[7], VisitEndTime: args[8], CreateTime: args[9]}
	newApplyAsBytes, _ := json.Marshal(newApply)
	stub.PutState("Apply"+args[0], newApplyAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) updateApply(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 10 {
		return shim.Error("Incorrect number of arguments. Expecting 8")
	}

	args6, error := strconv.Atoi(args[6])

	if error != nil {
		fmt.Println("String conversion integer failed!")
		return shim.Error("String conversion integer failed!")
	}

	var newApply = Apply{Id: args[0], ComId: args[1], ComName: args[2], FromComId: args[3], FromComName: args[4], PermTable: args[5], State: args6, VisitBeginTime: args[7], VisitEndTime: args[8], CreateTime: args[9]}
	newApplyAsBytes, _ := json.Marshal(newApply)
	stub.PutState("Apply"+args[0], newApplyAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) richQueryApply(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	return shim.Success(nil)
}

func (t *InfoContract) queryApply(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	postAsBytes, _ := stub.GetState("Apply" + args[0])
	return shim.Success(postAsBytes)
}

type ProductInfo struct {
	Id          string `json:"id"`
	ComId       string `json:"comid"`
	ComName     string `json:"comname"`
	PrdId       string `json:"prdid"`
	PrdName     string `json:"prdname"`
	BegPrice    string `json:"begprice"`
	NewestPrice string `json:"newestprice"`
	YsdPrice    string `json:"ysdprice"`
	SumPrice    string `json:"sumprice"`
	Pricedate   string `json:"pricedate"`
	Rate        string `json:"rate"`
	BegRate     string `json:"begrate"`
	SumRate     string `json:"sumrate"`
	State       int    `json:"state"`
	CreateTime  string `json:"createTime"`
	Remark      string `json:"remark"`
}

func (t *InfoContract) insertProductInfo(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 16 {
		return shim.Error("Incorrect number of arguments. Expecting 16")
	}

	args13, error := strconv.Atoi(args[13])

	if error != nil {
		fmt.Println("String conversion integer failed!")
		return shim.Error("String conversion integer failed!")
	}
	productInfoAsBytes, _ := stub.GetState("ProductInfo" + args[0])
	if productInfoAsBytes != nil {
		fmt.Println("ProductInfo has exists! id:%s" + args[0])
		return shim.Error("ProductInfo has exists!")
	}

	var newProductInfo = ProductInfo{Id: args[0], ComId: args[1], ComName: args[2], PrdId: args[3], PrdName: args[4], BegPrice: args[5], NewestPrice: args[6], YsdPrice: args[7], SumPrice: args[8], Pricedate: args[9], Rate: args[10], BegRate: args[11], SumRate: args[12], State: args13, CreateTime: args[14], Remark: args[15]}
	newProductInfoAsBytes, _ := json.Marshal(newProductInfo)
	stub.PutState("ProductInfo"+args[0], newProductInfoAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) updateProductInfo(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 16 {
		return shim.Error("Incorrect number of arguments. Expecting 16")
	}

	args13, error := strconv.Atoi(args[13])

	if error != nil {
		fmt.Println("String conversion integer failed!")
		return shim.Error("String conversion integer failed!")
	}

	var newProductInfo = ProductInfo{Id: args[0], ComId: args[1], ComName: args[2], PrdId: args[3], PrdName: args[4], BegPrice: args[5], NewestPrice: args[6], YsdPrice: args[7], SumPrice: args[8], Pricedate: args[9], Rate: args[10], BegRate: args[11], SumRate: args[12], State: args13, CreateTime: args[14], Remark: args[15]}

	newProductInfoAsBytes, _ := json.Marshal(newProductInfo)
	stub.PutState("ProductInfo"+args[0], newProductInfoAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) richQueryProductInfo(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	return shim.Success(nil)
}

func (t *InfoContract) queryProductInfo(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	productInfoAsBytes, _ := stub.GetState("ProductInfo" + args[0])
	return shim.Success(productInfoAsBytes)
}

type ProductStock struct {
	Id          string `json:"id"`
	ItemId      string `json:"itemid"`
	ItemName    string `json:"itemname"`
	ComId       string `json:"comid"`
	ComName     string `json:"comname"`
	PrdId       string `json:"prdid"`
	PrdName     string `json:"prdname"`
	MarketId    string `json:"marketid"`
	TradeDate   string `json:"tradedate"`
	Currency    string `json:"currency"`
	Amount      string `json:"amount"`
	Cost        string `json:"cost"`
	ExRate      string `json:"exrate"`
	Prmcost     string `json:"prmcost"`
	Selfcost    string `json:"selfcost"`
	NewestPrice string `json:"newestprice"`
	Prmval      string `json:"prmval"`
	Selfval     string `json:"selfval"`
	Prmadd      string `json:"prmadd"`
	SelfAdd     string `json:"selfadd"`
	TradeStatus string `json:"tradestatus"`
	RightsDesc  string `json:"rightsdesc"`
	CreateTime  string `json:"createtime"`
	Remark      string `json:"remark"`
}

func (t *InfoContract) insertProductStock(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 24 {
		return shim.Error("Incorrect number of arguments. Expecting 24")
	}

	productStockAsBytes, _ := stub.GetState("ProductStock" + args[0])
	if productStockAsBytes != nil {
		fmt.Println("ProductStock has exists! id:%s" + args[0])
		return shim.Error("ProductStock has exists!")
	}

	var newProductStock = ProductStock{Id: args[0], ItemId: args[1], ItemName: args[2], ComId: args[3], ComName: args[4], PrdId: args[5], PrdName: args[6], MarketId: args[7], TradeDate: args[8], Currency: args[9], Amount: args[10], Cost: args[11], ExRate: args[12], Prmcost: args[13], Selfcost: args[14], NewestPrice: args[15], Prmval: args[16], Selfval: args[17], Prmadd: args[18], SelfAdd: args[19], TradeStatus: args[20], RightsDesc: args[21], CreateTime: args[22], Remark: args[23]}
	newProductStockAsBytes, _ := json.Marshal(newProductStock)
	stub.PutState("ProductStock"+args[0], newProductStockAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) updateProductStock(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 24 {
		return shim.Error("Incorrect number of arguments. Expecting 24")
	}

	var newProductStock = ProductStock{Id: args[0], ItemId: args[1], ItemName: args[2], ComId: args[3], ComName: args[4], PrdId: args[5], PrdName: args[6], MarketId: args[7], TradeDate: args[8], Currency: args[9], Amount: args[10], Cost: args[11], ExRate: args[12], Prmcost: args[13], Selfcost: args[14], NewestPrice: args[15], Prmval: args[16], Selfval: args[17], Prmadd: args[18], SelfAdd: args[19], TradeStatus: args[20], RightsDesc: args[21], CreateTime: args[22], Remark: args[23]}

	newProductStockAsBytes, _ := json.Marshal(newProductStock)
	stub.PutState("ProductStock"+args[0], newProductStockAsBytes)
	return shim.Success(nil)
}

func (t *InfoContract) richQueryProductStock(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	return shim.Success(nil)
}

func (t *InfoContract) queryProductStock(stub shim.ChaincodeStubInterface, args []string) sc.Response {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	productStockAsBytes, _ := stub.GetState("ProductStock" + args[0])
	return shim.Success(productStockAsBytes)
}
