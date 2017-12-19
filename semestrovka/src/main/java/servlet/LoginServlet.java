package servlet;

import database.dao.DBConnection;
import database.sql.UserDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@javax.servlet.annotation.WebServlet(name = "servlet.LoginServlet")
public class LoginServlet extends HttpServlet {

    private final String COOKIE_USER = "user_id";
    private final String SESSION_USER = "current_user";

    private boolean check(String username, String password){
        UserDao dao = new UserDao(DBConnection.getDBConnection());
        String DBPassword = dao.getUserByLogin(username) == null ? null : dao.getUserByLogin(username).getPassword();
        try {
            password = md5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password.equals(DBPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("login");
        String password = request.getParameter("password");
        boolean remember = request.getParameter("checkbox") != null;
        if (username != null){
            username = username.toLowerCase();
        }
        if (check(username, password)){
            request.getSession().setAttribute(SESSION_USER, username);
            Cookie cookie = new Cookie(COOKIE_USER, username);
            if (remember){
                cookie.setMaxAge(30 * 24 * 60 * 60);
            }
            else {
                cookie.setMaxAge(-1);
            }
            response.addCookie(cookie);
            response.sendRedirect("/");
        }
        else {
            request.setAttribute("fail", true);
            request.getRequestDispatcher("/login.ftl").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fail", false);
        request.getRequestDispatcher("/login.ftl").forward(request, response);
        return;
    }

    private String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte aByteData : byteData) {
            String hex = Integer.toHexString(0xff & aByteData);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
