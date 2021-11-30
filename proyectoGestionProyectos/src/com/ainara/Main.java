package com.ainara;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.hibernate.Version;

public class Main {

    public static final SessionFactory sesion = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        //Supuestamente con estas lineas elimina los mensajes de info de la consola
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(Level.OFF);


        //Mostrar lista proveedores
        /*Session session = sesion.openSession();
        Query q = session.createQuery("from ProveedoresEntity ");
        List<ProveedoresEntity> lista = q.list();
        Iterator<ProveedoresEntity> iter = lista.iterator();
        System.out.printf("Número de departamentos: %d%n", lista.size());
        while (iter.hasNext()) {
            ProveedoresEntity prov = iter.next();
            System.out.printf("%s, %s%n", prov.getIdProv(), prov.getNombre());
        }
        session.close();*/

        for (ProveedoresEntity prov: Cargas.listaProveedores()) {
            System.out.println(prov.getNombre());
        }
    }
}
