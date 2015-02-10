
var UpdateDataManager = function UpdateDataManager(data, dataType) {
	
	DataManager.call(this, Object.keys(data), dataType)

	this.dataKeysToDelete = {}
	
	this.newDataManager = new NewDataManager(Object.keys(data), dataType)
}
UpdateDataManager.prototype = Object.create(DataManager.prototype)

UpdateDataManager.prototype
	.addNewDataKey = function addNewDatakey(){
	
	return this.newDataManager.addNewDataKey(this.newDataManager)
}

UpdateDataManager.prototype
	.getDataToAdd = function getDataToAdd(){

	return this.newDataManager.getData()
}

UpdateDataManager.prototype
	.clearDataToAdd = function clearDataToAdd(keys){

	this.newDataManager.clearNewDataKeys(keys)
}

UpdateDataManager.prototype
	.deleteData = function deleteData(dataKey){

	DataManager.prototype.deleteDataKey.call(this, dataKey)
		
	this.dataKeysToDelete[dataKey] = null
}

UpdateDataManager.prototype
	.deleteDataKey = function deleteDataKey(dataKey){
	
	if(DataManager.prototype.findElement.call(this, dataKey))
		this.deleteData(dataKey)
	
	else if(this.newDataManager.findElement(dataKey)) 
		this.newDataManager.deleteDataKey()
}

UpdateDataManager.prototype
	.getDataKeysToDelete = function getDataKeysToDelete(){

	var dataKeysToDeleteCopy = this.getJsonDeepCopy(this.dataKeysToDelete)
	this.dataKeysToDelete = {}

	return dataKeysToDeleteCopy
}

UpdateDataManager.prototype
	.addUpdateDataKey = function addUpdateDataKey(keys){

	for(var key in keys){			
		
		this.dataKeys.push(keys[key])	
	}
}

UpdateDataManager.prototype
	.findElement = function findElement(dataKey) {

	var element = DataManager.prototype.findElement.call(this, dataKey)
	if(element)
		return element
	
	return this.newDataManager.findElement(dataKey)	
}


DataManager.prototype
	.getDataSet = function getDataSet(key){

	var data = this.findElement(key)
	var existingElements = $.extend(true, {}, 
			DataManager.prototype.getData.call(this),
			this.newDataManager.getData())		
						
	return {data : data, existingElements : existingElements}
}

UpdateDataManager.prototype
	.getAllDataKeys = function getAllDataKeys(){
	
	return this.getDataKeys().concat(this.newDataManager.getAllDataKeys())
} 