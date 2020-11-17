package org.springframework.statemachine.support;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;

/**
 * @ClassName MyStateMachineUtils
 * @Description: 这里的包名是org.springframework.statemachine.support，
 *                --> 因为setCurrentState是default的scope，不放在同一个包里无法直接操作
 * @author: zhangjie
 * @Date: 2020/11/16 20:09
 **/
public class MyStateMachineUtils extends StateMachineUtils {

    /**
     * 设置状态机至指定状态
     * @param stateMachine
     * @param state
     * @param <S>
     * @param <E>
     */
    public static <S,E> void setCurrentState(StateMachine<S,E> stateMachine,S state){
        if(stateMachine instanceof AbstractStateMachine){
            setCurrentState((AbstractStateMachine<S, E>)stateMachine,state);
            System.out.println("StateMachine Current:" + stateMachine);
        }else{
            throw new IllegalArgumentException("Provided StateMachine is not a valid type");
        }
    }

    public static <S,E> void setCurrentState(AbstractStateMachine<S,E> stateMachine,S state){
        stateMachine.setCurrentState(findState(stateMachine, state), null, null, false, stateMachine);
    }

    /**
     * 从stateMachine实例中获取目标state
     */
    private static <S, E> State<S, E> findState(AbstractStateMachine<S, E> stateMachine, S stateId) {
        for (State<S, E> state : stateMachine.getStates()) {
            if (state.getId() == stateId) {
                return state;
            }
        }
        throw new IllegalArgumentException("Specified State ID is not valid");
    }

}
