package br.com.sigest.modelo;

import java.io.File;
import java.io.Serializable;

public class UploadedFile implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The nome. */
	private String nome;

	/** The mime. */
	private String mime;

	/** The tamanho. */
	private long tamanho;

	/** The dados. */
	private byte[] dados;

	/** The caminho. */
	private String caminho;

	/** The temp file. */
	private File tempFile;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public File getTempFile() {
		return tempFile;
	}

	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}

	public boolean isTempFile() {
		return getTempFile() != null;
	}

	{
	}
}
