<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>


<title>Sistemas - Consulta</title>
</head>
<body>

<jsp:include page="intranetCabecera.jsp" />
<div class="container" style="margin-top: 4%">
<h4>Consulta Libro</h4>
 <div class="col-md-23" >  
		     	<div class="row">
						<div class="col-md-3">	
						  	<select id="id_tipolibro" name="TipoLibro" class='form-control'>
								<option value=" " >[ SELECCIONE TIPO DE LIBRO ]</option>
								<option value="-1" >[ TODOS ]</option>
							</select>
							
						</div>
					</div>			
					
					<div class="row" style="padding-top: 2%"> 
						<div class="col-md-12">
								<div class="content" >
						
									<table id="id_table" class="table table-striped table-bordered" >
										<thead>
											<tr>
												<th>ID</th>
												<th>CODIGO</th>
												<th>TITULO</th>
												<th>ESTADO</th>
												<th>TIPO</th>
												<th>TIPO DE LIBRO</th>
											</tr>
										</thead>
										<tbody>
												   
												
										</tbody>
										</table>	
									
								</div>	
						</div>
					</div>
		  </div>
</div>
<script type="text/javascript">
	$.getJSON("CargaComboLibro",{"metodo":"TipoLibro"},function(data){
		console.log(data);
		$.each(data, function(i, item){
			$("#id_tipolibro").append("<option value='" + item.idTipoLibro + "'>"+ item.nombre +"</option>");
		} );
	});
	
	$("#id_tipolibro").change(function(){
		var var_lib = $("#id_tipolibro").val();
		console.log(var_lib);	
		
		$("#id_table tbody").empty();
		
		$.getJSON("ConsultaLibro",{"TipoLibro":var_lib},function (data){
			$.each(data, function(i, item){
				
				$("#id_table").append("<tr><td>"+ item.idLibro+"</td><td>"+ item.codigo+"</td><td>"+ item.titulo+"</td><td>"+ item.estado+"</td><td>"+ 
						item.tipo+"</td><td>"+ item.TipoLibro.nombre+"</td></tr>");
				
			});
		});
	});
</script>

		
</body>
</html>



