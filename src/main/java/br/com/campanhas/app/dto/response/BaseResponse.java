package br.com.campanhas.app.dto.response;

import lombok.Data;

@Data
public class BaseResponse {
	
	private int statusCode;
	private String message;
}
