package br.com.sigest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.annotations.Out;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sigest.modelo.UploadedFile;

public class UploadFileUtil {

	@Out(required = false)
	public static final int TAMANHO_MAX_ARQUIVO = 1048576;

	private List<UploadedFile> list = new ArrayList<UploadedFile>();

	private File diretorioRaiz;

	private UploadedFile uploadedFile = new UploadedFile();

	private FileInputStream arquivoLeitura;

	private FileOutputStream arquivoSaida;

	public UploadedFile salvarArquivo(UploadedFile file, File diretorio)
			throws IOException {
		uploadedFile = new UploadedFile();
		OutputStream fos = null;
		File arquivoDeSaida = null;
		try {
			uploadedFile.setDados(file.getDados());
			uploadedFile.setMime(file.getMime());
			uploadedFile.setTamanho(file.getDados().length);

			if (diretorio == null) {
				diretorio = this.criarDiretorio();
			}

			arquivoDeSaida = new File(diretorio, file.getNome());
			fos = new FileOutputStream(arquivoDeSaida);

		} catch (IOException ioe) {
			// log.info(ioe);
		} finally {
			fos.write(file.getDados());
			fos.close();
			uploadedFile.setCaminho(arquivoDeSaida.toString());
		}
		return uploadedFile;
	}

	public void salvarArquivo(UploadedFile uploadedFile)
			throws FileNotFoundException, IOException {
		if (uploadedFile == null) {
			throw new NullPointerException(
					"Arquivo a ser salvo não pode ser nulo.");
		}
		File diretorio = criarDiretorio();
		UploadedFile uploadedFileRes;
		if (uploadedFile.isTempFile()) {
			uploadedFileRes = this.salvarArquivo(uploadedFile.getNome(),
					uploadedFile.getMime(), uploadedFile.getTempFile(),
					diretorio);
		} else {
			uploadedFileRes = this.salvarArquivo(uploadedFile, diretorio);
		}
		uploadedFile.setCaminho(uploadedFileRes.getCaminho());
	}

	private UploadedFile salvarArquivo(final String nome, final String mime,
			final File arquivoTemp, File diretorio)
			throws FileNotFoundException, IOException {

		FileChannel in = null;
		FileChannel out = null;

		try {

			UploadedFile file = new UploadedFile();
			file.setMime(mime);
			file.setTamanho(arquivoTemp.length());

			if (diretorio == null) {
				diretorio = criarDiretorio();
			}

			File outFile = new File(diretorio, nome);
			arquivoLeitura = new FileInputStream(arquivoTemp);
			in = arquivoLeitura.getChannel();
			arquivoSaida = new FileOutputStream(outFile);
			out = arquivoSaida.getChannel();
			in.transferTo(0, arquivoTemp.length(), out);
			file.setCaminho(outFile.getAbsolutePath());
			return file;

		} finally {
			if (arquivoLeitura != null) {
				arquivoLeitura.close();
			}
			if (arquivoSaida != null) {
				arquivoSaida.close();
			}
		}
	}
	
	

	private File criarDiretorio() {
		try {
			this.diretorioRaiz = new File("C:\\arquivos\\anexos");
		} catch (Exception e) {
			// log.info(e.getStackTrace() +
			// "Propriedade: 'siproquim2.upload.anexos'");
		}
		UUID uuid = UUID.randomUUID();
		File arquivo = new File(diretorioRaiz, uuid.toString());

		// if (arquivo.exists()) {
		// return criarDiretorio();
		// } else {
		// arquivo.mkdirs();
		// return arquivo;
		// }
		if (arquivo != null) {
			arquivo.mkdirs();
		}
		return arquivo;
	}
	
	public String download(String caminho, String nome, String contentType) throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType(contentType);
		response.addHeader("Content-disposition", "attachment; filename=\"" + nome + "\"");

		InputStream fis = null;
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			File file = new File(caminho);
			fis = new FileInputStream(file);
			byte[] outputByte = new byte[1024];
			while (fis.read(outputByte, 0, 1024) != -1) {
				out.write(outputByte, 0, 1024);
			}
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException ioe) {
//			log.info(ioe.getStackTrace());
		} finally {
			fis.close();
			out.close();
		}
		return null;
	}
	
	public void salvarEmDisco(byte[] arquivo, String nome) {
		FileOutputStream fis = null;
		try {
			fis = new FileOutputStream(nome);
			fis.write(arquivo);
			fis.flush();
			fis.close();
		} catch (FileNotFoundException e1) {
			java.util.logging.Logger.getLogger("Arquivo não encontrado " + e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e) {
			java.util.logging.Logger.getLogger("Falha ao salvar o arquivo " + e.getMessage());
		}
	}

	public UploadItem listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		return item;
	}

	public String geraMime(String mime) {
		String name = mime;
		String mimeTemp = null;
		int extDot = name.lastIndexOf('.');
		if (extDot > 0) {
			String extension = name.substring(extDot + 1);
			if ("pdf".equals(extension)) {
				mimeTemp = "pdf";
			} else if ("PDF".equals(extension)) {
				mimeTemp = "pdf";
			} else if ("JPEG".equals(extension)) {
				mimeTemp = "jpeg";
			} else if ("jpeg".equals(extension)) {
				mimeTemp = "jpeg";
			} else if ("jpg".equals(extension)) {
				mimeTemp = "jpg";
			} else if ("JPG".equals(extension)) {
				mimeTemp = "jpg";
			} else if ("gif".equals(extension)) {
				mimeTemp = "gif";
			} else if ("GIF".equals(extension)) {
				mimeTemp = "gif";
			} else if ("doc".equals(extension)) {
				mimeTemp = "doc";
			} else if ("DOC".equals(extension)) {
				mimeTemp = "doc";
			} else if ("docx".equals(extension)) {
				mimeTemp = "docx";
			} else if ("DOCX".equals(extension)) {
				mimeTemp = "docx";
			} else {
				mimeTemp = "unknown";
			}
		}
		return mimeTemp;
	}
	
	public UploadItem listener(UploadEvent event, UploadedFile file) {
		UploadItem item = event.getUploadItem();
		file.setTempFile(item.getFile());
		file.setTamanho(item.getData().length);
		file.setCaminho(item.getFileName());
		file.setDados(item.getData());
		return item;
	}

	public List<UploadedFile> getList() {
		return list;
	}

	public void setList(List<UploadedFile> list) {
		this.list = list;
	}

	public File getDiretorioRaiz() {
		return diretorioRaiz;
	}

	public void setDiretorioRaiz(File diretorioRaiz) {
		this.diretorioRaiz = diretorioRaiz;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public FileInputStream getArquivoLeitura() {
		return arquivoLeitura;
	}

	public void setArquivoLeitura(FileInputStream arquivoLeitura) {
		this.arquivoLeitura = arquivoLeitura;
	}

	public FileOutputStream getArquivoSaida() {
		return arquivoSaida;
	}

	public void setArquivoSaida(FileOutputStream arquivoSaida) {
		this.arquivoSaida = arquivoSaida;
	}

	
}
