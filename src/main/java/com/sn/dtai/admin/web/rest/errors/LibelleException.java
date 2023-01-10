package com.sn.dtai.admin.web.rest.errors;

public class LibelleException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LibelleException() {
        super(ErrorConstants.LIBELLE_ALREADY_USED_TYPE, "Libelle exite dejà!", "entityName", "libelleexists");
    }
}
