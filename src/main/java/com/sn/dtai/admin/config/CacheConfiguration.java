package com.sn.dtai.admin.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.sn.dtai.admin.domain.Applications.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Applications.class.getName() + ".modules");
            createCache(cm, com.sn.dtai.admin.domain.Modules.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Modules.class.getName() + ".profils");
            createCache(cm, com.sn.dtai.admin.domain.Profils.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Profils.class.getName() + ".roles");
            createCache(cm, com.sn.dtai.admin.domain.Profils.class.getName() + ".modules");
            createCache(cm, com.sn.dtai.admin.domain.Roles.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Roles.class.getName() + ".pages");
            createCache(cm, com.sn.dtai.admin.domain.Roles.class.getName() + ".profils");
            createCache(cm, com.sn.dtai.admin.domain.Pages.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Pages.class.getName() + ".actions");
            createCache(cm, com.sn.dtai.admin.domain.Pages.class.getName() + ".roles");
            createCache(cm, com.sn.dtai.admin.domain.Actions.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Actions.class.getName() + ".pages");
            createCache(cm, com.sn.dtai.admin.domain.Personne.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.StructureAdmin.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.UserExtended.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeEtablissement.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeEtablissement.class.getName() + ".etabs");
            createCache(cm, com.sn.dtai.admin.domain.TypeLocalite.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeLocalite.class.getName() + ".localites");
            createCache(cm, com.sn.dtai.admin.domain.Localite.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Localite.class.getName() + ".etabs");
            createCache(cm, com.sn.dtai.admin.domain.Etablissement.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Etablissement.class.getName() + ".directions");
            createCache(cm, com.sn.dtai.admin.domain.Direction.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Direction.class.getName() + ".services");
            createCache(cm, com.sn.dtai.admin.domain.Services.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TableTypeValeur.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TableTypeValeur.class.getName() + ".tablevaleurs");
            createCache(cm, com.sn.dtai.admin.domain.TableValeur.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeDestinataires.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeDestinataires.class.getName() + ".destinataires");
            createCache(cm, com.sn.dtai.admin.domain.Destinataires.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeReglement.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.TypeReglement.class.getName() + ".reglements");
            createCache(cm, com.sn.dtai.admin.domain.Reglement.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Cadre.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Hierarchie.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Echelon.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Classe.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Corps.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Grade.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Emplois.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Indices.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.Positions.class.getName());
            createCache(cm, com.sn.dtai.admin.domain.PageAction.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
