package br.com.netflix.inatel.projeto.config.validation;

public class ErroFormAvaliacaoDTO {

	private String campo;
	private String erro;
	
	public ErroFormAvaliacaoDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
	
}
