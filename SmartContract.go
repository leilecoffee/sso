package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"strconv"
	"strings"

	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
)

type SmartContract struct {
}

type Post struct {
	Id              string `json:"id"`
	OriginalWebsite string `json:"originalwebsite"`
	OriginalID      string `json:"originalid"`
	Title           string `json:"title"`
	Content         string `json:"content"`
	AuthorId        string `json:"authorid"`
	PublishTime     string `json:"publishtime"`
	UpdateTime      string `json:"updatetime"`
	Category        string `json:"category"`
	SourceId        string `json:"sourceid"`
	Labels          string `json:"labels"`
	Follower_num    int    `json:"follower_num"`
	Browse_num      int    `json:"browse_num"`
	Star_num        int    `json:"star_num"`
}

type PostLength struct {
	Length int `json:"length"`
}

func (s *SmartContract) Init(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

func (s *SmartContract) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {

	function, args := APIstub.GetFunctionAndParameters()

	if function == "queryPost" {
		return s.queryPost(APIstub, args)
	} else if function == "initLedger" {
		return s.initLedger(APIstub)
	} else if function == "addPost" {
		return s.addPost(APIstub, args)
	} else if function == "updatePost" {
		return s.updatePost(APIstub, args)
	} else if function == "richQueryPosts" {
		return s.richQueryPosts(APIstub, args)
	} else if function == "getPostNum" {
		return s.getPostNum(APIstub, args)
	}
	return shim.Error("Invalid Smart Contract function name.")
}

func (s *SmartContract) queryPost(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	postAsBytes, _ := APIstub.GetState(args[0])
	return shim.Success(postAsBytes)
}

func (s *SmartContract) initLedger(APIstub shim.ChaincodeStubInterface) sc.Response {
	posts := []Post{
		Post{Id: "1", OriginalWebsite: "b", OriginalID: "c", Title: "d", Content: "e", AuthorId: "f", PublishTime: "g", UpdateTime: "h", Category: "i", SourceId: "j", Labels: "k", Follower_num: 100, Browse_num: 200, Star_num: 300},
		Post{Id: "2", OriginalWebsite: "bb", OriginalID: "bb", Title: "dd", Content: "ee", AuthorId: "ff", PublishTime: "gg", UpdateTime: "hh", Category: "ii", SourceId: "jj", Labels: "kk", Follower_num: 400, Browse_num: 500, Star_num: 600},
	}
	length := PostLength{Length: len(posts)}
	lengthAsBytes, _ := json.Marshal(length)
	APIstub.PutState("POSTLENGTH", lengthAsBytes)

	i := 0
	indexName := "POST~Title"
	for i < len(posts) {
		fmt.Println("i is ", i)
		postAsBytes, _ := json.Marshal(posts[i])
		APIstub.PutState("POST"+strconv.Itoa(i), postAsBytes)
		fmt.Println("Added", posts[i])
		i = i + 1

		TitleNameIndexKey, err := stub.CreateCompositeKey(indexName, []string{posts[i].Title, posts[i].Id})
		if err != nil {
			return shim.Error("Fail to create Composite key")
		}

		value := []byte{0x00}
		APIstub.PutState(TitleNameIndexKey, value)
	}

	return shim.Success(nil)
}

func (s *SmartContract) addPost(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 13 {
		return shim.Error("Incorrect number of arguments. Expecting 13")
	}
	args10, error := strconv.Atoi(args[10])
	args11, error := strconv.Atoi(args[11])
	args12, error := strconv.Atoi(args[12])

	if error != nil {
		fmt.Println("String conversion integer failed!")
	}
	lengthAsBytes, _ := APIstub.GetState("POSTLENGTH")
	length := PostLength{}
	json.Unmarshal(lengthAsBytes, &length)
	newlength := length.Length + 1
	var post = Post{Id: strconv.Itoa(newlength), OriginalWebsite: args[0], OriginalID: args[1], Title: args[2], Content: args[3], AuthorId: args[4], PublishTime: args[5], UpdateTime: args[6], Category: args[7], SourceId: args[8], Labels: args[9], Follower_num: args10, Browse_num: args11, Star_num: args12}
	postAsBytes, _ := json.Marshal(post)
	APIstub.PutState("POST"+strconv.Itoa(newlength), postAsBytes)
	length.Length = newlength
	lengthAsBytes, _ = json.Marshal(length)
	APIstub.PutState("POSTLENGTH", lengthAsBytes)

	indexName := "POST~Title"
	TitleNameIndexKey, err := stub.CreateCompositeKey(indexName, []string{posts[i].Title, posts[i].Id})
	if err != nil {
		return shim.Error("Fail to create Composite key")
	}

	value := []byte{0x00}
	APIstub.PutState(TitleNameIndexKey, value)

	return shim.Success(lengthAsBytes)
}

func (s *SmartContract) updatePost(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 14 {
		return shim.Error("Incorrect number of arguments. Expecting 14")
	}
	args11, error := strconv.Atoi(args[11])
	args12, error := strconv.Atoi(args[12])
	args13, error := strconv.Atoi(args[13])
	if error != nil {
		fmt.Println("String conversion integer failed!")
	}
	var post = Post{Id: args[0], OriginalWebsite: args[1], OriginalID: args[2], Title: args[3], Content: args[4], AuthorId: args[5], PublishTime: args[6], UpdateTime: args[7], Category: args[8], SourceId: args[9], Labels: args[10], Follower_num: args11, Browse_num: args12, Star_num: args13}
	postAsBytes, _ := json.Marshal(post)
	APIstub.PutState("POST"+args[0], postAsBytes)

	return shim.Success(nil)
}

func (s *SmartContract) richQueryPosts(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	INDEX := "POST~Title"
	coloredMarbleResultsIterator, err := stub.GetStateByPartialCompositeKey(INDEX, []string{})

	var queryString string

	if args[1] == "0" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":\"%s\"}}", args[0], args[2])
	} else if args[1] == "1" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$gt\":%s}}}", args[0], args[2])
	} else if args[1] == "2" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$gte\":%s}}}", args[0], args[2])
	} else if args[1] == "3" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$lt\":%s}}}", args[0], args[2])
	} else if args[1] == "4" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$lte\":%s}}}", args[0], args[2])
	} else if args[1] == "5" {
		between := strings.Split(args[2], ",")
		queryString = fmt.Sprintf("{\"selector\":{\"$and\":[{\"%s\":{\"$gte\":%s}},{\"%s\":{\"$lte\":%s}}]}}", args[0], between[0], args[0], between[1])
	} else {
		return shim.Error("Incorrect number of arguments. Expecting 0~5")
	}

	resultsIterator, err := APIstub.GetQueryResult(queryString)
	if err != nil {
		return shim.Error(err.Error())
	}
	defer resultsIterator.Close()

	var buffer bytes.Buffer
	buffer.WriteString("[")

	bArrayMemberAlreadyWritten := false
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return shim.Error(err.Error())
		}

		if bArrayMemberAlreadyWritten == true {
			buffer.WriteString(",")
		}
		buffer.WriteString("{\"Key\":")
		buffer.WriteString("\"")
		buffer.WriteString(queryResponse.Key)
		buffer.WriteString("\"")

		buffer.WriteString(", \"Record\":")

		buffer.WriteString(string(queryResponse.Value))
		buffer.WriteString("}")
		bArrayMemberAlreadyWritten = true
	}
	buffer.WriteString("]")

	fmt.Printf("- richQueryPosts:\n%s\n", buffer.String())

	return shim.Success(buffer.Bytes())
}

