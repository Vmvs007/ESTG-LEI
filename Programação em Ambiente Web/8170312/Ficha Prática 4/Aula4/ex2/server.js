/*var http = require('http');
var url = require('url');
var fs = require('fs');*/

var express = require('express');
var path = require('path');
var app= express();

app.use('/static',express.static('public'));
app.get('/',function(req,res){

	res.sendFile('C:/Users/Ricardo/Desktop/ESTG/2Ano/PAW/Aula4/ex2/main.html');
});

app.listen(8080)


/*http.createServer(function (req,res){

	var q = url.parse(req.url, true);
	var filename = "." + q.pathname;
	fs.readFile(filename, function(err,data){
		if(err){
			res.writeHead(404, {'Content-Type': 'text/html'});
			return res.end("404 Not Found");
		}
		res.writeHead(200, {'Content-Type': 'text/html'});

		res.write(data);

		var express = require('express');
		var app = express();

		app.use(express.static('C:/Users/Ricardo/Desktop/ESTG/2Ano/PAW/Aula4/ex2/public'));
		return res.end();
	});

}).listen(8080);*/