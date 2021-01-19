package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.userDAO;
import Entity.Usuario;

@SuppressWarnings("serial")
@WebServlet(name = "control", urlPatterns = {"/control"})
public class Controller extends HttpServlet{
	userDAO suarioDAO;
    HttpSession session;

    public Controller() {
        super();
        suarioDAO = new userDAO();
    }

    private RequestDispatcher dis;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                ruta = "View/login.jsp";
                this.login(request, response, ruta);
                break;
            case "validar":
                ruta = "View/home.jsp";
                this.validar(request, response, ruta);
                break;
            case "salir":
                this.Salir(request, response);
                break;
        }
    }
    protected void Salir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(session != null){
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }
    protected void validar(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {

        Usuario us = new Usuario();
        us.setUsuario(request.getParameter("txtUser"));
        us.setPass(request.getParameter("txtPass"));
        try {
            if (suarioDAO.validarUser(us)) {
                if (suarioDAO.validarPass(us)) {
                    if (suarioDAO.validarTodo(us)) {
                        session = request.getSession(true);
                        
                        
                        Usuario base = suarioDAO.llenarUser(us);
                        session.setAttribute("NOMBRE",base.getPersona().getNombre());
                        session.setAttribute("APELLIDO", base.getPersona().getApellido());
                        session.setAttribute("ROL", base.getPersona().getRol().getRol());
                        
                        //request.setAttribute("base", suarioDAO.llenarUser(us));
                        dis = request.getRequestDispatcher(ruta);
                        dis.forward(request, response);
                    } else {
                        request.setAttribute("msjT", "Sus Credenciales no coinciden");
                        dis = request.getRequestDispatcher("View/login.jsp");
                        dis.forward(request, response);
                    }
                } else {
                    request.setAttribute("msjP", "Este Pass no esta registrado");
                    dis = request.getRequestDispatcher("View/login.jsp");
                    dis.forward(request, response);
                }

            } else {
                request.setAttribute("msjU", "Este usuario no esta registrado");
                dis = request.getRequestDispatcher("View/login.jsp");
                dis.forward(request, response);
            }
        } catch (Exception e) {
        }

    }

    protected void login(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
