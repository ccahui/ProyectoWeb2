
//FILA 1 : Elemento <tr> que almacenan Notas
//FILA 2 : Elemento <input> que copian las Notas de la FILA 1
//ACTUALIZACION : Elemntos <tr> que copian el Contenido de la FILA 2 

//INSERTO LA FILA 2
function filaInsertar(td) {
	var fila = $(td).parent().parent();
	var numFil = $(fila).parent().children().index($(fila));
	/*Para encontrar la ubicacion donde Trabajar la clase rpta*/
	return $(fila).siblings()[numFil];
}
//OBTENGO LA FILA 1
function filaAnterior(rpta) {
	var numFil = $(rpta).parent().children().index($(rpta));
	var fila = $(rpta).parent().children();
	var datos = $(fila[numFil - 1]).children();
	var array = new Array(3);
	for (var i = 2; i <= 4; i++)
		array[i - 2] = $(datos[i]);
	return array;
}
//OBTENGO LAS NOTAS DE LA FILA 1
function filaDatos(td) {

	var array = new Array(3);
	var datos = $(td).parent().siblings();
	for (var i = 2; i <= 4; i++)
		array[i - 2] = $(datos[i]).text();

	return array;
}
//OBTENGO LOS DATOS DE LA FILA2
function inputFila(rpta) {

	var tr = $(rpta).children();
	var array = new Array(3);
	for (var i = 2; i <= 4; i++)
		array[i - 2] = $(tr[i]).children()[0];

	return array;
}
function validarEntrada(array){
	for (var i = 0; i < array.length; i++) {
		if(isNaN(array[i].value))
			return false;
		else if(array[i].value !== "" && array[i].value < 0 || array[i].value > 20)
			return false;
	}
	return true;
}


$(document).ready(function () {

	//MUESTRA LA FILA 2
	$('.insertar').click(function () {

		var rpta = filaInsertar(this);
		var dato = filaDatos(this);

		var input = inputFila(rpta);
		for (var i = 0; i < dato.length; i++) {
			input[i].value = dato[i];
		}
		$(rpta).show();
	});

	//OCULTO LA FILA 2
	$(".cancelar").click(function () {
		var rpta = filaInsertar(this);
		$(rpta).hide();
	});



	$(".actualizar").click(function () {

		var rpta = $(this).parent().parent();
		var array = filaAnterior(rpta);
		var input = inputFila(rpta);

		var tr=$(this).parent().parent().prev().children();
		var codigoNota = $(tr[0]).text();


		//var nota1 = input[0].value;
		//var nota2 = input[1].value;
		//var nota3 = input[2].value;
		var codigoNota = codigoNota;

		//Array Con las 3 Notas
		if(validarEntrada(input)){
			
			$.ajax({
				method:'post',
				url:'/profesor/misCursos/nota',
				data:{
					nota1:input[0].value,nota2:input[1].value,nota3:input[2].value,id:codigoNota
				},
				//Son los datos del servidor 
				/*beforeSend(Function)*/
				success:function(data){
					/*Los datos son correctoz y se a insetado en la BD*/

					if(data === "1"){
						console.log(data);
						for (var i = 0; i < array.length; i++) {
							$(array[i]).text(input[i].value);
						}
					}
					else {
						window.alert("Se produjo un Error al Actulizar el Registro");
						console.log(data);
					}
					
					$(rpta).hide();
				},
				error:function(data){
					/*ERRROR 404 o Tiempo de Espera Muy Largo no logro la conexion con el Servidor*/
					console.log(data);
				}
			});

			
		}
		else {
			window.alert("DATOS NO VALIDOS")
		}
		//window.alert("DATOS A ENVIAR POR AJAX: \n CODIGO: "+codigoNota+"\n Nota1 "+nota1+"\n Nota2 "+nota2+"\n Nota3 "+nota3);

		


	});
});

