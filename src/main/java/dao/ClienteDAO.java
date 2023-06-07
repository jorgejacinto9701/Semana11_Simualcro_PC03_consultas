package dao;

import java.util.List;

import entity.Cliente;

public interface ClienteDAO {

	//Consulta
	public abstract List<Cliente> listaClienteComplejo(String nombre, String dni, int estado, int idCategoria);
	
	//CRUD
	public abstract int insertaCliente(Cliente obj);
	public abstract List<Cliente> listaCliente(String filtro);
	public abstract int actualizaCliente(Cliente obj);
	public abstract int eliminaCliente(int idCliente);
	public abstract Cliente buscaCliente(int idCliente);
}
