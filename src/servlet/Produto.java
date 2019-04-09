package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import models.ProdutoModel;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProduto daoProduto = new DaoProduto();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String acao = request.getParameter("acao");
		String prod = request.getParameter("prod");

		try {
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(prod);
				RequestDispatcher view = request.getRequestDispatcher("/ProdutosCadastrados.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("msg", "Produto deletado com sucesso!");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				ProdutoModel produtoModel = daoProduto.consultar(prod);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("prod", produtoModel);
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		String msg = null;

		ProdutoModel prod = new ProdutoModel();
		prod.setNome(nome);
		prod.setQuantidade((quantidade == "")? 0 :Integer.parseInt(quantidade));
		prod.setValor((valor == "")? 0 : Double.parseDouble(valor));

		if (nome == null || nome.isEmpty()) {
			msg = "Nome do produto deve ser informado!";

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("prod", prod);
			view.forward(request, response);
		} else if (quantidade == null || quantidade.isEmpty() && quantidade != "0") {
			msg = "Quantidade deve ser informado!";

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("prod", prod);
			view.forward(request, response);
		} else if (valor == null || valor.isEmpty() && valor != "0") {
			msg = "Valor deve ser informado e diferente de 0!";

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("prod", prod);
			view.forward(request, response);
		} else if(quantidade.charAt(0) == '0') {
			msg = "Quantidade não pode ser igual a 0!";
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("prod", prod);
			view.forward(request, response);
		} else if(valor.charAt(0) == '0' || valor =="0.0") {
			msg = "Valor não pode ser igual a 0!";
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("msg", msg);
			request.setAttribute("prod", prod);
			view.forward(request, response);
		} else {
			
			prod.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			
			if (id == null || id.isEmpty() && !daoProduto.validarProdutoNome(nome)) {
				request.setAttribute("msg", "Já existe um produto com esse nome!");
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				view.forward(request, response);
			}

			if (id == null || id.isEmpty() && daoProduto.validarProdutoNome(nome)) {
				daoProduto.salvar(prod);
				request.setAttribute("msg", "Produto adicionado com sucesso!");
			} else if (id != null && !id.isEmpty()) {
				if (!daoProduto.validarProdutoNomeUpdate(nome, id)) {
					request.setAttribute("msg", "Já existe um produto com esse nome!");
				} else {
					daoProduto.atualizar(prod);
					request.setAttribute("msg", "Produto atualizado com sucesso!");
				}
			}

			try {

				RequestDispatcher view = request.getRequestDispatcher("/ProdutosCadastrados.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
