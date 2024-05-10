
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class addquestions extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String course = request.getParameter("corse");
        String subject = request.getParameter("subj");
        String q = request.getParameter("q");
        String op1 = request.getParameter("op1");
        String op2 = request.getParameter("op2");
        String op3 = request.getParameter("op3");
        String op4 = request.getParameter("op4");
        String ans = request.getParameter("ans");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO qans VALUES (?, ?, ?, ?,?,?,?,?)");
            ps.setString(1, course);
            ps.setString(2, subject);
            ps.setString(3, q);
            ps.setString(4, op1);
            ps.setString(5, op2);
            ps.setString(6, op3);
            ps.setString(7, op4);
            ps.setString(8, ans);
            
            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("<script>alert('questions added successfully');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("qadd.html");
                rd.include(request, response);
            } else {
                out.println("<script>alert('Failed to add question');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("qadd.html");
                rd.include(request, response);
            }
            
            ps.close();
            con.close();
        } catch(Exception e) {
            out.println(e);
        }
    
        out.close();
    }
}
