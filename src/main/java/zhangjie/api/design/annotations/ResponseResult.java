package zhangjie.api.design.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用来标记方法的返回值，是否需要包装
 * @author zhangjie
 */
@Retention(RUNTIME)
@Target({TYPE,METHOD})
@Documented
public @interface ResponseResult {
}
