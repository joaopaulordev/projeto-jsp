<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
                                                        <h5>Cadastro Usu?rio</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                    
                                                    
                                                        <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
                                                        	
                                                        	<input type="hidden" name="acao" id="acao" value="">
                                                        
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID:</label>
                                                            </div>
                                                            
                                                            
                                                            <div class="form-group form-default input-group mb-4">
                                                            	<div class="input-group-prepend">
                                									<c:if test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
                                                                       <a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
	                                                                     <img alt="Imagem User" id="fotoembase64" src="${modelLogin.fotoUser}" width="70px">
	                                                                    </a>
                                                                    </c:if>
                                                                    
                                                                    <c:if test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
                                                                       <img alt="Imagem User" id="fotoembase64"  src="assets/images/user.png" width="70px">
                                                        			</c:if>
                                                            	</div>
                                                            	 <input type="file" id="fileFoto" name="fileFoto" onchange="visualizarImg('fotoembase64', 'fileFoto');" accept="image/*" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
                                                            </div>
                                                            
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome:</label>
                                                            </div>
                                                         
                                                            
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="dataNascimento" id="dataNascimento" class="form-control" required="required" value="${modelLogin.dataNascimento}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Data Nascimento:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="rendaMensal" id="rendaMensal" class="form-control" required="required" value="${modelLogin.rendaMensal}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Renda Mensal:</label>
                                                            </div>
                                                            
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modelLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail:</label>
                                                            </div>
                                                            
                                                             <div class="form-group form-default form-static-label">
															    <select class="form-control" id="perfil" name="perfil">
															      
															      <option value="ADMIN" <% 
															      	ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															        
															      if( modelLogin != null && modelLogin.getPerfil() != null && modelLogin.getPerfil().equals("ADMIN")){
															        	out.print(" selected ");															      		
															        }
															      %>>Admin</option>
															      
															      
															      
															      <option value="SECRETARIA" 
															      <% 
															      	modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															        
															      if( modelLogin != null && modelLogin.getPerfil() != null && modelLogin.getPerfil().equals("SECRETARIA")){
															        	out.print(" selected ");															      		
															        }
															      %>>Secret?ria</option>
															      
															      
															      
															      <option value="AUXILIAR" 
															       <% 
															      	modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															        
															      if( modelLogin != null && modelLogin.getPerfil() != null && modelLogin.getPerfil().equals("AUXILIAR")){
															        	out.print(" selected ");															      		
															        }
															      %>>Auxiliar</option>
															      
															    </select>
											                    <span class="form-bar"></span>
                                                                <label class="float-label">Perfil:</label>
															  </div>
															  
															  
															  
															  
															 <div class="form-group form-default form-static-label">
                                                                <input type="text" onblur="pesquisaCep();" name="cep" id="cep" class="form-control" required="required" value="${modelLogin.cep}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">CEP:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="logradouro" id="logradouro" class="form-control" required="required" value="${modelLogin.logradouro}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Logradouro:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="bairro" id="bairro" class="form-control" required="required" value="${modelLogin.bairro}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Bairro:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="localidade" id="localidade" class="form-control" required="required" value="${modelLogin.localidade}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Localidade:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="uf" id="uf" class="form-control" required="required" value="${modelLogin.uf}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">UF:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="numero" id="numero" class="form-control" required="required" value="${modelLogin.numero}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">N?mero:</label>
                                                            </div>                                                            
                                                            
                                                            
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" value="${modelLogin.login}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modelLogin.senha}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                            
                                                            	<input type="radio" name="sexo" value="MASCULINO" 
                                                            	<% 
															      modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															        
															      if( modelLogin != null && modelLogin.getSexo() != null && modelLogin.getSexo().equals("MASCULINO")){
															        	out.print(" checked ");															      		
															        }
															    %>
                                                            	>Masculino</>
                                                            	
                                                            	<input type="radio" name="sexo" value="FEMININO" 
                                                            	<% 
															      modelLogin = (ModelLogin) request.getAttribute("modelLogin");
															        
															      if( modelLogin != null && modelLogin.getSexo() != null && modelLogin.getSexo().equals("FEMININO")){
															        	out.print(" checked ");															      		
															        }
															    %>
                                                            	>Feminino</>
                                                            </div>
                                                            
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
												            <button class="btn btn-success waves-effect waves-light">Salvar</button>
												            <button type="button" class="btn btn-warning waves-effect waves-light" onclick="criaDeleteComAjax()">Excluir</button>
												            
												            <c:if test="${modelLogin.id != null && modelLogin.id != '' }">
												            	<a class="btn btn-primary waves-effect waves-light" href="<%= request.getContextPath() %>/ServletTelefone?iduser=${modelLogin.id}">Telefone</a>
												            </c:if>
												            
												            <!-- Button trigger modal -->
															<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModalUsuario">
															  Pesquisar
															</button>
												            
                                                        </form>
                                                        
                                                    </div>                                                                                                    
                                                </div>
                                            </div>
                                        
                                         	<span id="msg">${msg}</span>   
                                         	
                                   			<div style="height: 500px; overflow: scroll; width: 100%;">
												<table class="table" id="tabelaResultadoUsuarios">
												  <thead>
												    <tr>
												      <th scope="col">Id</th>
												      <th scope="col">Nome</th>
												      <th scope="col">Login</th>
												      <th scope="col">Ver</th>
												    </tr>
												  </thead>
												  <tbody>
												  	<c:forEach items="${modelLogins}" var="model">
												  		<tr>
												  			<td>
												  				<c:out value="${model.id}"></c:out>
												  			</td>
												  			<td>
												  				<c:out value="${model.nome}"></c:out>
												  			</td>
												  			<td>
												  				<c:out value="${model.login}"></c:out>
												  			</td>
												  			<td>
												  				<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${model.id}" class="btn btn-success">Ver</a>
												  			</td>
												  		</tr>												  	
												  	</c:forEach>
									
												  </tbody>
												</table>
										  </div>
										  
										  <nav aria-label="Page navigation example">
											  <ul class="pagination">
											  <%
											  	int totalPagina = (int) request.getAttribute("totalPagina");
											  	
											  	for(int p=0; p < totalPagina; p++){
											  		String url = request.getContextPath()+"/ServletUsuarioController?acao=paginar&pagina="+(p * 5);
											  		out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+ url +"\">"+ (p + 1) +"</a></li>");
											  	}
											  %>											    
											  </ul>
										  </nav>
										  
								                                         	
                                                                                 
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
    
    
	    <!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usu?rios</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
		        <div class="input-group mb-3">
				  <input type="text" id="nomeBusca" class="form-control" placeholder="Nome Usu?rio..." aria-label="Recipient's username" aria-describedby="basic-addon2">
				  <div class="input-group-append">
				    <button class="btn btn-success" type="button" onclick="buscarUsuarioByNome()">Buscar</button>
				  </div>
				</div>
				
				<div style="height: 300px; overflow: scroll;">
				<table class="table" id="tabelaResultado">
				  <thead>
				    <tr>
				      <th scope="col">Id</th>
				      <th scope="col">Nome</th>
				      <th scope="col">Login</th>
				      <th scope="col">Ver</th>
				    </tr>
				  </thead>
				  <tbody>
	
				  </tbody>
				</table>
			   </div>
	           
	      </div>
	      
		  <nav aria-label="Page navigation example">
			  <ul class="pagination" id="ulPaginacaoUserAjax">
			  </ul>
		  </nav>
	      
	      <div class="modal-footer" style="display: flex; justify-content: space-between;">
	        <span id="totalResultados"></span>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
    
    
    <jsp:include page="javascriptfiles.jsp"></jsp:include>
    
    <script type="text/javascript">
    
    		$("#rendaMensal").maskMoney({showSymbol:true, symbol:"R$ ", decimal:",", thousands:"."});
    		
    		//Formatando currency
    		const formatter = new Intl.NumberFormat('pt-BR', {
    		    currency : 'BRL',
    		    minimumFractionDigits : 2
    		});

    		$("#rendaMensal").val(formatter.format($("#rendaMensal").val()));
    		$("#rendaMensal").focus();
    		
    		//Formatando data
    		var dataNascimento = $("#dataNascimento").val();

    		if (dataNascimento != null && dataNascimento != '') {

    			var dateFormat = new Date(dataNascimento);
    			
    			$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));

    		}

    		$("#nome").focus();
    		
    		
    
		    $( function() {
		  	  
		  	  $("#dataNascimento").datepicker({
		  		    dateFormat: 'dd/mm/yy',
		  		    dayNames: ['Domingo','Segunda','Ter?a','Quarta','Quinta','Sexta','S?bado'],
		  		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		  		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S?b','Dom'],
		  		    monthNames: ['Janeiro','Fevereiro','Mar?o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		  		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		  		    nextText: 'Pr?ximo',
		  		    prevText: 'Anterior'
		  		});
		  } );

    
   		$("#numero").keypress(function (event) {
    	   return /\d/.test(String.fromCharCode(event.keyCode)); 
    	});

    	$("#cep").keypress(function (event) {
    	    return /\d/.test(String.fromCharCode(event.keyCode)); 
    	});

    
    	function pesquisaCep() {
    		
    		var cep = $("#cep").val();
    		    		
    		//Consulta o webservice viacep.com.br/
            $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $("#cep").val(dados.cep);
                    $("#logradouro").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#localidade").val(dados.localidade);
                    $("#uf").val(dados.uf);
                    
                } //end if.
                else {
                    //CEP pesquisado n?o foi encontrado.
                    limpa_formul?rio_cep();
                    alert("CEP n?o encontrado.");
                }
            });
		}
    
    	function visualizarImg(fotoembase64, fileFoto) {
    		
    		var preview = document.getElementById(fotoembase64);
    		var fileUser = document.getElementById(fileFoto).files[0];
    		var reader = new FileReader();
    		
    		reader.onloadend = function (){
    			preview.src = reader.result;  /*carrega foto na tela */
    		}
    		
    		if (fileUser){
    			reader.readAsDataURL(fileUser);
    		}else{
    			preview.src = '';
    		}			
		}
    	
    	
    
    	function verEditar(id) {
    		
    		var urlAction = document.getElementById("formUser").action;
    		
    		window.location.href = 	urlAction +'?acao=buscarEditar&id='+ id;
		}
    
    	
    	
    	function buscarUsuarioByNome() {
    		
    		var nomeBusca = document.getElementById("nomeBusca").value;
    		
    		var urlParams = 'nomeBusca=' + nomeBusca +'&acao=buscarUsuarioAjax'
    		
    		buscarUsuarioPaginadoAjax(urlParams);
		}
    	
    	
    	
    	function buscarUsuarioPaginadoAjax(urlParams) {
    		
    		var nomeBusca = document.getElementById("nomeBusca").value;
    		
    		if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){
    			
    			var urlAction = document.getElementById("formUser").action;

	    		$.ajax({
	    			method: "get",
	    			url: urlAction,
	    			data: urlParams,
	    			success: function (response, textStatus, xhr){
	    			
	    				var json = JSON.parse(response);
	    				
	    				$('#tabelaResultado > tbody > tr').remove();
	    				for(var p = 0; p < json.length; p++){
	    					$('#tabelaResultado > tbody').append('<tr> <td>'+ json[p].id +'</td>  <td>'+ json[p].nome +'</td> <td>'+ json[p].login +'</td> <td><button onclick="verEditar('+ json[p].id +')" type="button" class="btn btn-link">Ver</button></td> </tr>')
	    				}	    					
	    				document.getElementById('totalResultados').textContent = 'Total de Resultados: '+json.length;
	    				
	    				
	    				//Pagina??o
	    				$("#ulPaginacaoUserAjax > li").remove();
	    				var totalPaginasAjax = xhr.getResponseHeader("totalPaginasAjax");
	    				
	    				for (var p = 0; p < totalPaginasAjax; p++){
	    					var urlParams = 'nomeBusca='+ nomeBusca +'&acao=buscarUsuarioAjaxPaginacao&pagina='+(p *5);
	    					
	    					$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscarUsuarioPaginadoAjax(\''+ urlParams +'\')">'+ (p + 1) +'</a></li>');
	    				}
	    				
	    			}
	    			
	    		}).fail(function(xhr, status, errorThrown){
	    			alert('Erro ao buscar usu?rio: '+ xhr.responseText);
	    		});
    		}
			
		}
    
    	function criaDeleteComAjax() {
    		
    		if (confirm('Deseja realmente excluir o registro?')){
    			
	    		var urlAction = document.getElementById("formUser").action;
	    		var idUser = document.getElementById("id").value;
	    		
	    		$.ajax({
	    			method: "get",
	    			url: urlAction,
	    			data: 'id=' + idUser +'&acao=deletarajax',
	    			success: function (response){
	    				limparForm();
	    				document.getElementById('msg').textContent = response;
	    			}
	    			
	    		}).fail(function(xhr, status, errorThrown){
	    			alert('Erro ao deletar usu?rio por id: '+ xhr.responseText);
	    		});
    		}
		}
    
    	
    	function criarDelete() {
    		
    		if(confirm('Deseja realmente excluir o registro?')){
	    		document.getElementById("formUser").method = 'get';
	    		document.getElementById("acao").value = 'deletar';
	    		document.getElementById("formUser").submit();
    		}
		}
    	
    
    	function limparForm() {
    		var elementos = document.getElementById("formUser").elements;
    		
    		for (p = 0; p < elementos.length; p++){
    			elementos[p].value = '';
    		}
		}
    
    </script>

</body>

</html>
