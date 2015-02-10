
var CustomerDataManager = function CustomerDataManager(customer_id){
	this.customer_id = customer_id
}

CustomerDataManager.prototype
	.getCustomer = function getCustomer(){
			
	return {
			customer_id : encodeURIComponent(this.customer_id),
			customer_name : $('#customer_name').val(),
			last_name : $('#last_name').val()
	}			
}

CustomerDataManager.prototype
	.validData = function validData(member){
	
	var regexResult = Regex.validate(member.value, member.type)
	
	return {exist : false, regexResult : regexResult}
}

