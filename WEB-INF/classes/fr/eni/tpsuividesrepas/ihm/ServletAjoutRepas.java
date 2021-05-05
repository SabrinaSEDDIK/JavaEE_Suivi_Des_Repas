package fr.eni.tpsuividesrepas.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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


//SERVLET :  s'assurer que la demande utilisateur est valide avant de transmettre à la BLL
//VERIFICATION DU TYPE DES VARIABLES SURTOUT

/**
 * Servlet implementation class ServletAjoutRepas
 */
@WebServlet("/ServletAjoutRepas")
public class ServletAjoutRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Integer> listeCodesErreur=null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO contrôler les données de la requête
		//TODO construire la réponse
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajout.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean ok = true;
		
		//Récupérer les infos du formulaire
			String date = request.getParameter("date");
			String heure = request.getParameter("heure");
			String repas = request.getParameter("repas");
			
			try {
		//Vérifier la NON NULLITE des données
				if(date==null||heure==null||repas==null) {
					throw new Exception();
				}
		//Vérifier LE TYPE des données qui seront transmises à la BLL
		//La conversion String vers autre type peut soulever des erreurs
				LocalDate localDate = LocalDate.parse(date);
				LocalTime localTime = LocalTime.parse(heure);
				String[] tabAliments = repas.split(",");
		//Transmission à la BLL (MANAGER)
				RepasManager repasManager = new RepasManager();
				repasManager.ajouter(localDate, localTime, tabAliments);
				List<Repas> allRepas = repasManager.getAllRepas();
		// DONNEES A ENVOYER A LA PAGE D'AFFICHAGE
				request.setAttribute("allRepas", allRepas);
			}catch(Exception ex){
				ex.printStackTrace();
				listeCodesErreur = new ArrayList<>();
				listeCodesErreur.add(CodesResultatServlets.ERREUR_FORMAT);
				if(ex instanceof BusinessException) {
					for (Integer integer : ((BusinessException) ex).getListeCodesErreur()) {
						listeCodesErreur.add(integer);
						}
				}
			}
			
			
			if(listeCodesErreur!=null) {
				
				request.setAttribute("listeCodesErreur",listeCodesErreur);
				ok = false;
			}
			
		
		//SI OK go historique.jsp SINON ajout.jps
		
		if(ok) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/historique.jsp");
			 rd.forward(request,response);
		}else{
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajout.jsp");
			rd.forward(request,response);
		}
		
		
		
	}

}
