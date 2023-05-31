package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EmpleadoDAO;
import entity.Empleado;
import fabricas.Fabrica;

@WebServlet("/listaEmpleadoComplejo")
public class ListaEmpleadoComplejo extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1 Traer todas las categorias de la base de datos
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		EmpleadoDAO  dao = fabrica.getEmpleado();
	
		String vfechaInicio = req.getParameter("fechaInicio");
		String vfechaFin = req.getParameter("fechaFin");
		String vnombre = req.getParameter("nombre");
		String vestado = req.getParameter("estado");
		String vpais = req.getParameter("pais");	
		
		List<Empleado> lista = dao.listaCompleja(vnombre+"%", 
												 Integer.parseInt(vpais),
												 Integer.parseInt(vestado), 
												 Date.valueOf(vfechaInicio),
												 Date.valueOf(vfechaFin));	
		
		//2 Convertir las categorias en formato JSON
		Gson gson = new Gson();
		String json = gson.toJson(lista);

		//3 Se notifica al chrome el tipo de archivo
		resp.setContentType("application/json;charset=UTF-8");
		
		//4 Se envía al chrome
		PrintWriter out = resp.getWriter();
		out.println(json);
	}

}