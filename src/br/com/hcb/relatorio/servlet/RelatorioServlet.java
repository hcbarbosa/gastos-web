package br.com.hcb.relatorio.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.hcb.relatorio.ConnectionFactory;
import br.com.hcb.relatorio.gerador.GeradorRelatorio;

@WebServlet("/gastos")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/gastos_por_mes.jasper");
			Connection conn = new ConnectionFactory().getConnection();
			Map<String, Object> map = new HashMap<String, Object>();
			
			String dataIni = request.getParameter("data_ini");
			String dataFim = request.getParameter("data_fim");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			Date dataInicial = format.parse(dataIni);
			Date dataFinal = format.parse(dataFim);
			
			map.put("data_ini", dataInicial);
			map.put("data_fim", dataFinal);
			
			GeradorRelatorio relatorio = new GeradorRelatorio(nome, map, conn);
			relatorio.geraPDFParaOutputStream(response.getOutputStream());
			
			conn.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			throw new ServletException(e);
		} 
	}
}
