package zhangjie.api.design.advices;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import zhangjie.api.design.enums.ResultCode;
import zhangjie.api.design.response.Result;

import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 统一异常处理
 * @author: zhangjie
 * @Date: 2020/10/15 17:45
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(Exception e){
        if(e instanceof ConstraintViolationException){//参数注解
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            String message = StringUtils.collectionToCommaDelimitedString(
                    constraintViolationException.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList())
            );
            return Result.failure(ResultCode.PARAM_IS_INVALID,message);
        }else if(e instanceof BindException){//实体注解
            BindException bindException = (BindException) e;
            String message = StringUtils.collectionToCommaDelimitedString(
                    bindException.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())
            );
            return Result.failure(ResultCode.PARAM_IS_INVALID,message);
        }
        return Result.failure(ResultCode.SYS_ERROR);
    }
}
