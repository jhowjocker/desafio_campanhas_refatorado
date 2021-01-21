package br.com.campanhas.app.exceptions.handle;

import br.com.campanhas.app.dto.response.BaseResponse;
import br.com.campanhas.app.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> defaultErrorHandler(HttpServletRequest req, Exception e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body( baseResponse );
    }

    @ExceptionHandler(CampanhaDuplicadaException.class)
    public ResponseEntity<BaseResponse> handle(HttpServletRequest req, CampanhaDuplicadaException e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body(baseResponse);
    }

    @ExceptionHandler(NomeTimeNotExistException.class)
    public ResponseEntity<BaseResponse> handle(HttpServletRequest req, NomeTimeNotExistException e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body(baseResponse);
    }

    @ExceptionHandler(TimeDiferenteException.class)
    public ResponseEntity<BaseResponse> handle(HttpServletRequest req, TimeDiferenteException e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body(baseResponse);
    }

    @ExceptionHandler(AssociadoNotExistException.class)
    public ResponseEntity<BaseResponse> handle(HttpServletRequest req, AssociadoNotExistException e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body(baseResponse);
    }

    @ExceptionHandler(CampanhaNotExistException.class)
    public ResponseEntity<BaseResponse> handle(HttpServletRequest req, CampanhaNotExistException e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body(baseResponse);
    }

    @ExceptionHandler(ClubeDuplicadoException.class)
    public ResponseEntity<BaseResponse> handle(HttpServletRequest req, ClubeDuplicadoException e) {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        baseResponse.setStatusCode(httpStatus.value());
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(httpStatus).body(baseResponse);
    }
}
