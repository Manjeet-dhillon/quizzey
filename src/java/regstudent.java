import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class regstudent extends HttpServlet {
  public  Connection con=null;
  public PreparedStatement ps=null;
  public ResultSet rs=null;
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        response.setContentType("text/html");
        
        PrintWriter pw = response.getWriter();
        String name=request.getParameter("n1");
        String user=request.getParameter("e1");
        String pass=request.getParameter("pass");
        
  try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("insert into  studentdetails values (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, user);
             ps.setString(3, pass);
           
            
           int rs = ps.executeUpdate();
          
                pw.println("<script>alert('registered successfully');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("//login.html");
                rd.include(request, response);
          
            
        } catch(Exception e) {
            pw.println(e.getMessage());
           
        }
      
  }
  }
