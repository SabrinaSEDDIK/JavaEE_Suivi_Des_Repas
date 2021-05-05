package fr.eni.tpsuividesrepas.dal;

import java.util.List;

import fr.eni.tpsuividesrepas.bo.Repas;

public interface RepasDAO {
	
	public abstract void insert(Repas repas) throws BusinessException;
	public abstract Repas selectById(int id) throws BusinessException;
	public abstract List<Repas> selectAll() throws BusinessException;
}
