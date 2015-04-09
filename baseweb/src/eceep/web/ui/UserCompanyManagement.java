package eceep.web.ui;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eceep.user.domain.CompanyNode;
import eceep.user.domain.UserCompany;
import eceep.user.service.User;
import eceep.web.repository.WebContext;

/**
 * Servlet implementation class UserCompanyManagement
 */
@WebServlet("/UserCompanyManagement")
public class UserCompanyManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCompanyManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Request Parameters
		String paraCompanyID = request.getParameter("companyID");
		
		// Get session
		HttpSession session = request.getSession();

		// Get this context
		WebContext context = WebContext.getContext(session);
		
		// Get User
		User user = context.getUser();
		
		// Get All of Company tree.
		try {
			CompanyNode allOfCompanys = user.getAllOfCompanys();
			request.setAttribute("node", allOfCompanys);
			
			// Current Company
			if(paraCompanyID==null || paraCompanyID.isEmpty())
				paraCompanyID = allOfCompanys.getChildren().get(0).getId() + "";
			
			UserCompany userCompany = user.getUserCompany(Integer.parseInt(paraCompanyID));
			request.setAttribute("usercompany", userCompany);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/useradmin/admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}