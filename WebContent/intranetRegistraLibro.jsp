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


<title>Sistemas - Registro</title>
</head>
<body>

<jsp:include page="intranetCabecera.jsp" />
<div class="container" style="margin-top: 4%">
<h4>Registra Libro</h4>

	<c:if test="${sessionScope.MENSAJE != null}">
		<div class="alert alert-success fade in" id="success-alert">
		 <a href="#" class="close" data-dismiss="alert">&times;</a>
		 <strong>${sessionScope.MENSAJE}</strong>
		</div>
	</c:if>
	<c:remove var="MENSAJE" />

	<form action="RegistroLibro" id="id_form"> 
			<input type="hidden" name="metodo" value="registra">	
			<div class="form-group">
				<label class="control-label" for="id_codigo">CODIGO</label>
				<input class="form-control" type="text" id="id_codigo" name="codigo" placeholder="Ingrese el codigo">
			</div>
			<div class="form-group">
				<label class="control-label" for="id_titulo">TITULO</label>
				<input class="form-control" type="text" id="id_titulo" name="titulo" placeholder="Ingrese el titulo">
			</div>
			<div class="form-group">
				<label class="control-label" for="id_estado">ESTADO</label>
				<select class="form-control" name="estado" id="id_estado">
		                                        		<option value=" ">[Seleccione]</option>
		                                        		<option value="ACTIVO">ACTIVO</option>
		                                        		<option value="INACTIVO">INACTIVO</option>
		        </select>
			</div>
				<div class="form-group">
				<label class="control-label" for="id_tipo">TIPO</label>
				<input class="form-control" type="text" id="id_tipo" name="tipo" placeholder="Ingrese el tipo">
			</div>
			<div class="form-group">
				<label class="control-label" for="id_tipolibro">TIPO DE LIBRO</label>
				<select id="id_tipolibro" name="TipoLibro" class='form-control'>
					<option value="" >[SELECCIONE]</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary" >CREA LIBRO</button>
			</div>
	</form>
</div>

<script type="text/javascript">
	console.log("En carga TipoLibro");
	$.getJSON("CargaComboLibro",{"metodo":"TipoLibro"} ,function(data){
		console.log(data)
		$.each(data, function(i, x){
			$("#id_tipolibro").append("<option value='"+ x.idTipoLibro +"'>"+ x.nombre +"</option>");		
		});
	});
</script>

<script type="text/javascript">
$("#success-alert").fadeTo(1000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
</script>

<script type="text/javascript">
$(document).ready(function() {
    $('#id_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        
        fields:{
        	codigo :{
          		selector : '#id_codigo',
        		validators :{
        			notEmpty :{
        				message :"El codigo es obligatorio"
        			},
        			stringLength :{
                     	message:"El codigo es de 2 a 40 caracteres",
                     	min : 2,
                     	max : 40
                    }
        		}
        	},
        	titulo :{
        		selector : '#id_titulo',
        		validators :{
        			notEmpty :{
        				message :"El titulo es obligatorio"
        			},
        			stringLength :{
                     	message:"El titulo es de 2 a 40 caracteres",
                     	min : 2,
                     	max : 40
                    }
        		}
        	},
        	estado :{
        		selector : '#id_estado',
        		validators :{
        			notEmpty :{
        				message :"El estado es obligatorio"
        			},		
        		}
        	},
        	tipo :{
        		selector : '#id_tipo',
        		validators :{
        			notEmpty :{
        				message :"El tipo es obligatorio"
        			},
        			stringLength :{
                     	message:"El tipo es de 2 a 40 caracteres",
                     	min : 2,
                     	max : 40
                    }
        		}
        	},   	
        	TipoLibro :{
          		selector : '#id_tipolibro',
        		validators :{
        			notEmpty :{
        				message :"El tipo de libro es obligatorio"
        			},
        		}
        	},
        }
  
    });

    // Validate the form manually
    $('#validateBtn').click(function() {
        $('#id_form').bootstrapValidator('validate');
    });
});
</script>

		
</body>
</html>



