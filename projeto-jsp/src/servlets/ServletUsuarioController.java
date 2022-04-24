package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

@MultipartConfig
@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuario = new DAOUsuarioRepository();

	public ServletUsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				
				String id = request.getParameter("id");
				daoUsuario.deletarUsuario(id);

				
				request.setAttribute("modelLogins", daoUsuario.buscarUsuarios(super.getIdUsuarioLogado(request)));
				
				
				request.setAttribute("msg", "Usuário deletado com sucesso!");	
				request.setAttribute("totalPagina", daoUsuario.totalPagina(super.getIdUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				
				String id = request.getParameter("id");
				daoUsuario.deletarUsuario(id);				
				response.getWriter().write("Registro excluido com sucesso!!");
				
				
				
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuarioAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca");				
				List<ModelLogin> lista = daoUsuario.buscarUsuarioByNome(nomeBusca, super.getIdUsuarioLogado(request));
				
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(lista);
				
				response.addHeader("totalPaginasAjax", ""+ daoUsuario.buscarUsuarioByNomeTotalPaginas(nomeBusca, super.getIdUsuarioLogado(request)));
				response.getWriter().write(json);
				
				
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuarioAjaxPaginacao")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				String paginaOffSet = request.getParameter("pagina");
				
				List<ModelLogin> lista = daoUsuario.buscarUsuarioByNomePaginadoOffSet(nomeBusca, super.getIdUsuarioLogado(request), paginaOffSet);				
				
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(lista);
				
				response.addHeader("totalPaginasAjax", ""+ daoUsuario.buscarUsuarioByNomeTotalPaginas(nomeBusca, super.getIdUsuarioLogado(request)));
				response.getWriter().write(json);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuario.consultaUsuarioById(id);
				
				request.setAttribute("msg", "Usuário em edição.");
				request.setAttribute("modelLogin", modelLogin);

				request.setAttribute("modelLogins", daoUsuario.buscarUsuarios(super.getIdUsuarioLogado(request)));
				request.setAttribute("totalPagina", daoUsuario.totalPagina(super.getIdUsuarioLogado(request)));
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
				
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuarios")) {
				
				List<ModelLogin> modelLogins = daoUsuario.buscarUsuarios(super.getIdUsuarioLogado(request));
												
				request.setAttribute("msg", "Usuários retornados: "+ modelLogins.size());
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuario.totalPagina(super.getIdUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuario.consultaUsuarioById(id);
				if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					
					response.setHeader("Content-Disposition", "attachment;filename=fotoUsuario." + modelLogin.getFotoUserExtensao());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}
								
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				int offSet = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> modelLogins = daoUsuario.buscarUsuariosPaginado(super.getIdUsuarioLogado(request), offSet);
				
				request.setAttribute("msg", "Usuários retornados: "+ modelLogins.size());
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuario.totalPagina(super.getIdUsuarioLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else {
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String msg = "";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");			
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);			
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			
			
			if (request.getPart("fileFoto") != null) {
				
				Part part = request.getPart("fileFoto"); /*Pega foto da tela*/
				
				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); /*Converte imagem para byte*/
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);
					
					modelLogin.setFotoUser(imagemBase64);
					modelLogin.setFotoUserExtensao(part.getContentType().split("\\/")[1]);
				}
				
			}

			if (daoUsuario.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login.";
			} else {

				if (modelLogin.isNovo()) {
					msg = "Usuário gravado com sucesso!";
				} else {
					msg = "Usuário alterado com sucesso!";
				}

				modelLogin = daoUsuario.gravarUsuario(modelLogin, super.getIdUsuarioLogado(request));
			}

			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			
			request.setAttribute("modelLogins", daoUsuario.buscarUsuarios(super.getIdUsuarioLogado(request)));
			request.setAttribute("totalPagina", daoUsuario.totalPagina(super.getIdUsuarioLogado(request)));
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			redirecionar.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
