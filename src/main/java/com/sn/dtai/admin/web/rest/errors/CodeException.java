package com.sn.dtai.admin.web.rest.errors;

public class CodeException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CodeException() {
        super(ErrorConstants.CODE_ALREADY_USED_TYPE, " Code existe déjà ! ", "entityName", "Code existe déjà !");
    }
}
