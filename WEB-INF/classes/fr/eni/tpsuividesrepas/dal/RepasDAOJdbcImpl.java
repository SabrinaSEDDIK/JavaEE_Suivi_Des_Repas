package fr.eni.tpsuividesrepas.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tpsuividesrepas.bo.Repas;

public class RepasDAOJdbcImpl implements RepasDAO{

	//requêtes SQL
	private static final String INSERT_REPAS = "INSERT INTO repas (date_repas, heure_repas) VALUES(?,?)";
	private static final String INSERT_ALIMENTS = "INSERT INTO aliments (id_repas, nom_aliment) VALUES(?,?)";
	private static final String SELECT_ALL = "SELECT * FROM repas ORDER BY id_repas DESC";
	private static final String SELECT_ALIMENTS = "SELECT nom_aliment FROM aliments WHERE id_repas = ?";
	
	@Override
	public void insert(Repas repas) throws BusinessException {
		if(repas==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			pstmt1 = cnx.prepareStatement(INSERT_REPAS,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt1.setDate(1, Date.valueOf(repas.getDate()));
			pstmt1.setTime(2, Time.valueOf(repas.getHeure()));
			pstmt1.execute();
			rs = pstmt1.getGeneratedKeys();
			int idRepas = 0;
			if(rs.next())
			{
				idRepas = rs.getInt(1);
			}
			pstmt2 = cnx.prepareStatement(INSERT_ALIMENTS);
			for (String aliment : repas.getAliments()) {
				pstmt2.setInt(1, idRepas);
				pstmt2.setString(2, aliment.trim());
				pstmt2.execute();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}finally {
			fermer(rs);
			fermer(pstmt2);
			fermer(pstmt1);
		}
		
	}

	@Override
	public Repas selectById(int id) {
		//TODO
		return null;
	}

	
	@Override
	public List<Repas> selectAll() throws BusinessException {
		List<Repas> listeRepas = new ArrayList<Repas>();
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement stmt = null;
		PreparedStatement pstmt =null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			stmt = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SELECT_ALL);
			@SuppressWarnings("unused")
			int rssss=0;
			while(rs.next()) {
				rssss++;
			}
			rs.beforeFirst();
		
			while(rs.next()) {
				Repas repas = new Repas();
				repas.setIdentifiant(rs.getInt("id_repas"));
				repas.setDate(rs.getDate("date_repas").toLocalDate());
				repas.setHeure(rs.getTime("heure_repas").toLocalTime());
				pstmt = cnx.prepareStatement(SELECT_ALIMENTS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				//System.out.println(pstmt.getResultSetType()); //1003 = FORWARD_ONLY ; 1004 = SCROLL_INSENSITIVE
				pstmt.setInt(1, rs.getInt("id_repas"));
				rs2 = pstmt.executeQuery();
				int rsLength=0;
				while(rs2.next()) {
					rsLength++;
				}
				String[] listeAliments = new String[rsLength];
				rs2.beforeFirst();
				int indexTab = 0;
				while(rs2.next()) {
					listeAliments[indexTab] = rs2.getString("nom_aliment");
					indexTab++;
				}
				repas.setAliments(listeAliments);
				listeRepas.add(repas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ECHEC);
			throw businessException;
			
		}finally {
			fermer(rs2);
			fermer(rs);
			fermer(pstmt);
			fermer(stmt);
		}
		return listeRepas;
	}
	
	private void fermer(AutoCloseable ressource) {
		if(ressource!=null) {
			try {
				ressource.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
