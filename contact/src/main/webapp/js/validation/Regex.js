
var types = {
	username: {
        pattern: '^[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\.]+\\.[a-z]+$',
        maxLength: 50
    },
    password: {
        pattern: '^[a-zA-Z0-9]+$',
        maxLength: 30
    },
    name: {
        pattern: '^[a-zA-Z\\s]+$',
        maxLength: 30
    },
    lastName: {
        pattern: '^[a-zA-Z\\s]+$',
        maxLength: 30            
    },
    email: {
        pattern: '^[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\.]+\\.[a-z]+$',
        maxLength: 50
    },
    phone_number: {
        pattern: '^\\d[\-\\d]*\\d$',
        maxLength: 20
    },
    street: {//
        pattern: '^[\\dA-Za-z\\s\'\#\&\-\/\\\\]+$',
        maxLength: 50
    },
    city: {
        pattern: '^[a-zA-Z\\s\-\,\\.\'\s]+$',//doesn't work for search
        maxLength: 30
    },
    state: {
        pattern: '^[a-zA-Z\\s\-\,\\.\']+$',
        maxLength: 30
    },
    zip_code: {
        pattern: '^[\\dA-Z]{1}[\\dA-Z\-\\s]*[\\dA-Z]{1}$',
        maxLength: 20
    }
}

var Regex = {
		
    validate: function validate(input, type) {
        var validCharacter = new RegExp(types[type].pattern).test(input)
        var tooLong = input.length > types[type].maxLength

        if (validCharacter && !tooLong) {
            return {
                valid: true
            }
        }

        var result = {
            valid: false
        }

        if (!validCharacter) {
            result.invalidCharacter = true
            result.pattern = types[type].pattern
        }

        if (tooLong) {
            result.tooLong = true
            result.maxLength = types[type].maxLength
        }

        return result
    },   
    
    validateData: function validateData(parameters, viewManager) {
		
    	var validateResult = this.validate(parameters.input, parameters.type)
    	var exist = Regex.dataUnique(parameters.data, parameters.existingElements)
    	
    	if(parameters.type == 'address'){
    		viewManager.showErrors(parameters.id, exist, validateResult)
    	}else{    		
    		
    		var url = 'http://localhost:8181/validate'
    			
    		var dataMember = {
    				type : parameters.type,
    				value : parameters.input,    	
    				customerId : encodeURIComponent(parameters.customerId)
    		}

    		var data = 'dataMember=' + JSON.stringify(dataMember)

    		$.get(url, data, function(unique) {	
			    	
		    	if(!unique.isUnique){
		    		exist = true		    	
		    	}
		    	
		    	//if(!validateResult.valid || exist){
		    		viewManager.showErrors(
    		    			parameters.id, 
    		    			exist, 
    		    			validateResult)
		    	//}better use else to delete popover			    			    
			})    
    	}    	
	},

	dataUnique: function dataUnique(newElement, existingElements) {
	
	    var found = 0
	    for (var key in existingElements) {
	
	        var members = existingElements[key]
	        var differentMembers = 0
	        for (var member in members) {
	            if (members[member] != newElement[member]) {
	                differentMembers++
	                if (differentMembers > 1) {
	                    break
	                }
	            }
	        }
	        if (differentMembers <= 1) {
	            found++
	            if (found > 1) {
	                return true;
	            }
	        }
	    }
	    return false;
	},
	
	validateAccount: function validateAccount(domObject, uniqueEmail, callback){
		
		var id = $(domObject).attr('id')
		
		var dataKeyStart = id.search(/\d/)		
		var member = {
			value : $(domObject).val(),
			type : id.substring(0, dataKeyStart)
		}
		
		var regexResult = '';
		
		var dataKeyStart = id.search(/\d/)		
		var type = id.substring(0, dataKeyStart)
		
		var otherDataKey = '2' 
		if(id.substring(dataKeyStart, id.length) == 2)
			otherDataKey = '1'		
		
		var input1 = $('#' + type + 1).val()
		var input2 = $('#' + type + 2).val()
			
		var regexResult = Regex.validate(input1, type)
		
	    regexResult.equal = (input1 === input2)
	    regexResult.valid = regexResult.valid && regexResult.equal
				
		if(regexResult.valid){
			if(member.type == 'username'){
				
				var url = "http://localhost:8181/validate"	
				var data = 'dataMember=' + JSON.stringify(member)
				$.get(url, data, function(results) {	
										
					if(results.isUnique){						
						callback.prepare(id, regexResult)						
					}else{							
						callback.viewManager.showErrors('#' + id, true, regexResult)												
					}				
					callback.viewManager.showErrors(
							'#' + type + otherDataKey, !results.isUnique, regexResult)												
				})
			}else if(uniqueEmail == "true" || uniqueEmail == 'unknow'){
				callback.prepare(id, regexResult)
				callback.viewManager.showErrors(
						'#' + type + otherDataKey, false, regexResult)												
			}
		}else{
			callback.viewManager.showErrors('#' + id, false, regexResult)												
		}		
	} 	
}