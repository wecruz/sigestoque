package br.com.sigest.util;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.document.ByteArrayDocumentData;
import org.jboss.seam.document.DocumentData;
import org.jboss.seam.document.DocumentData.DocumentType;
import org.jboss.seam.document.DocumentStore;


@Name("relatorioUtil")
@AutoCreate
public class RelatorioUtil {
	
	
	private static final String DIR_RELATORIOS = "/relatorios/";

//	private static final String DIR_IMAGEM = "/imagens/";

	private static final DocumentType PDF = new DocumentType("pdf",
			"application/pdf");

	@In(value = "org.jboss.seam.document.documentStore", create = true)
	private DocumentStore documentStore;



	/**
	 * Imprimi o relatorio PDF informado.
	 * 
	 * @param fileName
	 *            Nome do arquivo jasper
	 * @param params
	 *            Paremetros a ser passado para a geracao do PDF
	 * @param colecao
	 *            Colecao com as informacoes do relatorio
	 * 
	 * @return Endereco do PDF gerado
	 * 
	 * @throws Exception
	 *             Falha na geracao do PDF
	 */
	public String imprimir(final String fileName,
			final Map<String, Object> params, final Collection<?> colecao)
			throws Exception {
		final ByteArrayOutputStream output = gerarPdf(fileName, params, colecao);
		final String reportId;
		if (documentStore == null) {
			documentStore = new DocumentStore();
		}
		reportId = documentStore.newId();
		final DocumentData data = new ByteArrayDocumentData(
				"report" + reportId, PDF, output.toByteArray());
		documentStore.saveData(reportId, data);
		return "/report.pdf?docId=" + reportId;
	}
	/**
	 * Metodo para geração do arquivo PDF
	 * 
	 * @param fileName
	 * @param params
	 * @param colecao
	 * @return
	 * @throws JRException
	 */
	public ByteArrayOutputStream gerarPdf(final String fileName,
			final Map<String, Object> params, final Collection<?> colecao)
			throws JRException {
		final ServletContext context = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		final String arquivo = context.getRealPath(DIR_RELATORIOS + fileName
				+ ".jasper");
		final JasperPrint jasperPrint = JasperFillManager.fillReport(arquivo,
				params, new JRBeanCollectionDataSource(colecao));
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, output);
		return output;
	}
	
	

}
