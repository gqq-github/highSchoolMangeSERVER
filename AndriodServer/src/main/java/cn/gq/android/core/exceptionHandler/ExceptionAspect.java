package cn.gq.android.core.exceptionHandler;


import cn.gq.android.core.commexception.ForbiddenException;
import cn.gq.android.core.commexception.NotFoundException;
import cn.gq.android.web.utils.ResponseUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * title: 全局异常处理切面
 * Description: 利用 @ControllerAdvice + @ExceptionHandler组合处理Controller层RuntimeException异常
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        logger.error("could_not_read_json..." + e.getMessage());

        return ResponseUtil.failure(HttpStatus.NOT_FOUND.value(), "JSON 格式错误：",createReturnBody(HttpStatus.NOT_FOUND.value(),"JSON 格式错误："+e.getMessage()) );
    }

    /**
     * 400 - Bad Request
     */
   /* @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
        logger.error("parameter_validation_exception..."+e.getMessage());
        return ResponseUtil.failure(HttpStatus.NOT_FOUND.value(),"parameter_validation_exception",null);
    }*/

    /**
     * 处理未发现的异常,比如用户名不存在等, 抛出http status 404
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(Exception e) {
        logger.error("request_not_fond..." + e.getMessage());

        return ResponseUtil.failure(HttpStatus.NOT_FOUND.value(), "未找到资源！", createReturnBody(HttpStatus.NOT_FOUND.value(),"未找到资源！"+e.getMessage()));
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        logger.error("request_method_not_supported..." + e.getMessage());
        return ResponseUtil.failure(HttpStatus.METHOD_NOT_ALLOWED.value(), "不被支持的访问方法！", createReturnBody(HttpStatus.METHOD_NOT_ALLOWED.value(),"不被支持的访问方法！"));
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("content_type_not_supported..." + e.getMessage());
        return ResponseUtil.failure(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "不支持的媒体类型！", createReturnBody(HttpStatus.METHOD_NOT_ALLOWED.value(),"不支持的媒体类型！"));
    }

    /**
     * 403 - Forbidden
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity handleForbiddenException(Exception e) {
        logger.error("request_forbidden..." + e.getMessage());
        return ResponseUtil.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), createReturnBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
    }

    /**
     * shiro认证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity handleUnauthenticatedException(Exception e) {
        logger.error("Internal Server Error..." + e.getMessage());
        return ResponseUtil.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "身份认证失败，请重新登录！",createReturnBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),"身份认证失败，请重新登录！"));
    }

 //封装返回的时候的信息提示
    public static JSONObject createReturnBody(int code,String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject ;
    }
    /**
     * shiro 授权异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleUnauthorizedException(Exception e) {
        logger.error("Internal Server Error..." + e.getMessage());

        return ResponseUtil.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "无权访问！", createReturnBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),"无权访问！"));
    }

    /**
     * 500 - shiro权限异常处理
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity handleAuthorizationException(Exception e) {
        logger.error("Internal Server Error..." + e.getMessage());
        return ResponseUtil.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "身份认证失败！", createReturnBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),"身份认证失败！"));
    }

    /**
     * 500 - Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.error("Internal Server Error..." + e.getMessage());
        return ResponseUtil.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "程序内部错误！", createReturnBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),"程序内部错误！"));
    }


    /**
     * 对方法参数校验异常处理方法(前端提交的方式为json格式出现异常时会被该异常类处理)
     * json格式提交时，spring会采用json数据的数据转换器进行处理（进行参数校验时错误是抛出MethodArgumentNotValidException异常）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerArgumentNotValidException(MethodArgumentNotValidException exception) {
        return handlerNotValidException(exception);

    }


    /**
     * 对方法参数校验异常处理方法(仅对于表单提交有效，对于以json格式提交将会失效)
     * 如果是表单类型的提交，则spring会采用表单数据的处理类进行处理（进行参数校验错误时会抛出BindException异常）
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handlerBindException(BindException exception) {
        return handlerNotValidException(exception);
    }

    public ResponseEntity<Map<String, Object>> handlerNotValidException(Exception e) {
        logger.error("parameter_validation_exception..." + e.getMessage());
        BindingResult result;
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            result = exception.getBindingResult();
        } else {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            result = exception.getBindingResult();
        }

        Map<String, Object> maps;
        List<String> lists;
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            maps = new HashMap<>(fieldErrors.size());
            lists = new ArrayList<>(fieldErrors.size());
            fieldErrors.forEach(error -> {
                maps.put(error.getField(), error.getDefaultMessage());
                lists.add(error.getDefaultMessage());
            });
        } else {
            maps = Collections.EMPTY_MAP;
            lists = Collections.EMPTY_LIST;
        }

        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(maps);
        String failure = JSON.toJSONString(lists);
        System.out.println("=====" + failure);
        return ResponseUtil.failure(failure);

    }
}

