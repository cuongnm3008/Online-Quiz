
package Ultil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.UserModel;

/**
 *
 * @author NguyenManhCuong
 */
public class FormUltil {
    
    // get date from form register
    public static UserModel getDataFormInput(HttpServletRequest request) {
        UserModel userModel = new UserModel();
        if (request.getParameterMap().containsKey("username")) {
            userModel.setUsername(request.getParameter("username"));
        }
        if (request.getParameterMap().containsKey("password")) {
            userModel.setPassword(request.getParameter("password"));
        }
        if (request.getParameterMap().containsKey("roleid")) {
            if (!request.getParameter("roleid").isEmpty()) {
                Integer roleid = null;
                try {
                    roleid = Integer.parseInt(request.getParameter("roleid"));
                    if (roleid < 1 || roleid > 2) {
                        roleid = null;
                    }
                } catch (NumberFormatException ex) {
                }
                userModel.setRoleID(roleid);
            }
        }
        if (request.getParameterMap().containsKey("email")) {
            String email = request.getParameter("email");
            Pattern pattern = Pattern.compile(Contanst.PATTERN_EMAIL);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                userModel.setEmail(email);
            }
        }
        return userModel;
    }

    public static boolean checkData(HttpServletRequest request, String type) throws IOException, ServletException {
        if (request.getParameterMap().containsKey(type)) {
            if (request.getParameter(type).trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean isRightOptions(String[] answers) {
      //  String ans = "";
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals("1") || answers[i].equals("2")
                    || answers[i].equals("3") || answers[i].equals("4")) {
            } else {
                return false;
            }
        }
        return true;
    }

    
}
