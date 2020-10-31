package com.example.order.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionController {

	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<ExceptionBody> handleInternalServerException(InternalServerException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("Please try again later");

		return new ResponseEntity<>(
				new ExceptionBody(
						new Timestamp(System.currentTimeMillis()), 
						500,
						"HTTP: Internal server error", 
						exception.getMsg()
				), 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ExceptionBody> handleAlreadyExistsException(AlreadyExistsException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("Request is already fullfiled");
//		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
//		exceptionBodies.add();

		return new ResponseEntity<>(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 208, "HTTP: Already Reported",
				exception.getMsg()), HttpStatus.ALREADY_REPORTED);
	}

	@ExceptionHandler(PayloadException.class)
	public ResponseEntity<ExceptionBody> handlePayloadException(PayloadException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("In appropriate payload!");
//		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
//		exceptionBodies.add();

		return new ResponseEntity<>(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 400, "HTTP: Bad Request",
				exception.getMsg()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionBody> handleNotFoundException(NotFoundException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("Not Found Exception");

//		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
//		exceptionBodies.add();

		return new ResponseEntity<>(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 404, "HTTP: Page Not Found",
				exception.getMsg()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoContentFound.class)
	public ResponseEntity<ExceptionBody> handleNoContentFound(NoContentFound exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("No Content Found Exception");

//		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
//		exceptionBodies.add();

		return new ResponseEntity<>(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 204, "HTTP: No Content Found!",
				exception.getMsg()), HttpStatus.NO_CONTENT);
	}

}