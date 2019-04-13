const express = require('express');
const path=require('path');
var fs=require('fs');
var formidable=require('formidable');

const app=express();
    
app.use('/static', express.static('public'))

app.get('/home', function (req,res){
    res.sendFile(path.join(__dirname, '/index.html'))
});

app.post('/form',function(req,res){

	var form = new formidable.IncomingForm();
	
	form.parse(req,function(err,fields,files){
		var data = "nome:"+ fields.pnome+ ",idade:" +fields.idade;
		var file = __dirname + "/" + fields.pnome +".txt"

		fs.writeFile(file,data, function (err){
			if(err){
				console.log(err);
			}else{
				res.end("sucesso " +fields.pnome + "!");
			}
		});

	});
});
app.use(function(req, res, next) {
 return res.status(404).send('404 Not Found!');
});
app.listen(3000);