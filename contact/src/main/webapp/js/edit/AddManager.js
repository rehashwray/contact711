
var AddManager = function AddManager() {//improve constructor

	this.customerManager = null;
	this.emailsManager = null;
	this.phoneNumbersManager = null;
	this.addressesManager = null;

	this.viewManager = new ViewManager(['.add', '.changeButton'])
}

AddManager.prototype.initialize = function initialize(customerProfile){	
	
	this.customerManager = new CustomerDataManager(-1)
	this.emailsManager = new NewDataManager([0], 'emails')
	this.phoneNumbersManager = new NewDataManager([0], 'phone_numbers')
	this.addressesManager = new NewDataManager([0], 'addresses')
		
	this.viewManager.addChangeButtons(false)
	
	this.addData("#addEmail")
	this.addData("#addPhoneNumber")
	this.addData("#addAddress")
	
	var allKeys = this.emailsManager.getAllDataKeys() 
	this.viewManager.removeDelete('Email', allKeys.pop())
	
	allKeys = this.phoneNumbersManager.getAllDataKeys() 
	this.viewManager.removeDelete('PhoneNumber', allKeys.pop())
	
	allKeys = this.addressesManager.getAllDataKeys() 
	this.viewManager.removeDelete('Address', allKeys.pop())	
}

AddManager.prototype.tab = function tab(domElement){
	
	var baseIds = ['#name', '#email', '#phone', '#address']
	
	var id = '#' + $(domElement).attr('id')
	var baseId = id.substring(0, id.length - 10)

	baseIds.splice(baseIds.indexOf(baseId), 1)
	
	$(baseId + 'Data').show()
	$(baseId + 'Tab').parent().addClass("active")			
	
	for(var key in baseIds){
		$(baseIds[key] + 'Data').hide()
		$(baseIds[key] + 'Tab').parent().removeClass('active')
	}
}

AddManager.prototype.addData = function addData(domElement){

	var id = '#' + $(domElement).attr('id')			
	var type = id.substring(4, id.length)

	var dataTemplate = {}
	var allkeys = []
	if(type == 'Email'){
		dataTemplate = this.emailsManager.addNewDataKey()
		
		allKeys = this.emailsManager.getAllDataKeys() 
	}
	else if(type == 'PhoneNumber')
	{
		dataTemplate = this.phoneNumbersManager.addNewDataKey()
		
		allKeys = this.phoneNumbersManager.getAllDataKeys() 
	}
	else{
		dataTemplate = this.addressesManager.addNewDataKey()
		
		allKeys = this.addressesManager.getAllDataKeys() 
	}
		
	var data = {}
	for(var key in dataTemplate.members){
		data[dataTemplate.members[key]] = null
	}						
	
	this.viewManager.addForm(dataTemplate.newDataKey, data, type)
	
	if(allKeys.length == 2){		
		this.viewManager.addDelete(type, allKeys.shift())//might be better before adding key 
	}
}


AddManager.prototype.deleteElement = function deleteElement(domElement){
	
	var type = domElement.classList[1];
	
	var dataKey = this.viewManager.deleteForm(domElement)
	var allKeys = []				
	if(type == 'Email'){
		this.emailsManager.deleteDataKey(dataKey)
		
		allKeys = this.emailsManager.getAllDataKeys()
	}
	else if(type == 'PhoneNumber')
	{
		this.phoneNumbersManager.deleteDataKey(dataKey)	
		
		allKeys = this.phoneNumbersManager.getAllDataKeys()
	}
	else{
		this.addressesManager.deleteDataKey(dataKey)
		
		allKeys = this.addressesManager.getAllDataKeys()
	}
	
	if(allKeys.length == 1){
		this.viewManager.removeDelete(type, allKeys.pop())
	}
}

AddManager.prototype.inputValidate = function inputValidate(domElement){
	
	var id = $(domElement).attr('id')
			
	var dataKeyStart = id.search(/\d/)
	var key = id.substring(dataKeyStart, id.length)
	
	var	input = $(domElement).val()
	var type = domElement.classList[domElement.classList.length - 1]					
		
	var dataSet = {}
	if($.inArray(type, ['name', 'lastName']) > -1){
		var validateResult = Regex.validate(input, type)
		
		this.viewManager.showErrors('#' + id, false, validateResult)
		
		return 
	}
	else if(id.indexOf('email') > -1){
		dataSet = this.emailsManager.getDataSet(key)
	}				
	else if(id.indexOf('phone_number') > -1){
		dataSet = this.phoneNumbersManager.getDataSet(key)
	}		
	else{
		dataSet = this.addressesManager.getDataSet(key)
	}
	
	var parameters = {
			id : '#' + id, 
			input : input,
			type : type,
			data : dataSet.data,
			existingElements : dataSet.existingElements,
			customerId : this.customerManager.customer_id
	}
	
	Regex.validateData(parameters, this.viewManager)
}

AddManager.prototype
	.saveData = function saveData(){
		
	var customerProfileAdd = {}

	customerProfileAdd['customer'] = this.customerManager.getCustomer()
	customerProfileAdd['customerEmails'] = DataManager.prototype.getData.call(this.emailsManager)
	customerProfileAdd['customerPhoneNumbers'] = DataManager.prototype.getData.call(this.phoneNumbersManager)
	customerProfileAdd['customerAddresses'] = DataManager.prototype.getData.call(this.addressesManager)

	customerProfileAdd = JSON.stringify(customerProfileAdd)
	
	var url = '/AddCustomerProfile'
	var data = 'customerProfileAdd=' + customerProfileAdd
	
	var token = $("meta[name='_csrf']").attr("content")
    var header = $("meta[name='_csrf_header']").attr("content")
	
	$.ajax({
	    url: url,
	    type: 'POST',
	    data: data,
	    beforeSend: function (request)
        {
            request.setRequestHeader(header, token);
        },
	    success: function(response){
			window.location.href = "/";		
	    }
	}); 
}

