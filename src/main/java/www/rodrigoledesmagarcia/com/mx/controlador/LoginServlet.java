package www.rodrigoledesmagarcia.com.mx.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import www.rodrigoledesmagarcia.com.mx.servicios.LoginService;
import www.rodrigoledesmagarcia.com.mx.servicios.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "Rodrigo";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> httpOptional = auth.getUsername(req);


        if (httpOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Hola "+httpOptional.get()+"</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1 style='color: green;' >Hola "+httpOptional.get()+" ya iniciaste sesion con exito</h1>");
                out.println("<br>");
                out.println("<p><a href=\""+req.getContextPath()+""+"\">Volver al inicio</a></p<");
                out.println("<br>");
                out.println("<p><a href=\""+req.getContextPath()+"/logout"+"\">Cerrar la sesion</a></p<");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            // Esta es la cookie
            HttpSession session = req.getSession();
            session.setAttribute("username", username);


            resp.sendRedirect(req.getContextPath()+"/login");
        }else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar");
        }
    }
}
