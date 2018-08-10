package com.example.demo.utils;

import org.hibernate.Hibernate;

public class HibernateUtils {

    public static Object initialize(Object entity) {
        if (!Hibernate.isInitialized(entity)) {
            Hibernate.initialize(entity);
        }

        return entity;
    }

}
