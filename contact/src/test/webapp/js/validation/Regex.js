QUnit.test('Name', function(assert) {

	var input = {}
	input['mar mar ad'] = true
	input['0po'] = false
	input['l2'] = false
	input['m3p'] = false
	input[new Array(10).join('bola')] = false

	var type = 'name'

	for ( var key in input)
		assert.ok(Regex.validate(key, type).valid == input[key], key)		
})

QUnit.test('Email', function(assert) {

	var input = {}
	input['m3p@m.c'] = true
	input['mar'] = false
	input['0po@'] = false
	input['l2@mail'] = false
	input[new Array(20).join('bola') + '@mail.com'] = false

	var type = 'email'

	for ( var key in input)
		assert.ok(Regex.validate(key, type).valid == input[key], key)
})

QUnit.test('Phone Number', function(assert) {

	var input = {}
	input['8-5'] = true
	input['3'] = false
	input['2-'] = false
	input['-3'] = false
	input[new Array(30).join('9')] = false

	var type = 'phone_number'

	for ( var key in input)
		assert.ok(Regex.validate(key, type).valid == input[key], key)
})

QUnit.test('Street', function(assert) {

	var input = {}
	input["ma's raoa"] = true
	input['s\'s'] = true
	input['2-'] = true
	input['-3/'] = true
	input['3\\5'] = true
	input[new Array(52).join('9')] = false

	var type = 'street'

	for ( var key in input)
		assert.ok(Regex.validate(key, type).valid == input[key], key)
})

QUnit.test('City', function(assert) {

	var input = {}
	input['ma juan'] = true
	input['s\'s'] = true
	input['s,'] = true
	input['2-'] = false
	input['-3/'] = false
	input['3\\5'] = false
	input[new Array(52).join('d')] = false

	var type = 'city'

	for ( var key in input)
		assert.ok(Regex.validate(key, type).valid == input[key], key)
})

QUnit.test('Zip', function(assert) {

	var input = {}
	input['0-3'] = true
	input['A-3'] = true
	input['003'] = true
	input['3A3'] = true
	input['2-'] = false
	input['-3'] = false
	input['3\\5'] = false
	input[new Array(52).join('d')] = false

	var type = 'zip_code'

	for ( var key in input)
		assert.ok(Regex.validate(key, type).valid == input[key], key)
})
