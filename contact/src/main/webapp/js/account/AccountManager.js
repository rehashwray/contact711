
var AccountManager = function AccountManager(path, userId){
	
	var invalidInputs = {
			'#username1' : true, 
			'#username2' : true, 
			'#password1' : true, 
			'#password2' : true			
	}
	this.viewManager = new ViewManager(['#send'], invalidInputs)
	this.viewManager.hideButtons()
	
	this.uniqueEmail = 'unknow'	
	this.path = path
	this.userId = userId
}  

AccountManager.prototype
	.validate = function validate(domObject) {
	
	var validateResult = Regex.validateAccount(domObject, this.uniqueEmail, this)
}

AccountManager.prototype
	.prepare = function prepare(id, regexResult){
	
	this.uniqueEmail = 'true'
	
	var user = {
		userId : this.userId,
		username : $('#username1').val(),
		userPassword : $('#password1').val()						
	}
	
	user = JSON.stringify(user)
	
	var url = 'http://localhost:8181/' + this.path + '?'
	var data = 'user=' + user
	
	$('#send').attr('href', url + data);
	
	this.viewManager.showErrors('#' + id, false, regexResult)												
}