func (s *SmartContract) getPostNum(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 3 {
		return shim.Error("Incorrect number of arguments. Expecting 3")
	}
	var queryString string

	if args[1] == "0" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":\"%s\"}}", args[0], args[2])
	} else if args[1] == "1" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$gt\":%s}}}", args[0], args[2])
	} else if args[1] == "2" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$gte\":%s}}}", args[0], args[2])
	} else if args[1] == "3" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$lt\":%s}}}", args[0], args[2])
	} else if args[1] == "4" {
		queryString = fmt.Sprintf("{\"selector\":{\"%s\":{\"$lte\":%s}}}", args[0], args[2])
	} else if args[1] == "5" {
		between := strings.Split(args[2], ",")
		queryString = fmt.Sprintf("{\"selector\":{\"$and\":[{\"%s\":{\"$gte\":%s}},{\"%s\":{\"$lte\":%s}}]}}", args[0], between[0], args[0], between[1])
	} else {
		return shim.Error("Incorrect number of arguments. Expecting 0~5")
	}

	resultsIterator, err := APIstub.GetQueryResult(queryString)
	if err != nil {
		return shim.Error(err.Error())
	}
	defer resultsIterator.Close()

	i := 0

	for resultsIterator.HasNext() {
		resultsIterator.Next()

		i = i + 1

	}

	fmt.Printf("- getPostNum:\n%s\n", strconv.Itoa(i))

	return shim.Success([]byte(strconv.Itoa(i)))
}

func main() {
	err := shim.Start(new(SmartContract))
	if err != nil {
		fmt.Printf("Error creating new Smart Contract: %s", err)
	}
}
