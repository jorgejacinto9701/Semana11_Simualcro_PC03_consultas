<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>

<title>Lista Cliente</title>
</head>
<body>
	<div class="container">
		<h1>Lista Cliente</h1>

		<div class="row" style="margin-top: 5%">
			<div class="col-md-4">
				<label class="control-label">Nombre</label> 
			</div>	
			<div class="col-md-5">
				<input class="form-control" type="text" name="nombre" id="id_nombre" >
			</div>			
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="col-md-4">
				<label class="control-label">DNI</label> 
			</div>	
			<div class="col-md-5">
				<input class="form-control" type="text" name="dni" id="id_dni" >
			</div>			
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="col-md-4">
				<label class="control-label" for="id_estado">Estado</label> 
			</div>	
			<div class="col-md-5">
				<input type="checkbox" class="custom-control-input" id="id_estado" checked="checked"  name="estado" value="1"/>
			</div>			
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="col-md-4">
				<label class="control-label" for="id_filtro">Categoría</label> 
			</div>	
			<div class="col-md-5">
				<select class="form-control" id="id_categoria" name="categoria">
					<option value="-1">[Seleccione]</option>
				</select>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-primary" id="id_btn_filtro">Filtro</button>
			</div>	
		</div>


		<div class="row" style="margin-top: 4%">
			<table id="id_table" class="table table-bordered table-hover table-condensed" >
				<thead style='background-color:#337ab7; color:white'>
					<tr>
						<th>Código</th>
						<th>Nombres</th>
						<th>DNI</th>
						<th>Fecha Registro</th>
						<th>Estado</th>
						<th>Categoría</th>
						<th></th>
					</tr>
				</thead>
				<tbody>


				</tbody>
			</table>

		</div>
		
		<!-- INICIO MODAL DE ACTUALIZA -->
		<div class="modal fade" id="id_div_modal_ver" >
			<div class="modal-dialog" style="width: 60%">
					<div class="modal-content">
					<div class="modal-header" >
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4><span class="glyphicon glyphicon-ok-sign"></span>Datos de Empleado</h4>
					</div>
					<div class="modal-body" >
						 <div class="panel-group" id="steps">
			                   <div class="panel panel-default">
			                   		<div id="stepOne" class="panel-collapse collapse in">
			                   			<form id="id_form_actualiza">
			                   			<input type="hidden" name="metodo" value="actualiza">
			                   			<input type="hidden" name="idEmpleado" id="idEmpleado" >
			                   			<div class="panel-body">
			                                <div class="form-group" >
		                                        <label class="col-lg-3 control-label" for="id_act_nombre">Nombre</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_act_nombre" type="text" readonly="readonly"/>
		                                        </div>
		                                    </div> 	
											<div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_dni">DNI</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_act_dni" type="text" readonly="readonly"/>
		                                        </div>
		                                    </div> 	 	
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_estado">Estado</label>
		                                        <div class="col-lg-8">
													<select class="form-control" id="id_act_estado" name="estado" disabled="disabled">
														<option value=" ">[Seleccione]</option>
														<option value="1">Activo</option>
														<option value="0">Inactivo</option>
													</select>
		                                        </div>
		                                    </div> 	 
			                                <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_categoria">Categoría</label>
		                                        <div class="col-lg-8">
													<select class="form-control" id="id_act_categoria" name="categoria" disabled="disabled">
														<option value=" ">[Seleccione]</option>
													</select>
		                                        </div>
		                                    </div> 	 
		                                    <div class="form-group"  >
		                                        <div class="col-lg-12" align="center">
		                                        	<button type="button" style="width: 80px" id="id_btn_salir" class="btn btn-primary btn-sm" data-dismiss="modal">Salir</button>
		                                        </div>
		                                    </div>   
		                                     
			                             </div>
			                             </form>
			                        </div>
			                   </div>
			              </div>
					</div>
				</div>
			</div>
		</div>	
		<!-- FIN MODAL DE ACTUALIZA -->
		
	</div>


	<script type="text/javascript">
		$("#id_btn_filtro").click(function() {
			var vnom = $("#id_nombre").val();
			var vcategoria = $("#id_categoria").val();
			var vdni = $("#id_dni").val();
			var vestado = $("#id_estado").is(":checked") ? "1": "0";
			
			console.log("Nombre : "  + vnom);
			console.log("DNI :"  + vdni);
			console.log("Categoría :"   + vcategoria);
			console.log("Estado :"  + vestado);
			
			$.getJSON("listaClienteComplejo", {"estado": vestado,"categoria":vcategoria,"nombre":vnom,"dni":vdni}, function(data) {
				agregarGrilla(data);
			});
		});
		
		function agregarGrilla(lista){
			 $('#id_table').DataTable().clear();
			 $('#id_table').DataTable().destroy();
			 $('#id_table').DataTable({
					data: lista,
					language: IDIOMA,
					searching: true,
					ordering: true,
					processing: true,
					pageLength: 10,
					lengthChange: false,
					info:true,
					scrollY: 305,
			        scroller: {
			            loadingIndicator: true
			        },
					columns:[
						{data: "idCliente",className:'text-center'},
						{data: "nombre",className:'text-center'},
						{data: "dni",className:'text-center'},
						{data: "fechaRegistro",className:'text-center'},
						{data: function(row, type, val, meta){
							return row.estado == 1 ? "Activo" : "Inactivo";  
						},className:'text-center'},
						{data: "categoria.nombre",className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-info btn-sm" onClick="verFormulario(\'' + row.nombre + '\',\'' +  row.dni + '\',\'' +  row.estado + '\',\'' +  row.categoria.idCategoria +'\');">Ver</button>';  
						},className:'text-center'},
					]                                     
			    });
		}
		
		$.getJSON("cargaCategoria",{}, function (data){
			$.each(data, function(index, item){
				$("#id_categoria").append("<option value='"+ item.idCategoria +"'>"+ item.nombre+"</option>");
				$("#id_act_categoria").append("<option value='"+ item.idCategoria +"'>"+ item.nombre+"</option>");
			})
		});
		
		function verFormulario(nombre,dni,estado,categoria){
			$("#id_act_nombre").val(nombre);
			$("#id_act_dni").val(dni);
			$("#id_act_estado").val(estado);   
			$("#id_act_categoria").val(categoria);
			$("#id_div_modal_ver").modal("show");
		}
		
	</script>

</body>
</html>




