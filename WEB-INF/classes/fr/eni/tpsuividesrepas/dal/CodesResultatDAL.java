package fr.eni.tpsuividesrepas.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative de sélection d'objet
	 */
	public static final int SELECT_ECHEC=10000;
	
	/**
	 * Echec général quand erreur non gérée à  l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de l'insertion d'un objet null
	 */
	public static final int INSERT_OBJET_NULL=10002;
	
	
}
