package zhangjie.validator.basic.spring;

/**
 * @ClassName PersonException
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 18:27
 **/
public class PersonException extends RuntimeException {
    public PersonException(){

    }

    public PersonException(String message){
        super(message);
    }

    public PersonException(Throwable cause){
        super(cause);
    }

    public PersonException(String message,Throwable cause){
        super(message,cause);
    }

}
