function formatString(string) {

	string = string.replace('_', ' ');
	string = string.split(' ')

	for ( var index in string) {
		string[index] = string[index].charAt(0).toUpperCase()
				+ string[index].slice(1);
	}

	return string.join(' ')
}

function jsonToArrayJson(json) {
	
	var array = []
	for ( var key in json) {
		array.push({key : key, value : json[key]})
	}
	
	return array
}

function arrayJsonToJson(array){
	
	var json = {}
	for(var index in array){
		var element = array[index]
		json[element.key] = element.value
	}
	
	return json
}