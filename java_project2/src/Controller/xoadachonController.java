package Controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.giohangbean;
import bo.giohangbo;

/**
 * Servlet implementation class xoadachonController
 */
@WebServlet("/xoadachonController")
public class xoadachonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xoadachonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Enumeration<String> d= request.getParameterNames();
			while(d.hasMoreElements()) {
				giohangbo gh;
				String ten = d.nextElement();
				String masanpham = request.getParameter(ten);
				if(masanpham!=null) {
					HttpSession session = request.getSession();
					//Luu session("gh") ra bien gh
			
					gh = (giohangbo)session.getAttribute("gh");
					if(session.getAttribute("dem")!=null) {
						long dem = (long) session.getAttribute("dem");
						for(giohangbean h : gh.ds) {
							if(h.getMasanpham().equals(masanpham))
								dem = dem - h.getSoluong();
						}
						session.setAttribute("dem", dem);
					}
					
					gh.Xoa(masanpham);
					session.setAttribute("gh", gh); //Luu bien vao lai session	
					
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("htgio.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
