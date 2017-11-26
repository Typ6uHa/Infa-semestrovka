package servlet;

import database.dao.DBConnection;
import database.sql.UserDao;
import helper.ServletHelper;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {

    private final static String COOKIE_USER = "user_id";
    private final static String SESSION_USER = "current_user";

    private boolean checkNickname(String nickname) {
        UserDao userDao = new UserDao(DBConnection.getDBConnection());
        return userDao.getUserByNickname(nickname) == null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String city = request.getParameter("city");
        User user = ServletHelper.getUserFromDAO(request);
        request.setAttribute("fail", false);
        request.setAttribute("empty", false);
        request.setAttribute("nick_letters", false);
        request.setAttribute("nick_length", false);
        request.setAttribute("login_nick", false);

        if (!username.isEmpty() && !name.isEmpty()
                && !surname.isEmpty() && !city.isEmpty()) {

            if (!checkNickname(username) && user.getNickname().equals(username)) {
                setAllAttributeSuccess(request);

                user.setNickname(username);
                user.setName(name);
                user.setSurname(surname);
                user.setCity(city);


                String savePath = getServletConfig().getServletContext().getRealPath("upload");
                File fileSaveDir = new File(savePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }
                Part part = request.getPart("image");
                Random random = new Random();
                String filePath = extractFileName(part);

                if (filePath.length() > 2) {
                    String ext = filePath.substring(filePath.lastIndexOf("."));
                    File uploadedFile;
                    String fileName;
                    do {
                        if (user.getPhotoUrl() != null){
                            fileName = user.getPhotoUrl().split("\\\\")[1];
                            filePath = savePath + File.separator + fileName;
                            File oldPhoto = new File(filePath);
                            oldPhoto.delete();
                        }
                        fileName = random.nextInt(1000000000) + ext;
                        filePath = savePath + File.separator + fileName;
                        uploadedFile = new File(filePath);
                    } while (uploadedFile.exists());
                    part.write(filePath);
                    user.setPhotoUrl("../upload" + File.separator + fileName);
                }

            UserDao userDao = new UserDao(DBConnection.getDBConnection());
            userDao.update(user);
        } else {
            request.setAttribute("fail", true);
            Pattern p = Pattern.compile("^[a-zA-Z1-9]+$");
            if (!p.matcher(username).matches())
                request.setAttribute("nick_letters", true);
            else if (username.length() < 4 || username.length() > 16)
                request.setAttribute("nick_length", true);
            else request.setAttribute("login_nick", true);
        }

    } else

    {
        request.setAttribute("fail", true);
        request.setAttribute("empty", true);
    }

    openProfile(request, user, response);

}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletHelper.getUserFromDAO(request);
        setAllAttributeSuccess(request);
        openProfile(request, user, response);
    }

    private void setAllAttributeSuccess(HttpServletRequest request) {
        request.setAttribute("fail", false);
        request.setAttribute("empty", false);
        request.setAttribute("nick_letters", false);
        request.setAttribute("nick_length", false);
        request.setAttribute("login_nick", false);
    }

    private void openProfile(HttpServletRequest request, User user, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", user.getNickname());
        request.setAttribute("name", user.getName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("city", user.getCity());
        if (request.getSession().getAttribute(SESSION_USER) != null) {
            request.setAttribute("login", false);
        } else request.setAttribute("login", true);
        request.setAttribute("photo", user.getPhotoUrl());
        request.setAttribute("current_url", request.getRequestURI());
        request.getRequestDispatcher("profile.ftl").forward(request, response);
    }


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
