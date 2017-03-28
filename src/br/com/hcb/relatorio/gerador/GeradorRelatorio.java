package br.com.hcb.relatorio.gerador;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GeradorRelatorio {

	private String nomeArquivo;
	private Map<String, Object> parametros;
	private Connection connection;

	public GeradorRelatorio(String nomeArquivo, Map<String, Object> parametros, Connection connection) {
		this.nomeArquivo = nomeArquivo;
		this.parametros = parametros;
		this.connection = connection;
	}

	public void geraPDFParaOutputStream(OutputStream outputStream) {

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametros, this.connection);

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();

			// outra forma de gerar relatorio
//			//compila o arquivo jrxml para jasper
//			JasperCompileManager.compileReportToFile("gastos_por_mes.jrxml"); 
//			JasperCompileManager.compileReportToFile("gastos_por_mes_subreport1.jrxml"); 
//
//			Connection conexao = new ConnectionFactory().getConnection();
//			Map<String, Object> params = new HashMap<String, Object>();
//			
//			//le e preenche o relatorio
//			JasperPrint jasperPrint = JasperFillManager.fillReport("gastos_por_mes.jasper", params , conexao);
//			
//			//processador do relatorio
//			JRExporter exporter = new JRPdfExporter();
//			//new JRHtmlExporter();
//			//new JRXlsExporter();
//			
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("gastos_por_mes.pdf"));
//			
//			exporter.exportReport();
//			
//			conexao.close();
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
