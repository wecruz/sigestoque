package br.com.sigest.modelo.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.sigest.modelo.Venda;
import br.com.sigest.modelo.VendaProduto;


public class PedidoDTO {

	private Venda venda = new Venda();
	
	private List<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setListaVendaProduto(List<VendaProduto> listaVendaProduto) {
		this.listaVendaProduto = listaVendaProduto;
	}

	public List<VendaProduto> getListaVendaProduto() {
		return listaVendaProduto;
	}

	
	
	
}
