package servlet;

import database.dao.DBConnection;
import database.sql.TopicDao;
import database.sql.UserDao;
import helper.ServletHelper;
import model.Topic;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "CreateServlet")
public class CreateServlet extends HttpServlet {

    private final static String COOKIE_USER = "user_id";
    private final static String SESSION_USER = "current_user";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String photoUrl = loadPhoto(request);
        User user = ServletHelper.getUserFromDAO(request);
        int user_id = user.getId();

        ServletHelper.initCookie(request);

        Topic topic = new Topic(user_id, title, description, photoUrl);

        if (title != null && description != null && photoUrl != null){
            TopicDao topicDao = new TopicDao(DBConnection.getDBConnection());
            topicDao.insert(topic);
            response.sendRedirect("/");
        }
        else {
            request.setAttribute("fail", true);
            if (title == null || description == null){
                request.setAttribute("empty", true);
                request.setAttribute("no_photo", false);
            }
            else {
                request.setAttribute("empty", false);
                request.setAttribute("no_photo", true);
            }
            if (request.getSession().getAttribute(SESSION_USER) != null)
                request.setAttribute("login", false);
            else request.setAttribute("login", true);
            request.setAttribute("current_url", request.getRequestURI());
            request.getRequestDispatcher("create.ftl").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(SESSION_USER) != null){
            request.setAttribute("login", false);
        }
        else request.setAttribute("login", true);

        request.setAttribute("current_url", request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("fail", false);
        request.setAttribute("empty", false);
        request.setAttribute("no_photo", false);

        request.getRequestDispatcher("create.ftl").forward(request, response);
    }

    private String loadPhoto(HttpServletRequest request) throws IOException, ServletException {
        String result = null;
        String savePath = getServletConfig().getServletContext().getRealPath("upload");
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part part = request.getPart("image");
        if (part != null){
            Random random = new Random();
            String filePath = extractFileName(part);

            if (filePath.length() > 2) {
                String ext = filePath.substring(filePath.lastIndexOf("."));
                File uploadedFile;
                String fileName;
                do {
                    fileName = random.nextInt(1000000000) + ext;
                    filePath = savePath + File.separator + fileName;
                    uploadedFile = new File(filePath);
                } while (uploadedFile.exists());
                part.write(filePath);
                result = "../upload" + File.separator + fileName;
            }
        }

        return result;
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
