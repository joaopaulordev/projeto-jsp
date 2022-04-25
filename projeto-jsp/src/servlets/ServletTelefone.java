package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuario = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefone = new DAOTelefoneRepository();
       
    public ServletTelefone() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equals("excluirTelefone")) {
				String idFone = request.getParameter("id");
				String idPai = request.getParameter("idPai");
				
				daoTelefone.deletarTelefone(Long.parseLong(idFone));
				request.setAttribute("msg", "Telefone deletado com sucesso.");
				
				request.setAttribute("modelTelefones", daoTelefone.listarTelefonesByUsuarioPai(Long.parseLong(idPai)));
				request.setAttribute("modelLogin", daoUsuario.consultaUsuarioById(idPai));	
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);	
				
				return;
			}
			
			
			
			String idUser = request.getParameter("iduser");
			
			if (idUser != null && !idUser.isEmpty()) {
				request.setAttribute("modelLogin", daoUsuario.consultaUsuarioById(idUser));
				
				request.setAttribute("modelTelefones", new DAOTelefoneRepository().listarTelefonesByUsuarioPai(Long.parseLong(idUser)));
				
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);				
			
			} else {
				request.setAttribute("modelLogins", daoUsuario.buscarUsuarios(super.getIdUsuarioLogado(request)));
				request.setAttribute("totalPagina", daoUsuario.totalPagina(super.getIdUsuarioLogado(request)));
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);	
			}
			
						
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idUser = request.getParameter("idUser");
			String numero = request.getParameter("numero");
			
			ModelLogin modelLogin = daoUsuario.consultaUsuarioById(idUser);
			
			ModelTelefone objTelefone = new ModelTelefone();
			objTelefone.setNumero(numero);
			objTelefone.setUsuario_pai(modelLogin);
			objTelefone.setUsuario_cad(daoUsuario.consultaUsuarioById(""+super.getIdUsuarioLogado(request)));			
			new DAOTelefoneRepository().gravarTelefone(objTelefone);
			
			request.setAttribute("modelTelefones", daoTelefone.listarTelefonesByUsuarioPai(Long.parseLong(idUser)));
			
			request.setAttribute("modelLogin", modelLogin);			
			request.setAttribute("msg", "Telefone gravado com sucesso.");
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
			redirecionar.forward(request, response);			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
