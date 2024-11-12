package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.Contato;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/edit", "/delete"})
public class Controller extends HttpServlet {

    DAO dao = new DAO();
    Contato contato = new Contato();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/main")) {
            try {
                contatos(request, response);
            } catch (SQLException e) {
                tratarErroBancoDados(request, response, e);
            }
        }
        if (action.equals("/select")) {
            listarContato(request, response);
        }
        
        if (action.equals("/delete")) {
            deletarContato(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/insert")) {
            try {
                novoContato(request, response);
            } catch (SQLException e) {
                tratarErroBancoDados(request, response, e);
            }
        }

        if (action.equals("/edit")) {
            editarContato(request, response);
        }
    }

    // Listar Contatos
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ArrayList<Contato> contatos = dao.listarContatos();
        request.setAttribute("contatos", contatos);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    // Novo Contato
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        contato.setNome(request.getParameter("nome"));
        contato.setPhone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));

        dao.inserirContato(contato);
        response.sendRedirect("main");
    }

    // editar contato
    protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idcon = request.getParameter("idcon");
        contato.setIdcon(idcon);
        dao.selecionarContato(contato);
        request.setAttribute("contato", contato);

        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }

    protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        contato.setIdcon(request.getParameter("id"));
        contato.setNome(request.getParameter("nome"));
        contato.setPhone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));

        dao.editarContato(contato);

        response.sendRedirect("main");
    }

    protected void deletarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        contato.setIdcon(request.getParameter("idcon"));
        
        dao.deletarContato(contato);
        
        response.sendRedirect("main");
    }
    
    // Tratativa de Erro de Banco de Dados
    private void tratarErroBancoDados(HttpServletRequest request, HttpServletResponse response, SQLException e)
            throws ServletException, IOException {
        // Log do erro (opcional)
        e.printStackTrace();

        // Define uma mensagem de erro para o usuário
        request.setAttribute("erro", "O sistema está temporariamente indisponível. Tente novamente mais tarde.");

        // Redireciona para uma página de erro ou exibe a mensagem no JSP atual
        RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
        rd.forward(request, response);
    }
}
