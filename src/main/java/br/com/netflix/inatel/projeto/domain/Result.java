package br.com.netflix.inatel.projeto.domain;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> {
	
	private List<T> Result;

	public Result() {
	}

	public Result(List<T> Result) {
		this.Result = Result;
	}

	public List<T> getResult() {
		return Result;
	}

	public void setResult(List<T> result) {
		Result = result;
	}

	

}
