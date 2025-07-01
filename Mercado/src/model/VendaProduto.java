package model;

public class VendaProduto {
			private String idVendaProduto;
			private String idVenda;
		    private String idProduto;
		    private String quantidade;
		    private String valorToral;
		    
		  //Metodo construtor carregado --
			public VendaProduto(String idVendaProduto, String idVenda, String idProduto, String quantidade, String valorToral) {
				super();
				this.idVendaProduto = idVendaProduto;
				this.idVenda = idVenda;
				this.idProduto = idProduto;
				this.quantidade = quantidade;
				this.valorToral = valorToral;
			}

			//Metodo construtor vazio --
			public VendaProduto() {
				super();
			}
			
			public String getIdVendaProduto() {
				return idVendaProduto;
			}


			public void setIdVendaProduto(String idVendaProduto) {
				this.idVendaProduto = idVendaProduto;
			}


			public String getIdVenda() {
				return idVenda;
			}


			public void setIdVenda(String idVenda) {
				this.idVenda = idVenda;
			}


			public String getIdProduto() {
				return idProduto;
			}


			public void setIdProduto(String idProduto) {
				this.idProduto = idProduto;
			}


			public String getQuantidade() {
				return quantidade;
			}


			public void setQuantidade(String quantidade) {
				this.quantidade = quantidade;
			}


			public String getValorToral() {
				return valorToral;
			}


			public void setValorToral(String valorToral) {
				this.valorToral = valorToral;
			}
		    
		    
	
	
	

}
