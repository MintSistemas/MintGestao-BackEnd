package com.mintgestao.Infrastructure.Tenant;

import org.checkerframework.checker.initialization.qual.Initialized;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.checkerframework.checker.nullness.qual.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TenantResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

    private Integer currentTenant = -1;

    public void setCurrentTenant(Integer tenantId) {
        currentTenant = tenantId;
    }

    public void clear() {
        currentTenant = -1;
    }

    @Override
    public Integer resolveCurrentTenantIdentifier() {
        return currentTenant;
    }

    @Override
    public @UnknownKeyFor @Initialized boolean validateExistingCurrentSessions() {
        return false;
    }

    @Override
    public @UnknownKeyFor @Initialized boolean isRoot(Object tenantId) {
        return CurrentTenantIdentifierResolver.super.isRoot(tenantId) || tenantId.equals(-1);
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }

}