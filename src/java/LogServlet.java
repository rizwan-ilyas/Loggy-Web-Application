
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/LogServlet"})
public class LogServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            Service service;
            try {
                service = new Service("jdbc:mysql://localhost:3306/tasks", "root", "");
                Log l = new Log(request.getParameter("title"), request.getParameter("content"));
                service.addLog(l);
                //service.deleteLog();
                response.sendRedirect("displayServlet");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LogServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } finally {
            out.close();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
