package com.minticcruddba.app.dao;

import java.util.List;

import com.minticcruddba.app.entity.Cliente;

public interface IClienteDao {
	public void save(Cliente cliente);
	public void delete(Long Id);
	public Cliente findone(Long Id);
	public List<Cliente> findall(Long Id);

}
