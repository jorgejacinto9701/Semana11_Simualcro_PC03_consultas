package dao;

import java.sql.Date;
import java.util.List;

import entity.Empleado;

public interface EmpleadoDAO {
	
	//Consultas
	public abstract List<Empleado> listaPorFecha(Date fecInicio, Date fecFin);
	public abstract List<Empleado> listaCompleja(String nombre, int idPais, int estado, Date fecInicio, Date fecFin);
	
	//CRUD
	public abstract int insertaEmpleado(Empleado obj);
	public abstract List<Empleado> listaEmpleado(String filtro);
	public abstract int actualizaEmpleado(Empleado obj);
	public abstract int eliminaEmpleado(int idEmpleado);
	public abstract Empleado buscaEmpleado(int idEmpleado);
	
}
