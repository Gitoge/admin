package com.sn.dtai.admin.service.dto;

import java.io.Serializable;

/**
 * A DTO for the PagesActionsDto.
 */
public class PagesActionsDto implements Serializable {

    public PagesActionsDto() {
        
    }

    public PagesActionsDto(Long id, Long pageId, Long actionId, String libellePage, String libelleAction) {
        this.id = id;
        this.pageId = pageId;
        this.actionId = actionId;
        this.libellePage = libellePage;
        this.libelleAction = libelleAction;
    }

    

    private Long id;

    private Long pageId;

    private Long actionId;

    private String libellePage;

    private String libelleAction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getLibellePage() {
        return libellePage;
    }

    public void setLibellePage(String libellePage) {
        this.libellePage = libellePage;
    }

    public String getLibelleAction() {
        return libelleAction;
    }

    public void setLibelleAction(String libelleAction) {
        this.libelleAction = libelleAction;
    }


    

    

    
}
