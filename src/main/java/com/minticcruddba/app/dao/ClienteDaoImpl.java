package com.minticcruddba.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.minticcruddba.app.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@org.springframework.transaction.annotation.Transactional
	public void save(Cliente cliente) {
		if(cliente.getId()!=null && cliente.getId()>0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}		
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void delete(Long Id) {
		em.remove(findone(Id));		
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Cliente findone(Long Id) {
		
		return em.find(Cliente.class, Id);
	}

	@SuppressWarnings("unchecked")
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	@Override
	public List<Cliente> findall(Long Id) {
		
		return em.createQuery("from Cliente").getResultList();
	}

}
