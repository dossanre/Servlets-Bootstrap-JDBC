function myFunction() {
    alert("Hello! I am an alert box!");
}

function myIncrease() {
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('number').value = value;
}

function myDecrease() {
    var value = parseInt(document.getElementById('number').value, 10);
    if(value>1){
    	value = isNaN(value) ? 0 : value;
    	value--;
    	document.getElementById('number').value = value;
    }
}