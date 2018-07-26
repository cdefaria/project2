window.onload = function () {
	loadPartial();
}

// Logging In
function loadPartial() {
	// console.log('in loadLogin()');
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function () {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$('#content').html(xhr.responseText);
		}
	}
	
	xhr.open('GET','partial.view',true);
	xhr.send();
}