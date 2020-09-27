package com.mamalimomen.base.controller.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public final class PersistenceUnitManager {
    private static final Map<PersistenceUnit, EntityManagerFactory> mapper = new HashMap<>();

    private PersistenceUnitManager() {
    }

    public static synchronized EntityManager getEntityManager(PersistenceUnit pu) {
        EntityManagerFactory em = mapper.get(pu);

        if (em == null) {
            mapper.put(pu, Persistence.createEntityManagerFactory(pu.getUnit()));
            return mapper.get(pu).createEntityManager();
        } else return em.createEntityManager();
    }

    public static synchronized void closePersistenceUnits() {
        for (EntityManagerFactory em : mapper.values()) {
            em.close();
        }
    }
}
