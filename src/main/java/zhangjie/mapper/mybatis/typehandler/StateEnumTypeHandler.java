package zhangjie.mapper.mybatis.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;

/**
 * @Author zhangjie
 * @Date 2020/9/7 21:52
 **/
public class StateEnumTypeHandler extends EnumOrdinalTypeHandler<StateEnum> {
    public StateEnumTypeHandler(Class<StateEnum> type) {
        super(type);
    }
}
