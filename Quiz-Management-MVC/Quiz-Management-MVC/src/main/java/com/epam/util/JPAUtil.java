package com.epam.util;


import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class JPAUtil {
    private static EntityManagerFactory factory ;
   private JPAUtil(){
    }

    public static EntityManagerFactory getInstance()
    {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory("quiz-demo");
        return factory;
    }

}
