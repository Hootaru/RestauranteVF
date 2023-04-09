var fecha = new Date();
var anio = fecha.getFullYear();
var dia = fecha.getDate();
var _mes = fecha.getMonth(); //viene con valores de 0 al 11
_mes = _mes + 1; //ahora lo tienes de 1 al 12
if (_mes < 10) //ahora le agregas un 0 para el formato date
{
    var mes = "0" + _mes;
} else {
    var mes = _mes.toString;
}

var fecha_minimo = anio + '-' + mes + '-' + dia; // Nueva variable
document.getElementById("fechaReserva").setAttribute('min', fecha_minimo);  
fechaReserva.min = new Date().toISOString().split("T")[0];