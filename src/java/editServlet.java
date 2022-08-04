
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/editServlet"})
public class editServlet extends HttpServlet {

    int i;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Service service = new Service("jdbc:mysql://localhost:3306/tasks", "root", "");
            
            String s=request.getParameter("id");
            Log l = service.getLogById(Integer.parseInt(s));
            i=Integer.parseInt(s);
            try {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("       <meta http-equiv='Content-Type' content='text/html' charset='UTF-8'>");
                out.println("        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor' crossorigin='anonymous'>");
                out.println("        <title>My Page</title>");
                out.println("    </head>");
                out.println("");
                out.println("    <body>");
                out.println("        <div class='container mt-5' style='width: 650px;'>");
                out.println("            <h1>Log Writing</h1>");
                out.println("            <form class='form' action='editServlet' method='POST'>");
                out.println("                <div class='mb-4'>");
                out.println("                    <label for='log' class='form-label'>Log Title</label>");
                out.println("                    <input type='text' class='form-control' value=" + l.getTitle() + " id='log' name='title'>");
                out.println("                </div>");
                out.println("                <div class='mb-4'>");
                out.println("                    <label class='form-label'>Log Content</label>");
                out.println("                    <textarea class='form-control' rows='7' id='content' name='content'>" + l.getLogContent() + "</textarea>");
                out.println("                </div>");
                out.println("                <button type='submit' class='btn btn-primary'>Create Log</button>");
                out.println("            </form>");
                out.println("        </div>");
                out.println("    </body>");
                out.println("</html>");

            } finally {
                out.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Service service;
        try {
            service = new Service("jdbc:mysql://localhost:3306/tasks", "root", "");
            Log log = new Log(i, request.getParameter("title"), request.getParameter("content"));
            if (service.editLog(log)) {
                response.sendRedirect("displayServlet");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //service.getLogById(Integer.getInteger(request.getParameter("id")));

        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
