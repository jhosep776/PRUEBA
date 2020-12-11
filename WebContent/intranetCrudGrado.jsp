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


<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>

<jsp:include page="intranetCabecera.jsp" />
<div class="container" style="margin-top: 4%">
<h4>Mantenimiento de Grado</h4>

		<div class="col-md-23" >  
			  <form id="idFormElimina" action="crudGrado">
			  		<input type="hidden" name="metodo" value="ELIMINA">
			  		<input type="hidden" id="id_elimina" name="id" >
			  </form>	
				
		       <form accept-charset="UTF-8"  action="crudGrado" class="simple_form" id="defaultForm2"  method="post">
		       		<input type="hidden" name="metodo" value="LISTA">
					<div class="row" style="height: 70px">
						<div class="col-md-2" >
								<input class="form-control" id="id_nonbre_filtro"  name="filtro" placeholder="Ingrese el nombre" type="text" maxlength="30"/>
						</div>
						<div class="col-md-2" >
							<button type="submit" class="btn btn-primary" style="width: 150px">FILTRA</button>
						</div>
						<div class="col-md-2">
							<button type="button" data-toggle='modal' onclick="registrar();"  class='btn btn-success' id="validateBtnw2" style="width: 150px">REGISTRA</button>
						</div>
						<div class="col-md-4">
							<c:if test="${sessionScope.MENSAJE != null }">
									<div class="alert alert-success" id="success-alert">
							 		   <button type="button" class="close" data-dismiss="alert">x</button>
										${sessionScope.MENSAJE}				
									</div>
							</c:if>
							<c:remove var="MENSAJE"/>
						</div>
					</div>
					
					<div class="row" > 
						<div class="col-md-12" >
								<div class="content" >
						
									<table id="tableGrado" class="table table-striped table-bordered" >
										<thead>
											<tr>
												<th>ID</th>
												<th>Nombre</th>
												<th>Apellido</th>
												<th>Actualiza </th>
												<th>Elimina </th>
											</tr>
										</thead>
										<tbody>
												    
												<c:forEach items="${sessionScope.GRADOS}" var="x">
													<tr>
														<td>${x.idGrado}</td>
														<td>${x.nombre}</td>
														<td>${x.estado}</td>
														<td>
															<button type='button' data-toggle='modal' onclick="editar('${x.idGrado}','${x.nombre}','${x.estado}');" class='btn btn-success' style='background-color:hsla(233, 100%, 100%, 0);'>
																<img src='images/edit.gif' width='auto' height='auto' />
															</button>
														</td>
														<td>
															<button type='button' data-toggle='modal' onclick="eliminar('${x.idGrado}');" class='btn btn-success' style='background-color:hsla(233, 100%, 100%, 0);'>
																<img src='images/delete.gif' width='auto' height='auto' />
															</button>
														</td>
													</tr>
												</c:forEach>
										</tbody>
										</table>	
									
								</div>	
						</div>
					</div>
		 		</form>
		  </div>
  
  
  	 <div class="modal fade" id="idModalRegistra" >
			<div class="modal-dialog" style="width: 60%">
		
				<!-- Modal content-->
				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Grado</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						<form id="id_form_registra" accept-charset="UTF-8" action="crudGrado" class="form-horizontal"     method="post">
							<input type="hidden" name="metodo" value="INSERTA">
		                    <div class="panel-group" id="steps">
		                        <!-- Step 1 -->
		                        <div class="panel panel-default">
		                            <div class="panel-heading">
		                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos del Grado</a></h4>
		                            </div>
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_nombre">Nombre</label>
		                                        <div class="col-lg-5">
													<input class="form-control" id="id_reg_nombre" name="nombre" placeholder="Ingrese el Nombre" type="text" maxlength="20"/>
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_estado">Estado</label>
		                                        <div class="col-lg-5">
		                                        	<select class="form-control" name="estado" id="id_reg_estado">
		                                        		<option value=" ">[Seleccione]</option>
		                                        		<option value="ACTIVO">ACTIVO</option>
		                                        		<option value="INACTIVO">INACTIVO</option>
		                                        	</select>
												</div>
		                                    </div>
		                                    
		                                    <div class="form-group">
		                                        <div class="col-lg-9 col-lg-offset-3">
		                                        	<button type="submit" class="btn btn-primary" id="idBtnRegistra">REGISTRA</button>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                        
		                    </div>
		                </form>   
				
				</div>
			</div>
		</div>
			
		</div>
  
		 <div class="modal fade" id="idModalActualiza" >
			<div class="modal-dialog" style="width: 60%">
		
				<!-- Modal content-->
				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Actualiza Grado</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						<form id="id_form_actualiza" accept-charset="UTF-8"  action="crudGrado" class="form-horizontal"     method="post">
						<input type="hidden" name="metodo" value="ACTUALIZA">
		                    <div class="panel-group" id="steps">
		                        <!-- Step 1 -->
		                        <div class="panel panel-default">
		                            <div class="panel-heading">
		                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos del Grado</a></h4>
		                            </div>
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_ID">ID</label>
		                                        <div class="col-lg-5">
		                                           <input class="form-control" id="id_ID" readonly="readonly" name="idGrado" type="text" maxlength="8"/>
		                                        </div>
		                                     </div>
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_nombre">Nombre</label>
		                                        <div class="col-lg-5">
													<input class="form-control" id="id_act_nombre" name="nombre" placeholder="Ingrese el Nombre" type="text" maxlength="20"/>
		                                        </div>
		                                    </div>
		                                    
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_estado">Estado</label>
		                                        <div class="col-lg-5">
													<select class="form-control" name="estado" id="id_act_estado">
		                                        		<option value=" ">[Seleccione]</option>
		                                        		<option value="ACTIVO">ACTIVO</option>
		                                        		<option value="INACTIVO">INACTIVO</option>
		                                        	</select>
		                                        </div>
		                                    </div>
		                                
  
		                                    <div class="form-group">
		                                        <div class="col-lg-9 col-lg-offset-3">
		                                        	<button type="submit" class="btn btn-primary" id="idBtnActualiza">ACTUALIZA</button>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                        
		                    </div>
		                </form>   
				
				</div>
			</div>
		</div>
			
		</div>
		
		
				 <div class="modal fade" id="idModalElimina" >
					<div class="modal-dialog" style="width: 60%">
				 	
				 	<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="padding: 35px 50px">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4><span class="glyphicon glyphicon-ok-sign"></span> Eliminar Grado</h4>
						</div>
				
						  <div class="modal-footer">
						    <button type="button" id="idBtnElimina" name="modalDe" class="btn btn-primary">ELIMINAR</button>
						    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
						  </div>
					</div>
				</div>
				</div>

