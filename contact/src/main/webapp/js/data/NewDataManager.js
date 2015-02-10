
var NewDataManager = function NewDataManager(keys, dataType){
			
	this.nextKey = parseInt(keys.pop()) + 1
	
	DataManager.call(this, [], dataType)
}
NewDataManager.prototype = Object.create(DataManager.prototype)

NewDataManager.prototype.addNewDataKey = function(){

	this.dataKeys.push(this.nextKey)
	
	var newDataKey = this.nextKey
	this.nextKey++
	
	var dataMembers = this.getJsonDeepCopy(this.getDataMembers())
	dataMembers.shift()
	return {
		newDataKey : newDataKey,
		members : dataMembers
	}	
}

NewDataManager.prototype.clearNewDataKeys = function(keys){
	
	this.nextKey = parseInt(keys.pop()) + 1
	
	this.dataKeys = []
}

NewDataManager.prototype
	.getAllDataKeys = function getAllDataKeys(){

	var c = this.getDataKeys()
	return c
} 

