package br.borges.domain;

public class Venda {
	
	 private Long id;	
	 private String codigo;
	 private Long cliente_id;
	 private Long produto_id;
	 private Integer quantidade;
	 private Integer total;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	public Long getProduto_id() {
		return produto_id;
	}
	public void setProduto_id(Long produto_id) {
		this.produto_id = produto_id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	 
	 

}
