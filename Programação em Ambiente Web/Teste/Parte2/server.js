const http = require('http');
const fs = require('fs');
const url = require('url');
var formidable=require('formidable');

const hostname = '127.0.0.1';
const port = 3000;

const server = http.createServer((req, res) => {

  //TODO
  var e="/home"
  var q=url.parse(req.url,true);
  var q1=q.pathname;
  if(req.url=='/home'){
    fs.readFile('index.html', function(err, data) {
      res.writeHead(200, {'Content-Type': 'text/html'});
      res.write(data);
      res.end();

    });
  }else if(req.url=='/form'){

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
  }

  else{
    res.writeHead(404,{'Content-Type':'text/html'});
    return res.end("404 Not Found!");
};
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});