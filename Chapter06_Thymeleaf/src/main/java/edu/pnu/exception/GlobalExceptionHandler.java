package edu.pnu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BoardException.class)
	public String handleCustomException(BoardException exception, Model model) {
		model.addAttribute("exception",exception);
		return "/errors/boardError";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String handleCustomException(IllegalArgumentException exception, Model model) {
//		model.addAttribute("message",exception.getMessage());
//		return "/errors/globalError";
//	}
	
	@ExceptionHandler(Exception.class)
	public String hadleException(Exception exception,Model model) {
		model.addAttribute("message",exception.getMessage());
		model.addAttribute("stackTrace", exception.getStackTrace());
		return "/errors/globalError";
	}
}
