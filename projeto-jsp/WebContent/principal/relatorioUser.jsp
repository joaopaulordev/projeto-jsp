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
														<h5>Relatório Usuário</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
													</div>
													<div class="card-block">


														<form class="form-material" action="<%=request.getContextPath()%>/ServletUsuarioController" method="get" id="formRel">

															<input type="hidden" name="acao" id="acaoImprimirTipo" value="">
															
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
															      <button type="button" onclick="imprimirRel('HTML');" class="btn btn-primary mb-2">Imprimir Tela</button>
															      <button type="button" onclick="imprimirRel('PDF');" class="btn btn-primary mb-2">Imprimir PDF</button>
															    </div>
															  </div>
															  
														</form>
														
														
													 <div style="height: 600px; overflow: scroll; width: 100%;">
														<table class="table" id="tabelaRelUser">
														  <thead>
														    <tr>
														      <th scope="col">Id</th>
														      <th scope="col">Nome</th>														      
														    </tr>
														  </thead>
														  <tbody>
														  	<c:forEach items="${listaUsuarios}" var="u">
														  		<tr>
														  			<td><c:out value="${u.id}"></c:out></td>
														  			<td><c:out value="${u.nome}"></c:out></td>
														  		</tr>									
														  		<c:forEach items="${u.telefones}" var="fone">														  			
														  			<tr>
														  				<td/>
														  				<td style="font-size: 10px"><c:out value="${fone.numero}"></c:out></td>
														  			</tr>														  																	  		
														  		</c:forEach>			  	
														  	</c:forEach>
											
														  </tbody>
														</table>
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
    
<script type="text/javascript">

function imprimirRel(tipo) {
	document.getElementById("acaoImprimirTipo").value = 'imprimirRelatorioUser'+ tipo;
	$("#formRel").submit();
}


$( function() {
	  
	  $("#dataInicial").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );

$( function() {
	  
	  $("#dataFinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );


</script>
</body>

</html>
