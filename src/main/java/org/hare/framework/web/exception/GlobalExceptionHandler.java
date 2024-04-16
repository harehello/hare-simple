package org.hare.framework.web.exception;

import org.hare.framework.exception.BaseException;
import org.hare.framework.web.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;


/**
 * 异常处理器
 *
 * @author hare
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BaseException.class)
	public R handleBaseException(BaseException e){
		logger.error(e.getMessage());
		return R.failed().code(e.getCode()).msg(e.getMessage());
	}

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public R handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
														  HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		logger.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
		return R.failed(e.getMessage());
	}

	/**
	 * 文件超大
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public R handMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
		logger.error(e.getMessage());
		return R.failed().msg("请求被拒绝，文件大小超过了最大值");
	}

	/**
	 * 数据完整性异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public R handDataIntegrityViolationException(DataIntegrityViolationException e){
		logger.error("无法执行的SQL语句，不可破坏数据完整性", e);
		return R.failed().msg("请检查数据关联，以保证数据完整性");
	}

	/**
	 * bean参数验证.
	 *
	 * @param e RuntimeException
	 * @return String
	 */
	@ExceptionHandler(BindException.class)
	public R validExceptionHandler(BindException e) {
		logger.error(e.getMessage(), e);
		return R.failed().msg(e.getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(",")));
	}

	/**
	 * bean参数验证 带requestbody的方式.
	 *
	 * @param e RuntimeException
	 * @return String
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

		String msg = e.getBindingResult().getFieldError().getDefaultMessage();
		logger.error(msg);
		return R.failed().msg(msg);
	}


	/**
	 * security密文异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public R handleBadCredentialsException(BadCredentialsException e){
		logger.error(e.getMessage(), e);
		return R.failed().msg(e.getMessage());
	}

	/**
	 * security授权异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public void handleAccessDeniedException(AccessDeniedException e){
		throw e;
	}
	/**
	 * security认证异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	public void handleAuthenticationException(AuthenticationException e){
		throw e;
	}

	/**
	 * 异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		e.printStackTrace();
		return R.failed();
	}
}
