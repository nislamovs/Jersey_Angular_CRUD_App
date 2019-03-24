package com.jerseyexample.app.configuration.audit;

import com.jerseyexample.app.model.audit.AuditRevisionEntity;
import org.hibernate.envers.RevisionListener;

public class AuditRevisionListenerConfiguration implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;
        audit.setUsername("admin");
    }
}
