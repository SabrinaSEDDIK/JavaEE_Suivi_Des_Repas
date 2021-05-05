package fr.eni.tpsuividesrepas.bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import fr.eni.tpsuividesrepas.bo.Repas;
import fr.eni.tpsuividesrepas.dal.BusinessException;
import fr.eni.tpsuividesrepas.dal.DAOFactory;
import fr.eni.tpsuividesrepas.dal.RepasDAO;

//MANAGER gère les traitements liés à l'objet et contrôle la cohérence des données


public class RepasManager {
	
	private RepasDAO repasDAO = DAOFactory.getRepasDAO();
	
	public void ajouter(LocalDate date, LocalTime heure, String[] aliments) throws BusinessException {
		//CONSTRUCTION DE L'OBJET REPAS
		Repas repas = new Repas(date, heure, aliments);
		//ENVOI A LA DAL
		repasDAO.insert(repas);
	}

	public List<Repas> getAllRepas() throws BusinessException {
		return repasDAO.selectAll();
	}
	
}
