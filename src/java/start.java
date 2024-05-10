
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class start extends HttpServlet {

    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            pw.println("<html><head><title>Quiz</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; }");
            pw.println(".container { height: 400px; width: 400px; background-color: white; border-radius: 20px; margin: 50px auto; padding: 20px;color:#7e57c2; }");
            pw.println("h4 { margin-top: 0; }");
            pw.println(".nav { margin-top:20px;}");
            pw.println("nav h1 b {color:#7e57c2;}");
            pw.println("nav { background-color: whitesmoke; display:flex; justify-content: space-between;box-shadow: #fff; }");
            pw.println(" .nav1 a{   color: #7e57c2;  text-decoration: none;}");
            pw.println("input[type='radio'] {  }");
            pw.println("input[type='submit'] { margin-top: 20px;  padding: 10px 0;border-radius: 5px;border-style:none; background-color: #7e57c2; color: white; padding: 10px 20px; cursor: pointer; }");
            pw.println("</style>");
            pw.println("</head><body>");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("select * from qans");
            rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("<nav> <div class='logo'>");
                pw.println("<h1><b>Quizzey</b></h1></div>");
                pw.println("<div class='nav1'>");
                pw.println("<a href='index.html'>Home</a>");
                pw.println("<a href=''>about us</a>");
                pw.println("<a href=''>contact</a>");
                pw.println("</div></nav>");

                pw.println("<div class='container'>");
                pw.println("<form action='NextQ'>");
                pw.println("<div>");
                pw.println("<h2>Question: " + rs.getString(3) + "</h2>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(4) + "'>" + rs.getString(4) + "<br>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(5) + "'>" + rs.getString(5) + "<br>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(6) + "'>" + rs.getString(6) + "<br>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(7) + "'>" + rs.getString(7) + "<br>");
                pw.println("<input type='submit' value='Next Question'>");
                pw.println("</div>");
                pw.println("</form>");
                pw.println("</div>");
                String ans = rs.getString(8);
                HttpSession hs = request.getSession(true);
                hs.setAttribute("resultset", rs);
                hs.setAttribute("answer", ans);
                hs.setAttribute("correct", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
