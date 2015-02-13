
var ViewManager = function ViewManager(bottomsToHide, invalidInputs) {
	
	this.buttonsToHide = bottomsToHide
	
	this.invalidInputs = invalidInputs || {}
}

ViewManager.prototype
	.addChangeButtons = function addChangeButtons(addDelete){
	
	var dataTabs = ['#nameData', '#emailData', '#phoneData', '#addressData']
	
	var buttons = 	
		'<div class="form-group">'
			+'<div class="col-sm-offset-2 col-sm-10">'
				+'<button type="submit" class="btn btn-default changeButton" id="changeButtonCustomer">Save</button>'
				
	if(addDelete){
		buttons += '<button type="button" class="btn btn-default deleteButton">Delete</button>'					
	}	
				
	buttons += 	'<a href="/" type="button" class="btn btn-default">Cancel</a>'
			+'</div>'
		+'</div>'
		
	for(var dataTab in dataTabs){
		$(dataTabs[dataTab]).append(buttons)
	}
} 

ViewManager.prototype
	.addForm = function addForm(newDataKey, components, type, populate){	
		
	$('#addDiv' + formatString(type)).before(
		'<div class="col-sm-12" id="delete'+ formatString(type) + newDataKey +'">'
			+'<span class="badge '+ type +' deleteElement">delete</span>'
		+'</div>'		
	)		
	for(var componentKey in components){
		
		var textFill = 'value="' + components[componentKey] + '">' 
		if(!components[componentKey]){
			textFill = 'placeholder="'+formatString(componentKey) + '">'  
		}
		$('#addDiv' + formatString(type)).before(
			'<div class="form-group '+ type.toLowerCase() +'Div'+ newDataKey +'">'
				+'<label for="'+ componentKey + newDataKey +'" class="col-sm-2 control-label">'
					+ formatString(componentKey) 
				+'</label>'
				+'<div class="col-sm-10">'
					+'<input type="text" class="form-control '+ componentKey 
						+'" id="'+ componentKey + newDataKey +'" ' + textFill
				+'</div>'
			+'</div>'						
		)
		if(populate == undefined){
			this.invalidInputs['#' + componentKey + newDataKey] = true
		}
	}
	if(populate == undefined){
		this.hideButtons()
	}
}

ViewManager.prototype
	.addDelete = function addDelete(type, dataKey){

	var dataType = type
	if(type == 'PhoneNumber')
		dataType = 'phone'
	
	$('#'+ dataType.toLowerCase() + 'Data').prepend(
		'<div class="col-sm-12" id="delete'+ formatString(type) + dataKey +'">'
			+'<span class="badge '+ type +' deleteElement">delete</span>'
		+'</div>'				
	)	
}

ViewManager.prototype
	.updateFormKey = function updateFormKey(group, components){	
		
	for(var oldId in group){			
					
		for(var component in components){
			
			var inputId = '#' + components[component] + oldId
			var newInputId = components[component] + group[oldId]

			$(inputId).attr('id', newInputId)				
		}
	}	
}

ViewManager.prototype
	.deleteForm = function deleteForm(id){
	
	var idParent = '#' + $(id).parent().attr('id')
	var classNextToParent = '.' + 
		$(id).parent().next().attr('class').split(" ")[1]
	
	var idInputs = []
	$(classNextToParent).each(function(index, div){

		idInputs.push('#' + $(div).find('input').attr('id'))
	})	
	
	$(idParent).remove()
	$(classNextToParent).remove()
	
	this.deleteInvalidInput(idInputs)
	
	var dataKeyStart = idInputs[0].search(/\d/)//first input
	
	return idInputs[0].substring(dataKeyStart, idInputs[0].length)
}

ViewManager.prototype
	.removeDelete = function removeDelete(type, key){

	var id = '#delete' + type + key
	
	$(id).remove()	
}

ViewManager.prototype
	.showErrors = function showErrors(id, exist, regexResult){
	
	var content = ''
		
	if(exist){
		content += 'Already Exist'
	}	
	if(regexResult.invalidCharacter){
		if(content.length > 0)
			content += '<br\>'
		
		content += 'Invalid Characters' 
	}
	if(regexResult.tooLong){
		if(content.length > 0)
			content += '<br\>'
		
		content += 'Too Long: ' 
			+ regexResult.maxLength
	}
	if(regexResult.equal === false){
		if(content.length > 0)
			content += '<br\>'
		
		content += 'Inputs Not Equals'		
	}
	if(content.length > 0){
		$(id).popover({
			//toggle : 'popover,
			trigger : 'hover',
			html : true,
			title : 'Invalid',
			content : content,
			placement : 'bottom'					
		})
		
		this.invalidInputs[id] = true 
		
		$(id).popover('show')
		
		$(id).parent().parent().addClass('has-error')
		
		this.hideButtons()
	}
	else{
		$(id).parent().parent().removeClass('has-error')
		
		this.deleteInvalidInput([id])
	}
}
	
ViewManager.prototype
	.deleteInvalidInput = function deleteInvalidInput(idInputs){

	for (var idIndex in idInputs) {
		
		var id = idInputs[idIndex]
		
		delete this.invalidInputs[id]

		$(id).popover('destroy')
	}
	
	if (Object.keys(this.invalidInputs) == 0) {
		
		for(var key in this.buttonsToHide)
			$(this.buttonsToHide[key]).show()
	}
}

ViewManager.prototype
	.hideButtons = function hideButtons(){
	
	for(var key in this.buttonsToHide)
		$(this.buttonsToHide[key]).hide()
}
