package servlet;

import database.dao.DBConnection;
import database.sql.UserDao;
import model.User;
import org.json.JSONObject;
import org.json.JSONString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "servlet.RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private final String JSON_ERROR = "error";
    private UserDao userDao;

    private boolean checkLogin(String login) {
        userDao = new UserDao(DBConnection.getDBConnection());
        return userDao.getUserByLogin(login) == null;
    }

    private boolean checkNickname(String nickname) {
        userDao = new UserDao(DBConnection.getDBConnection());
        return userDao.getUserByNickname(nickname) == null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String city = request.getParameter("city");

        request.setAttribute("fail", false);
        request.setAttribute("empty", false);
        request.setAttribute("login_letters", false);
        request.setAttribute("login_length", false);
        request.setAttribute("nick_letters", false);
        request.setAttribute("nick_length", false);
        request.setAttribute("rep_password", false);
        request.setAttribute("password", false);
        request.setAttribute("login_nick", false);
        request.setAttribute("incorrect_city", false);







        User user_demo = new User(login, password, nickname, name, surname, city);
        userDao = new UserDao(DBConnection.getDBConnection());
        String temp = userDao.add(user_demo);
//        if (temp == null) {
//            request.setAttribute("Message", "User added");
//            request.setAttribute("fail", false);
//            response.sendRedirect("/login");
//            userDao.add(user);
            if(temp!=null) {
            if (temp.contains("length_check_login")) {
                request.setAttribute("fail", true);
                request.setAttribute("login_length", true);
               // request.getRequestDispatcher("/registration.ftl").forward(request, response);
            }
            if (temp.contains("length_check_nickname")) {
                request.setAttribute("fail", true);
                request.setAttribute("nick_length", true);
              //  request.getRequestDispatcher("/registration.ftl").forward(request, response);
            }
        }






        if (!login.isEmpty() && !nickname.isEmpty() && !password.isEmpty()
                && !name.isEmpty() && !surname.isEmpty() && !city.isEmpty()){
            Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+$");
            if (!p.matcher(login).matches()) {
                request.setAttribute("fail", true);
                request.setAttribute("login_letters", true);
            } else {
//                if (login.length() < 4 || login.length() > 16) {
//                    request.setAttribute("login_length", true);
//                    request.setAttribute("fail", true);
//                } else {
                    if (!p.matcher(nickname).matches()) {
                        request.setAttribute("nick_letters", true);
                        request.setAttribute("fail", true);
                    } else {
//                        if (nickname.length() < 4 || nickname.length() > 16) {
//                            request.setAttribute("nick_length", true);
//                            request.setAttribute("fail", true);
//                        } else {
                            if (!password.equals(password2)) {
                                request.setAttribute("rep_password", true);
                                request.setAttribute("fail", true);
                            } else {
                                Pattern pass = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,16}$");
                                if (!pass.matcher(password).matches()) {
                                    request.setAttribute("password", true);
                                    request.setAttribute("fail", true);
                                } else {
                                    if (!checkLogin(login) || !checkNickname(nickname)){
                                        request.setAttribute("login_nick", true);
                                        request.setAttribute("fail", true);
                                    } else {
                                        Pattern c = Pattern.compile("^[a-zA-Z]+$");
                                        if (!c.matcher(city).matches()) {
                                            request.setAttribute("incorrect_city", true);
                                            request.setAttribute("fail",true);
                                        }
                                    }
                                }
                            }
                }
            }
        } else {
            request.setAttribute("empty", true);
            request.setAttribute("fail", true);
        }
        if ((boolean) request.getAttribute("fail"))
            request.getRequestDispatcher("/registration.ftl").forward(request, response);
        else {
            User user = new User(login, password, nickname, name, surname, city);
            userDao.insert(user);
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fail", false);
        request.setAttribute("empty", false);
        request.setAttribute("login_letters", false);
        request.setAttribute("login_length", false);
        request.setAttribute("nick_letters", false);
        request.setAttribute("nick_length", false);
        request.setAttribute("rep_password", false);
        request.setAttribute("password", false);
        request.setAttribute("login_nick", false);
        request.setAttribute("incorrect_city",false);
        request.getRequestDispatcher("/registration.ftl").forward(request, response);
    }
}
