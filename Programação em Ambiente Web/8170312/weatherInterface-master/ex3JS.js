       const openweathermaps_key = "99cd0d541b100040d6aec5c779ec265c"

       var lat = document.getElementById("paw-form-lat");
       var lon = document.getElementById("paw-form-lon");


       navigator.geolocation.getCurrentPosition(showPosition);
       

       function showPosition(position) {
    
        lat.placeholder = position.coords.latitude;
        lon.placeholder = position.coords.longitude;
      }


      function searchWeather(lat, long) {
        var lat = document.getElementById('paw-form-lat').value;
        var lon = document.getElementById('paw-form-lon').value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var weatherObject = JSON.parse(xhttp.response);
                var currentWeather = weatherObject["weather"][0]["description"];
                var imagem = document.getElementById("imgR");
                document.getElementById("paw-results-row").style.display = "block";
                document.getElementById("Results").innerHTML = currentWeather;

                if( currentWeather ==="few clouds"){
                    imagem.src="http://openweathermap.org/img/w/02d.png";

                }
                if ( currentWeather ==="clear sky"){
                     imagem.src="http://openweathermap.org/img/w/01d.png";
                }
            }
        }

        xhttp.open("GET", `http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&APPID=${openweathermaps_key}`, true);
        xhttp.setRequestHeader('Accept', 'application/json');
        xhttp.send();
    }