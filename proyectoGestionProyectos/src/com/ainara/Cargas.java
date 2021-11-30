package com.ainara;

import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cargas {

    public static final SessionFactory sesion = HibernateUtil.getSessionFactory();

    public static ArrayList<ProveedoresEntity> listaProveedores() {
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

    public static ArrayList<PiezasEntity> listaPiezas() {
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

    public static ArrayList<ProyectosEntity> listaProyectos() {
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

    public static ArrayList<GestionEntity> listaGestiones() {
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

    public static void insertarProveedor(int id, String nombre, String apellido, String dir) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Inserto un Proveedor.");

        ProveedoresEntity prov = new ProveedoresEntity();
        prov.setIdProv(id);
        prov.setNombre(nombre);
        prov.setApellidos(apellido);
        prov.setDir(dir);


        try {
            session.save(prov);

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

    public static void insertarPieza(int id, String nombre, String descrip, double precio) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Inserto un EMPLEADO EN EL DEPARTAMENTO 10.");

        PiezasEntity pieza = new PiezasEntity();
        pieza.setIdPieza(id);
        pieza.setNombre(nombre);
        pieza.setDescripcion(descrip);
        pieza.setPrecio(precio);

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

    public static void insertarProyecto(int id, String nombre, String ciudad) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Inserto un Proyecto");

        ProyectosEntity proyecto = new ProyectosEntity();
        proyecto.setIdProyecto(id);
        proyecto.setNombre(nombre);
        proyecto.setCiudad(ciudad);

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

    public static void insertarGestion(int id, ProveedoresEntity prov, PiezasEntity piez, ProyectosEntity proy, double cant) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Inserto un EMPLEADO EN EL DEPARTAMENTO 10.");

        GestionEntity gest = new GestionEntity();
        gest.setIdGestion(id);
        gest.setProveedoresByCodProv(prov);
        gest.setPiezasByCodPieza(piez);
        gest.setProyectosByCodProyecto(proy);
        gest.setCantidad(cant);

        try {
            session.save(gest);

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

    public static void modificarProveedor(int id, String nombre, String apellidos, String dir) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        ProveedoresEntity prov = new ProveedoresEntity();

        try {
            prov = session.load(ProveedoresEntity.class, id);

            if (prov == null) {
                System.out.println("El proveedor no existe");
                //JOptioPanel
            } else {
                prov.setNombre(nombre);
                prov.setApellidos(apellidos);
                prov.setDir(dir);

                session.update(prov);
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

    public static void modificarPieza(int id, String nombre, String desc, double precio) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        PiezasEntity pieza = new PiezasEntity();

        try {
            pieza = session.load(PiezasEntity.class, id);
            if (pieza == null) {
                System.out.println("La pieza no existe");
                //JOptioPanel
            } else {
                pieza.setNombre(nombre);
                pieza.setDescripcion(desc);
                pieza.setPrecio(precio);

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

    public static void modificarProyecto(int id, String nombre, String ciudad) {
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        ProyectosEntity proyecto = new ProyectosEntity();

        try {
            proyecto = session.load(ProyectosEntity.class, id);
            if (proyecto == null) {
                System.out.println("El proyecto no existe");
                //JOptioPanel
            } else {
                proyecto.setNombre(nombre);
                proyecto.setCiudad(ciudad);

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

    public static void modificarGestion() {
    }

    public static void eliminarProveedor(int id) {
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

    public static void eliminarPieza(int id) {
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

    public static void eliminarProyecto(int id) {
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

    public static void eliminarGestion() {
    }

    public static void consultaProveedorPorCod() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from ProveedoresEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaProveedorPorNombre() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaProveedorPorDir() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaPiezaPorCod() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaPiezaPorNombre() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaProyectoPorCod() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaProyectoPorNombre() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    public static void consultaProyectoPorCiudad() {
        /*
        //Muestra el apellido y oficio del empleado con número 7369
        String hql = "from EmpleadosEntity where dni = :numemple";
        Query q = session.createQuery (hql);
        q.setParameter("numemple",  "12345678A");
        EmpleadosEntity emple = (EmpleadosEntity) q.uniqueResult(); //uniqueResult() se utiliza cuando sabemos que nosva a devolver un único registro
        System.out.printf ("%s, %s %n",emple.getNombre(), emple.getCodDepartamento());
        */
    }

    //Faltan las consultas de gestion
}
