package www.rodrigoledesmagarcia.com.mx.controlador;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import www.rodrigoledesmagarcia.com.mx.servicios.LoginServiceICookieImpl;
import www.rodrigoledesmagarcia.com.mx.servicios.LoginService;
import www.rodrigoledesmagarcia.com.mx.servicios.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);
        if (username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath()+ "/login");

    }
}
