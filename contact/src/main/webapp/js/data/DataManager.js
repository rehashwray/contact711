
var DataManager = function DataManager(dataKeys, dataType){

	dataKeys.forEach(function(element, index, array){	    
	    array[index] = parseInt(element)	    
	})
	
	this.dataKeys = dataKeys
	
	this.dataMembers = []
	if(dataType == 'emails'){
		this.dataMembers = ['email_id', 'email']
	}
	else if(dataType == 'phone_numbers'){
		this.dataMembers = ['phone_number_id', 'phone_number']
	}
	else{
		this.dataMembers = ['address_id', 'street', 'city', 'state', 'zip_code']
	}
}

DataManager.prototype
	.deleteDataKey = function deleteDataKey(key){
	
	var index = this.dataKeys.indexOf(parseInt(key))
	
	this.dataKeys.splice(index, 1)	
}

DataManager.prototype
	.getJsonDeepCopy = function getJsonDeepCopy(json){
		
	return JSON.parse(JSON.stringify(json));
}

DataManager.prototype
	.getArrayDeepCopy = function getArrayDeepCopy(array){
		
	return $.extend(true, [], array, []);	
}

DataManager.prototype
	.findElement = function findElement(dataKey) {
	
	var existingElements = this.getData()	
	for (var key in existingElements) {
	
	    var element = existingElements[key]//not id members are undefined	    
	    if (element[this.dataMembers[0]] == dataKey) {	    
	    	return element	        
	    }	    
	}
	return false;
}

DataManager.prototype
	.getData = function getData(){
	
	var dataToAdd = {}
	for(var index in this.dataKeys){
		
		var key = this.dataKeys[index]
		
		var data = {};
		var dataMembersCopy = this.getJsonDeepCopy(this.dataMembers)
		data[dataMembersCopy.shift()] = key
		
		for(var member in dataMembersCopy){	
			data[dataMembersCopy[member]] 
				= $('#'+ dataMembersCopy[member] + key).val()												
		}
		dataToAdd[key] = data
	}
	
	return dataToAdd
}

DataManager.prototype
	.getDataKeys = function getDataKeys(){	
 
	var c = this.getArrayDeepCopy(this.dataKeys) 
	return c
} 

DataManager.prototype
	.getDataSet = function getDataSet(key){

	var data = this.findElement(key)
	var existingElements = this.getData()		
						
	return {data : data, existingElements : existingElements}
}

DataManager.prototype
	.getDataMembers = function getDataMembers(){
	
	return this.getJsonDeepCopy(this.dataMembers)
}