</div>

<script type="text/javascript">
$("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
</script>

<script type="text/javascript">
$("#idBtnElimina").click(function(){
	$("#idFormElimina").submit();
});

function eliminar(id){
	$('#idModalElimina').modal('show');
	$('#id_elimina').val(id);	
}

function registrar(){	
	$('#idModalRegistra').modal("show");
}

function editar(id,nombre,estado){	
	$('#id_ID').val(id);
	$('#id_act_nombre').val(nombre);
	$('#id_act_estado').val(estado);
	$('#idModalActualiza').modal("show");
}

$(document).ready(function() {
    $('#tableGrado').DataTable();
} );
</script>

<script type="text/javascript">
$(document).ready(function() {
	$('#id_form_registra').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	"nombre":{
    			selector :"#id_reg_nombre",
    			validators: {
    				notEmpty :{
    					message : "El nombre es obligatorio"
    				},
    				stringLength: {
    					min: 2,
                        max: 50,
                        message: 'El nombre debe contener entre 2 y 50 caracteres'
                    }
    			}	
         	},
        	"estado":{
    			selector :"#id_reg_estado",
    			validators: {
    				notEmpty :{
    					message : "El estado es obligatorio"
    				},
    			}	
         	},         	
       
        }   
    });
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	$('#id_form_actualiza').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
          	"nombre":{
    			selector :"#id_act_nombre",
    			validators: {
    				notEmpty :{
    					message : "El nombre es obligatorio"
    				},
    				stringLength: {
    					min: 2,
                        max: 50,
                        message: 'El nombre debe contener entre 2 y 50 caracteres'
                    }
    			}	
         	},
         	"estado":{
    			selector :"#id_act_estado",
    			validators: {
    				notEmpty :{
    					message : "El estado es obligatorio"
    				},
    			}	
         	},   
          
        }   
    });
});
</script>




</body>
</html>



