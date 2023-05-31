package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dao.EmpleadoDAO;
import entity.Empleado;
import entity.Pais;
import util.FechaUtil;
import util.MySqlDBConexion;

public class MySqlEmpleadoDAO implements EmpleadoDAO{


	private static Logger log = Logger.getLogger(MySqlEmpleadoDAO.class.getName());
	
	public int insertaEmpleado(Empleado  obj) {
		int salida = -1;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into empleado values(null,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setDate(2, obj.getFechaNacimiento());
			pstm.setInt(3, obj.getEstado());
			pstm.setTimestamp(4, obj.getFechaRegistro());
			pstm.setInt(5, obj.getPais().getIdPais());
			
			log.info(">>>> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return salida;
	}

	@Override
	public List<Empleado> listaPorFecha(Date fecInicio, Date fecFin) {
		List<Empleado> lista = new ArrayList<Empleado>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "SELECT e.*, p.nombre FROM empleado e "
					+ "inner join pais p on e.idPais = p.idPais "
					+ "where fechaNacimiento between ? and ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, fecInicio);
			pstm.setDate(2, fecFin);
			
			log.info(">>>> " + pstm);

			rs = pstm.executeQuery();
			Empleado objEmpleado = null;
			Pais objPais = null;
			while(rs.next()) {
				objEmpleado = new Empleado();
				objEmpleado.setIdEmpleado(rs.getInt(1));
				objEmpleado.setNombres(rs.getString(2));
				objEmpleado.setFechaNacimiento(rs.getDate(3));
				objEmpleado.setEstado(rs.getInt(4));
				objEmpleado.setFechaRegistro(rs.getTimestamp(5));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(6));
				objPais.setNombre(rs.getString(7));
				objEmpleado.setPais(objPais);
				
				lista.add(objEmpleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return lista;
	}
	
	
	@Override
	public List<Empleado> listaEmpleado(String filtro) {
		List<Empleado> lista = new ArrayList<Empleado>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "SELECT e.*, p.nombre FROM empleado e "
					+ "inner join pais p on e.idPais = p.idPais "
					+ "where e.nombres like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);

			log.info(">>>> " + pstm);

			rs = pstm.executeQuery();
			Empleado objEmpleado = null;
			Pais objPais = null;
			while(rs.next()) {
				objEmpleado = new Empleado();
				objEmpleado.setIdEmpleado(rs.getInt(1));
				objEmpleado.setNombres(rs.getString(2));
				objEmpleado.setFechaNacimiento(rs.getDate(3));
				objEmpleado.setEstado(rs.getInt(4));
				objEmpleado.setFechaRegistro(rs.getTimestamp(5));
				objEmpleado.setFormateadoFecNac(FechaUtil.getFechaFormateadaYYYYMMdd(rs.getDate(3)));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(6));
				objPais.setNombre(rs.getString(7));
				objEmpleado.setPais(objPais);
				
				lista.add(objEmpleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return lista;
	}
	
	
	@Override
	public int actualizaEmpleado(Empleado obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update empleado set nombres=?, fechaNacimiento=?, estado=?, idPais=? where idEmpleado=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setDate(2, obj.getFechaNacimiento());
			pstm.setInt(3, obj.getEstado());
			pstm.setInt(4, obj.getPais().getIdPais());
			pstm.setInt(5, obj.getIdEmpleado());
			
			log.info(">>>> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}

	@Override
	public int eliminaEmpleado(int idEmpleado) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "delete from empleado where idEmpleado = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEmpleado);
			
			log.info(">>>> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}

	@Override
	public Empleado buscaEmpleado(int idEmpleado) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Empleado objEmpleado = null;
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "SELECT e.*, p.nombre FROM empleado e "
					+ "inner join pais p on e.idPais = p.idPais "
					+ "where e.idEmpleado = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEmpleado);

			log.info(">>>> " + pstm);

			rs = pstm.executeQuery();
			
			Pais objPais = null;
			while(rs.next()) {
				objEmpleado = new Empleado();
				objEmpleado.setIdEmpleado(rs.getInt(1));
				objEmpleado.setNombres(rs.getString(2));
				objEmpleado.setFechaNacimiento(rs.getDate(3));
				objEmpleado.setEstado(rs.getInt(4));
				objEmpleado.setFechaRegistro(rs.getTimestamp(5));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(6));
				objPais.setNombre(rs.getString(7));
				objEmpleado.setPais(objPais);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return objEmpleado;
	}

	@Override
	public List<Empleado> listaCompleja(String nombre, int idPais, int estado, Date fecInicio, Date fecFin) {
		List<Empleado> lista = new ArrayList<Empleado>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "SELECT e.*, p.nombre FROM empleado e "
					+ "inner join pais p on e.idPais = p.idPais "
					+ "where 1=1 "
					+ "and e.nombres like ? "
					+ "and e.fechaNacimiento > ? "
					+ "and e.fechaNacimiento < ? "
					+ "and e.estado = ? "
					+ "and e.idpais = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombre);
			pstm.setDate(2, fecInicio);
			pstm.setDate(3, fecFin);
			pstm.setInt(4, estado);
			pstm.setInt(5, idPais);

			log.info(">>>> " + pstm);

			rs = pstm.executeQuery();
			Empleado objEmpleado = null;
			Pais objPais = null;
			while(rs.next()) {
				objEmpleado = new Empleado();
				objEmpleado.setIdEmpleado(rs.getInt(1));
				objEmpleado.setNombres(rs.getString(2));
				objEmpleado.setFechaNacimiento(rs.getDate(3));
				objEmpleado.setEstado(rs.getInt(4));
				objEmpleado.setFechaRegistro(rs.getTimestamp(5));
				objEmpleado.setFormateadoFecNac(FechaUtil.getFechaFormateadaYYYYMMdd(rs.getDate(3)));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(6));
				objPais.setNombre(rs.getString(7));
				objEmpleado.setPais(objPais);
				
				lista.add(objEmpleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return lista;
	}
	
	
}







