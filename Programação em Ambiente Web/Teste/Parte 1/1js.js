 function AJAX_Request() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            addContentotoPage(this.response);
        }
    };
    xhttp.open("GET", "http://localhost:3000/", true);
    xhttp.send();
}

function addContentotoPage(content){

    var x = JSON.parse(content);
    var t = document.getElementById("teste");
    t.innerHTML += " <div class='col-md-2'>" + x.id+ "</div><div class='col-md-5'>" + x.nome +"</div><div class='col-md-5'>" + x.tempo +"</div>";
}

function add(){
    var x=document.getElementById("paw-input1").value;
    var y=document.getElementById("paw-input2").value;

document.getElementById("result").innerHTML = "Resultado: " +Math.pow(x,y);
    //document.getElementById("result").innerHTML = "Resultado: " +x+ " "+ y;
}

function centrar(){

    var textbox = document.getElementsByTagName('h1');
    for (var i=0; i<(textbox.length);i++) {
        textbox[i].style.textAlign='center';
        //textbox[i].style.display='none';
    }
}