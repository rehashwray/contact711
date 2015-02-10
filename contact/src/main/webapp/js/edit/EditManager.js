var EditManager = function EditManager() {

	AddManager.call(this)
}
AddManager.prototype = Object.create(AddManager.prototype)

EditManager.prototype.initialize = function initialize(customerProfile) {

	this.populateName(customerProfile.customer)
	this.populate(customerProfile.customerEmails, 'Email')
	this.populate(customerProfile.customerPhoneNumbers, 'PhoneNumber')
	this.populate(customerProfile.customerAddresses, 'Address')

	this.customerManager = new CustomerDataManager(
			customerProfile.customer.customer_id)
	this.emailsManager = new UpdateDataManager(
			customerProfile.customerEmails, 'emails')
	this.phoneNumbersManager = new UpdateDataManager(
			customerProfile.customerPhoneNumbers, 'phone_numbers')
	this.addressesManager = new UpdateDataManager(
			customerProfile.customerAddresses, 'addresses')
	
	var allKeys = this.emailsManager.getAllDataKeys() 
	if(allKeys.length == 1){
		this.viewManager.removeDelete('Email', allKeys.pop())
	}	
	allKeys = this.phoneNumbersManager.getAllDataKeys() 
	if(allKeys.length == 1){
		this.viewManager.removeDelete('PhoneNumber', allKeys.pop())
	}
	allKeys = this.addressesManager.getAllDataKeys() 
	if(allKeys.length == 1){
		this.viewManager.removeDelete('Address', allKeys.pop())
	}
}

EditManager.prototype.populateName = function populateName(dataElement) {

	$('#customer_name').val(dataElement.customer_name)
	$('#last_name').val(dataElement.last_name)
}

EditManager.prototype.populate = function populate(dataElements, type) {

	for ( var index in dataElements) {

		var dataElement = dataElements[index]

		var data = jsonToArrayJson(dataElement)
		data.shift()
		var memberKey = data.shift().value
		data = arrayJsonToJson(data)

		this.viewManager.addForm(memberKey, data, type, true)
	}
}

EditManager.prototype.saveData = function saveData() {

	var customerProfileAdd = {}

	customerProfileAdd['customerEmails'] = this.emailsManager.getDataToAdd()
	customerProfileAdd['customerPhoneNumbers'] = this.phoneNumbersManager
		.getDataToAdd()
	customerProfileAdd['customerAddresses'] = this.addressesManager.getDataToAdd()
	
	customerProfileAdd = JSON.stringify(customerProfileAdd)

	var customerProfileUpdate = {}

	customerProfileUpdate['customer'] = this.customerManager.getCustomer()
	customerProfileUpdate['customerEmails'] = this.emailsManager.getData()
	customerProfileUpdate['customerPhoneNumbers'] = this.phoneNumbersManager.getData()
	customerProfileUpdate['customerAddresses'] = this.addressesManager.getData()

	customerProfileUpdate = JSON.stringify(customerProfileUpdate)

	var customerProfileDelete = {}

	customerProfileDelete['customerEmails'] = this.emailsManager
			.getDataKeysToDelete()
	customerProfileDelete['customerPhoneNumbers'] = this.phoneNumbersManager
			.getDataKeysToDelete()
	customerProfileDelete['customerAddresses'] = this.addressesManager
			.getDataKeysToDelete()

	customerProfileDelete = JSON.stringify(customerProfileDelete)

	var url = '/updateCustomerProfile'
	var data = 'customerProfileAdd=' + customerProfileAdd
			+ '&customerProfileUpdate=' + customerProfileUpdate
			+ '&customerProfileDelete=' + customerProfileDelete
	
	var token = $("meta[name='_csrf']").attr("content")
    var header = $("meta[name='_csrf_header']").attr("content")

   	var outerClass = this
	$.ajax({
	    url: url,
	    type: 'POST',
	    data: data,
	    beforeSend: function (request)
        {
            request.setRequestHeader(header, token);
        },
	    success: function(ids){
			outerClass.updateData(ids)
	    }
	}); 
	
	/*$.get(url, data, function(ids) {
		outerClass.updateData(ids)
	});*/
}

EditManager.prototype.updateData = function updateData(ids) {

	function arrayCopy(array){
		var newArray = []
		for(var key in array){
			newArray.push(array[key])
		}
		
		return newArray
	}
	
	var components = {
			customerEmailsId : ['email'],
			customerPhoneNumbersId : ['phone_number'],
			customerAddressesId : ['street', 'city', 'state', 'zip_code']		
	} 
		
	for(var idGroup in ids){
			
		var idGroupKeys = ids[idGroup]
		var newIds = {}
		for(var keyPair in idGroupKeys){			
			
			newIds[idGroupKeys[keyPair]] = true	
		}
		
		var newIdsKeys = Object.keys(newIds) 
		if(newIdsKeys.length > 0){
			this.viewManager.updateFormKey(ids[idGroup], components[idGroup])
					
			if(idGroup == 'customerEmailsId'){
				this.emailsManager.clearDataToAdd(arrayCopy(newIdsKeys))
				this.emailsManager.addUpdateDataKey(arrayCopy(newIdsKeys))
			}
			else if(idGroup == 'customerPhoneNumbersId'){
				this.phoneNumbersManager.clearDataToAdd(arrayCopy(newIdsKeys))
				this.phoneNumbersManager.addUpdateDataKey(arrayCopy(newIdsKeys))
			}
			else{
				this.addressesManager.clearDataToAdd(arrayCopy(newIdsKeys))
				this.addressesManager.addUpdateDataKey(arrayCopy(newIdsKeys))
			}
		}
	}
}

EditManager.prototype.deleteData = function deleteData() {

	var customer = this.customerManager.getCustomer()
	
	var url = '/deleteCustomerProfile'
	var data = 'customer=' + JSON.stringify(customer)
	
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
	
	/*$.get(url, data, function(result) {
		window.location.href = "http://localhost:8181/Search";
	});	*/
}