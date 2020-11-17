package zhangjie.statemachine.config;

/**
 * 表单处理状态
 * @author zhangjie
 */

public enum ComplexFormStates {
    /** 空白名单 */
    BLANK_FORM,
    /** 填写完表单 */
    FULL_FORM,
    /** 表单校验判断 */
    CHECK_CHOICE,
    /** 待提交表单 */
    CONFIRM_FORM,
    /** 成功表单 */
    SUCCESS_FORM,
    /** 待处理表单 */
    DEAL_FORM,
    /** 表单处理校验 */
    DEAL_CHOICE,
    /** 失败表单 */
    FAILED_FORM
}
