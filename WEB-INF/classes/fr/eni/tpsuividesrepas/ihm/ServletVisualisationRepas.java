package fr.eni.tpsuividesrepas.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tpsuividesrepas.bll.RepasManager;
import fr.eni.tpsuividesrepas.bo.Repas;
import fr.eni.tpsuividesrepas.dal.BusinessException;

/**
 * Servlet implementation class ServletVisualisationRepas
 */
@WebServlet("/ServletVisualisationRepas")
public class ServletVisualisationRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Integer> listeCodesErreur=null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RepasManager repasManager = new RepasManager();
			List<Repas> allRepas = repasManager.getAllRepas();
			request.setAttribute("allRepas", allRepas);
		} catch (BusinessException ex) {
		
			ex.printStackTrace();
			listeCodesErreur = new ArrayList<>();
			listeCodesErreur.add(CodesResultatServlets.ERREUR_FORMAT);
			if(ex instanceof BusinessException) {
				for (Integer integer : ((BusinessException) ex).getListeCodesErreur()) {
					listeCodesErreur.add(integer);
					}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/historique.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
