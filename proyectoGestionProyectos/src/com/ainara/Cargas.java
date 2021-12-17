package com.ainara;

import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cargas {

    public static final SessionFactory sesion = HibernateUtil.getSessionFactory();

    public ArrayList<ProveedoresEntity> listaProveedores() {
        Session session = sesion.openSession();
        Query q = session.createQuery("from ProveedoresEntity ");
        ArrayList<ProveedoresEntity> list = new ArrayList<>();
        List<ProveedoresEntity> lista = q.list();
        Iterator<ProveedoresEntity> iter = lista.iterator();
        while (iter.hasNext()) {
            ProveedoresEntity prov = iter.next();
            list.add(prov);
        }
        session.close();
        return list;
    }

    public ArrayList<PiezasEntity> listaPiezas() {
        Session session = sesion.openSession();
        Query q = session.createQuery("from PiezasEntity ");
        ArrayList<PiezasEntity> list = new ArrayList<>();
        List<PiezasEntity> lista = q.list();
        Iterator<PiezasEntity> iter = lista.iterator();
        while (iter.hasNext()) {
            PiezasEntity pieza = iter.next();
            list.add(pieza);
        }
        session.close();
        return list;
    }

    public ArrayList<ProyectosEntity> listaProyectos() {
        Session session = sesion.openSession();
        Query q = session.createQuery("from ProyectosEntity ");
        ArrayList<ProyectosEntity> list = new ArrayList<>();
        List<ProyectosEntity> lista = q.list();
        Iterator<ProyectosEntity> iter = lista.iterator();
        while (iter.hasNext()) {
            ProyectosEntity proyecto = iter.next();
            list.add(proyecto);
        }
        session.close();
        return list;
    }

    public ArrayList<GestionEntity> listaGestiones() {
        Session session = sesion.openSession();
        Query q = session.createQuery("from GestionEntity ");
        ArrayList<GestionEntity> list = new ArrayList<>();
        List<GestionEntity> lista = q.list();
        Iterator<GestionEntity> iter = lista.iterator();
        while (iter.hasNext()) {
            GestionEntity gestion = iter.next();
            list.add(gestion);
        }
        session.close();
        return list;
    }

    public void insertarProveedor(ProveedoresEntity proveedor) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(proveedor);

            try {
                tx.commit();
            } catch (ConstraintViolationException e) {
                System.out.println("PROVEEDOR DUPLICADO");
                System.out.printf("MENSAJE:%s%n", e.getMessage());
                System.out.printf("COD ERROR:%d%n", e.getErrorCode());
                System.out.printf("ERROR SQL:%s%n", e.getSQLException().getMessage());
            }

        } catch (TransientPropertyValueException e) {
            //System.out.println("EL DEPARTAMENTO NO EXISTE");
            System.out.printf("MENSAJE:%s%n", e.getMessage());
            System.out.printf("Propiedad:%s%n", e.getPropertyName());
        }
        session.close();
    }

    public void insertarPieza(PiezasEntity pieza) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(pieza);

            try {
                tx.commit();
            } catch (ConstraintViolationException e) {
                System.out.println("PIEZA DUPLICADA");
                System.out.printf("MENSAJE:%s%n", e.getMessage());
                System.out.printf("COD ERROR:%d%n", e.getErrorCode());
                System.out.printf("ERROR SQL:%s%n", e.getSQLException().getMessage());
            }

        } catch (TransientPropertyValueException e) {
            //System.out.println("EL DEPARTAMENTO NO EXISTE");
            System.out.printf("MENSAJE:%s%n", e.getMessage());
            System.out.printf("Propiedad:%s%n", e.getPropertyName());
        }
        session.close();
    }

    public void insertarProyecto(ProyectosEntity proyecto) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Inserto un Proyecto");

        try {
            session.save(proyecto);

            try {
                tx.commit();
            } catch (ConstraintViolationException e) {
                System.out.println("proyecto DUPLICADO");
                System.out.printf("MENSAJE:%s%n", e.getMessage());
                System.out.printf("COD ERROR:%d%n", e.getErrorCode());
                System.out.printf("ERROR SQL:%s%n", e.getSQLException().getMessage());
            }

        } catch (TransientPropertyValueException e) {
            // System.out.println("EL DEPARTAMENTO NO EXISTE");
            System.out.printf("MENSAJE:%s%n", e.getMessage());
            System.out.printf("Propiedad:%s%n", e.getPropertyName());
        }
        session.close();
    }

    public void insertarGestion(GestionEntity gestion) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Inserto una gestión.");

        try {
            session.save(gestion);

            try {
                tx.commit();
            } catch (ConstraintViolationException e) {
                System.out.println("GESTIÓN DUPLICADa");
                System.out.printf("MENSAJE:%s%n", e.getMessage());
                System.out.printf("COD ERROR:%d%n", e.getErrorCode());
                System.out.printf("ERROR SQL:%s%n", e.getSQLException().getMessage());
            }

        } catch (TransientPropertyValueException e) {
            //System.out.println("EL DEPARTAMENTO NO EXISTE");
            System.out.printf("MENSAJE:%s%n", e.getMessage());
            System.out.printf("Propiedad:%s%n", e.getPropertyName());
        }
        session.close();
    }

    public void modificarProveedor(ProveedoresEntity proveedor) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();


        try {
            proveedor = session.load(ProveedoresEntity.class, proveedor.getIdProv());

            if (proveedor == null) {
                System.out.println("El proveedor no existe");
                JOptionPane.showMessageDialog(null, "No existe el proveedor con ese código", "No existe", JOptionPane.WARNING_MESSAGE);
            } else {
                session.update(proveedor);
                tx.commit();
            }


        } catch (ObjectNotFoundException o) {
            System.out.println("El proveedor no existe");
        } catch (ConstraintViolationException c) {
            System.out.println("Error");
            //System.out.println("No se puede asignar un departamento que no existe");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
    }

    public void modificarPieza(PiezasEntity pieza) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();


        try {
            pieza = session.load(PiezasEntity.class, pieza.getIdPieza());
            if (pieza == null) {
                System.out.println("La pieza no existe");
                JOptionPane.showMessageDialog(null, "No existe la pieza con ese código", "No existe", JOptionPane.WARNING_MESSAGE);
            } else {
                session.update(pieza);
                tx.commit();
            }


        } catch (ObjectNotFoundException o) {
            System.out.println("La pieza no existe");
        } catch (ConstraintViolationException c) {
            System.out.println("Error");
            //System.out.println("No se puede asignar un departamento que no existe");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
    }

    public void modificarProyecto(ProyectosEntity proyecto) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();


        try {
            proyecto = session.load(ProyectosEntity.class, proyecto.getIdProyecto());
            if (proyecto == null) {
                System.out.println("El proyecto no existe");
                JOptionPane.showMessageDialog(null, "No existe el proyecto con ese código", "No existe", JOptionPane.WARNING_MESSAGE);
            } else {
                session.update(proyecto);
                tx.commit();
            }


        } catch (ObjectNotFoundException o) {
            System.out.println("El proyecto no existe");
        } catch (ConstraintViolationException c) {
            System.out.println("Error");
            //System.out.println("No se puede asignar un departamento que no existe");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
    }

    public void modificarGestion(GestionEntity gestion) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();


        try {

            session.update(gestion);
            tx.commit();


        } catch (ObjectNotFoundException o) {
            System.out.println("El proyecto no existe");
        } catch (ConstraintViolationException c) {
            System.out.println("Error");
            //System.out.println("No se puede asignar un departamento que no existe");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
    }

    public void eliminarProveedor(int id) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        ProveedoresEntity prov = session.load(ProveedoresEntity.class, id);

        try {
            session.delete(prov);
            tx.commit();
        } catch (ObjectNotFoundException o) {
            System.out.println("No existe el proveedor");
        } catch (ConstraintViolationException c) {
            System.out.println("No se puede eliminar, tiene gestiones asignadas");
        } catch (PersistenceException p) {
            System.out.println("Error, tiene gestiones, primero tienes que quitarlos de este proveedor");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
        session.close();
    }

    public void eliminarPieza(int id) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        PiezasEntity pieza = session.load(PiezasEntity.class, id);

        try {
            session.delete(pieza);
            tx.commit();
        } catch (ObjectNotFoundException o) {
            System.out.println("No existe la pieza");
        } catch (ConstraintViolationException c) {
            System.out.println("No se puede eliminar, tiene gestiones asignadas");
        } catch (PersistenceException p) {
            System.out.println("Error, tiene gestiones, primero tienes que quitarlos de esta pieza");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
        session.close();
    }

    public void eliminarProyecto(int id) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        ProyectosEntity pieza = session.load(ProyectosEntity.class, id);

        try {
            session.delete(pieza);
            tx.commit();
        } catch (ObjectNotFoundException o) {
            System.out.println("No existe el proyecto");
        } catch (ConstraintViolationException c) {
            System.out.println("No se puede eliminar, tiene gestiones asignadas");
        } catch (PersistenceException p) {
            System.out.println("Error, tiene gestiones, primero tienes que quitarlos de este proyecto");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
        session.close();
    }

    public void eliminarGestion(GestionEntity gestion) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.delete(gestion);
            tx.commit();
        } catch (ObjectNotFoundException o) {
            System.out.println("No existe el proveedor");
        } catch (ConstraintViolationException c) {
            System.out.println("No se puede eliminar, tiene gestiones asignadas");
        } catch (PersistenceException p) {
            System.out.println("Error, tiene gestiones, primero tienes que quitarlos de este proveedor");
        } catch (Exception e) {
            System.out.println("Error no controlado");
            e.printStackTrace();
        }
        session.close();

    }

    //Faltan las consultas de gestion
}
