package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;
import dao.DaoUsuario;
import models.UsuarioModel;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		try {
			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/UsuariosCadastrados.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "alert('Usuário deletado com sucesso!');");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				UsuarioModel usuarioModel = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", usuarioModel);
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String uf = request.getParameter("uf");
		String msg = null;
		UsuarioModel user = new UsuarioModel(login, senha, email);
		user.setCep(cep);
		user.setRua(rua);
		user.setBairro(bairro);
		user.setCidade(cidade);
		user.setUf(uf);

		/* Inicio Upload de Imagem */
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				Part imagemFoto = request.getPart("img");
				String fotoBase64 = new Base64()
						.encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));

				user.setFile(fotoBase64);
				user.setFileType(imagemFoto.getContentType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Fim upload de Imagem */

		if (login == null || login.isEmpty()) {
			msg = "Login deve ser informado!";

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("user", user);
			view.forward(request, response);
		} else if (senha == null || senha.isEmpty()) {
			msg = "Senha deve ser informado!";

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("user", user);
			view.forward(request, response);
		} else if (email == null || email.isEmpty()) {
			msg = "Email deve ser informado!";

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("user", user);
			view.forward(request, response);
		} else {
			user.setId(!id.isEmpty() ? Long.parseLong(id) : null);

			if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
				msg = "Já existe um usuário com esse Login!";
				request.setAttribute("msg", msg);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				view.forward(request, response);
			}

			if (id == null || id.isEmpty() && daoUsuario.validarLogin(login)) {
				daoUsuario.salvar(user);
				msg = "Sucesso!";
				request.setAttribute("msg", msg);
			} else if (id != null && !id.isEmpty()) {
				if (!daoUsuario.validarLoginUpdate(login, id)) {
					msg = "Já existe um usuário com esse login!";
					request.setAttribute("msg", msg);
				} else {
					daoUsuario.atualizar(user);
					msg = "Usuário atualizado com sucesso!";
				}
			}

			try {

				RequestDispatcher view = request.getRequestDispatcher("/UsuariosCadastrados.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();

		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}

		return baos.toByteArray();

	}

}
