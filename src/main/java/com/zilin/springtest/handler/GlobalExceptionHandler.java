package com.zilin.springtest.handler;

import com.zilin.springtest.entity.ErrorMsg;
import com.zilin.springtest.jsonre.InputError;
import com.zilin.springtest.util.ExceptionAll;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public InputError MethodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        InputError a = new InputError(new ErrorMsg(exception.getBindingResult().getFieldError().getField(),exception.getBindingResult().getFieldError().getDefaultMessage()));
        a.setCode(ExceptionAll.EMPTY_BLANK.getCode());
        a.setMsg(ExceptionAll.EMPTY_BLANK.getResult());
        log.info(a.toString());
        return a;
    }
}
