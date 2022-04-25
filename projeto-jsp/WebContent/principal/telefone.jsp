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
														<h5>Cadastro Telefones</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
													</div>
													<div class="card-block">


														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletTelefone" method="post" id="formFone">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="idUser" id="idUser" class="form-control" readonly="readonly" value="${modelLogin.id}">
																<span class="form-bar"></span> 
																<label class="float-label">ID Usuário:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nomeUser" id="nomeUser" class="form-control" readonly="readonly" required="required" value="${modelLogin.nome}">
																<span class="form-bar"></span> <label
																	class="float-label">Usuário:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero" class="form-control" required="required" value="">
																<span class="form-bar"></span> <label
																	class="float-label">Número:</label>
															</div>

															<button class="btn btn-success waves-effect waves-light">Salvar</button>

														</form>

													</div>
												</div>
											</div>

											<span id="msg">${msg}</span> 
											
											<div style="height: 500px; overflow: scroll; width: 100%;">
												<table class="table" id="tabelaResultadoTelefones">
												  <thead>
												    <tr>
												      <th scope="col">Id</th>
												      <th scope="col">Número</th>
												      <th scope="col">Ação</th>
												    </tr>
												  </thead>
												  <tbody>
												  	<c:forEach items="${modelTelefones}" var="fone">
												  		<tr>
												  			<td>
												  				<c:out value="${fone.id}"></c:out>
												  			</td>
												  			<td>
												  				<c:out value="${fone.numero}"></c:out>
												  			</td>
												  			<td>
												  				<a href="<%= request.getContextPath() %>/ServletTelefone?acao=excluirTelefone&id=${fone.id}&idPai=${modelLogin.id}" class="btn btn-success">Excluir</a>
												  			</td>
												  		</tr>												  	
												  	</c:forEach>
									
												  </tbody>
												</table>
										  </div>

										</div>


									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfiles.jsp"></jsp:include>


</body>

</html>
