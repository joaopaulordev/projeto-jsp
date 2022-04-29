<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

 <jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <jsp:include page="theme-loader.jsp"></jsp:include>

  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
      <jsp:include page="navbar.jsp"></jsp:include>


          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
              <jsp:include page="navbarmainmenu.jsp"></jsp:include>

                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                      
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                        
                                            						<div class="col-md-12">
												<div class="card">
													<div class="card-header">
														<h5>Relat�rio Usu�rio</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
													</div>
													<div class="card-block">


														<form class="form-material" action="<%=request.getContextPath()%>/ServletUsuarioController" method="get" id="formGraficoSalarioUser">

															  <div class="form-row align-items-center">															    
															    
																<div class="col-auto">
															      <label class="sr-only" for="dataInicial">Data Inicial</label>
															      <input value="${dataInicial}" type="text" class="form-control" id="dataInicial" name="dataInicial" placeholder="Data Inicial">															      
															    </div>
															    
															    <div class="col-auto">
															      <label class="sr-only" for="dataFinal">Data Final</label>
															      <input value="${dataFinal}" type="text" class="form-control" id="dataFinal" name="dataFinal" placeholder="Data Final">															      
															    </div>
															    															    
															    <div class="col-auto">
															      <button type="button" onclick="gerarGrafico();" class="btn btn-primary mb-2">Gerar Gr�fico</button>
															    </div>
															  </div>
															  
														</form>
														
														
													 <div style="height: 100%; overflow: scroll; width: 100%;">
														<div>
															<canvas id="myChart"></canvas>
														</div>
													 </div>
												  

													</div>
												</div>
											</div>                                       
                                            
                                            
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="javascriptfiles.jsp"></jsp:include>
    
    
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script type="text/javascript">

var myChart = new Chart(document.getElementById('myChart'));

function gerarGrafico(){
	
		var urlAction = document.getElementById("formGraficoSalarioUser").action;
		var dataInicial = document.getElementById("dataInicial").value;
		var dataFinal = document.getElementById("dataFinal").value;
		
		$.ajax({
			method: "get",
			url: urlAction,
			data: 'dataInicial=' + dataInicial +'&dataFinal='+ dataFinal +'&acao=gerarGraficoMediaSalUser',
			success: function (response){
				
				  var json = JSON.parse(response);
				 
				  myChart.destroy();
				  
				  myChart = new Chart(
				    document.getElementById('myChart'),
				    {
			        	type: 'line',
			        	data: {
			        	    labels: json.perfils,
			        	    datasets: [{
			        	      label: 'Gr�fico de m�dia salarial',
			        	      backgroundColor: 'rgb(255, 99, 132)',
			        	      borderColor: 'rgb(255, 99, 132)',
			        	      data: json.salarios,
			        	    }]
			        	  },
			        	options: {}
				   }
				);
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert('Erro ao gerar gr�fico m�dia sal�rio: '+ xhr.responseText);
		});
	

}


$( function() {
	  
	  $("#dataInicial").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Pr�ximo',
		    prevText: 'Anterior'
		});
} );

$( function() {
	  
	  $("#dataFinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Pr�ximo',
		    prevText: 'Anterior'
		});
} );


</script>
</body>

</html>