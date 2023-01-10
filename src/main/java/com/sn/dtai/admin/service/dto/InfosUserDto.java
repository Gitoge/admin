package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class InfosUserDto implements Serializable {

    private PersonneDTO personne;

    private List<RolesDTO> roles;

    private List<PagesDTO> pages;

    private Set<ModulesDTO> modules;

    private List<ActionsDTO> actions;

    public PersonneDTO getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDTO personne) {
        this.personne = personne;
    }

    public List<RolesDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesDTO> roles) {
        this.roles = roles;
    }

    public List<PagesDTO> getPages() {
        return pages;
    }

    public void setPages(List<PagesDTO> pages) {
        this.pages = pages;
    }

    public Set<ModulesDTO> getModules() {
        return modules;
    }

    public void setModules(Set<ModulesDTO> modules) {
        this.modules = modules;
    }

    public List<ActionsDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionsDTO> actions) {
        this.actions = actions;
    }
}
