/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Boleta;
import entity.Cliente;
import entity.Habitacion;
import entity.RegistrosVentas;
import entity.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;

/**
 *
 * @author Leonardo
 */
public class RegistrosVentasJpaController implements Serializable {

    public RegistrosVentasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistrosVentas registrosVentas) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Boleta boletaidboleta = registrosVentas.getBoletaidboleta();
            if (boletaidboleta != null) {
                boletaidboleta = em.getReference(boletaidboleta.getClass(), boletaidboleta.getIdBoleta());
                registrosVentas.setBoletaidboleta(boletaidboleta);
            }
            Cliente clienterutcliente = registrosVentas.getClienterutcliente();
            if (clienterutcliente != null) {
                clienterutcliente = em.getReference(clienterutcliente.getClass(), clienterutcliente.getRutCliente());
                registrosVentas.setClienterutcliente(clienterutcliente);
            }
            Habitacion habitacionidhabitacion = registrosVentas.getHabitacionidhabitacion();
            if (habitacionidhabitacion != null) {
                habitacionidhabitacion = em.getReference(habitacionidhabitacion.getClass(), habitacionidhabitacion.getIdHabitacion());
                registrosVentas.setHabitacionidhabitacion(habitacionidhabitacion);
            }
            Usuarios usuariosrutusuario = registrosVentas.getUsuariosrutusuario();
            if (usuariosrutusuario != null) {
                usuariosrutusuario = em.getReference(usuariosrutusuario.getClass(), usuariosrutusuario.getRutUsuario());
                registrosVentas.setUsuariosrutusuario(usuariosrutusuario);
            }
            em.persist(registrosVentas);
            if (boletaidboleta != null) {
                boletaidboleta.getRegistrosVentasCollection().add(registrosVentas);
                boletaidboleta = em.merge(boletaidboleta);
            }
            if (clienterutcliente != null) {
                clienterutcliente.getRegistrosVentasCollection().add(registrosVentas);
                clienterutcliente = em.merge(clienterutcliente);
            }
            if (habitacionidhabitacion != null) {
                habitacionidhabitacion.getRegistrosVentasCollection().add(registrosVentas);
                habitacionidhabitacion = em.merge(habitacionidhabitacion);
            }
            if (usuariosrutusuario != null) {
                usuariosrutusuario.getRegistrosVentasCollection().add(registrosVentas);
                usuariosrutusuario = em.merge(usuariosrutusuario);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistrosVentas registrosVentas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RegistrosVentas persistentRegistrosVentas = em.find(RegistrosVentas.class, registrosVentas.getIdRegistro());
            Boleta boletaidboletaOld = persistentRegistrosVentas.getBoletaidboleta();
            Boleta boletaidboletaNew = registrosVentas.getBoletaidboleta();
            Cliente clienterutclienteOld = persistentRegistrosVentas.getClienterutcliente();
            Cliente clienterutclienteNew = registrosVentas.getClienterutcliente();
            Habitacion habitacionidhabitacionOld = persistentRegistrosVentas.getHabitacionidhabitacion();
            Habitacion habitacionidhabitacionNew = registrosVentas.getHabitacionidhabitacion();
            Usuarios usuariosrutusuarioOld = persistentRegistrosVentas.getUsuariosrutusuario();
            Usuarios usuariosrutusuarioNew = registrosVentas.getUsuariosrutusuario();
            if (boletaidboletaNew != null) {
                boletaidboletaNew = em.getReference(boletaidboletaNew.getClass(), boletaidboletaNew.getIdBoleta());
                registrosVentas.setBoletaidboleta(boletaidboletaNew);
            }
            if (clienterutclienteNew != null) {
                clienterutclienteNew = em.getReference(clienterutclienteNew.getClass(), clienterutclienteNew.getRutCliente());
                registrosVentas.setClienterutcliente(clienterutclienteNew);
            }
            if (habitacionidhabitacionNew != null) {
                habitacionidhabitacionNew = em.getReference(habitacionidhabitacionNew.getClass(), habitacionidhabitacionNew.getIdHabitacion());
                registrosVentas.setHabitacionidhabitacion(habitacionidhabitacionNew);
            }
            if (usuariosrutusuarioNew != null) {
                usuariosrutusuarioNew = em.getReference(usuariosrutusuarioNew.getClass(), usuariosrutusuarioNew.getRutUsuario());
                registrosVentas.setUsuariosrutusuario(usuariosrutusuarioNew);
            }
            registrosVentas = em.merge(registrosVentas);
            if (boletaidboletaOld != null && !boletaidboletaOld.equals(boletaidboletaNew)) {
                boletaidboletaOld.getRegistrosVentasCollection().remove(registrosVentas);
                boletaidboletaOld = em.merge(boletaidboletaOld);
            }
            if (boletaidboletaNew != null && !boletaidboletaNew.equals(boletaidboletaOld)) {
                boletaidboletaNew.getRegistrosVentasCollection().add(registrosVentas);
                boletaidboletaNew = em.merge(boletaidboletaNew);
            }
            if (clienterutclienteOld != null && !clienterutclienteOld.equals(clienterutclienteNew)) {
                clienterutclienteOld.getRegistrosVentasCollection().remove(registrosVentas);
                clienterutclienteOld = em.merge(clienterutclienteOld);
            }
            if (clienterutclienteNew != null && !clienterutclienteNew.equals(clienterutclienteOld)) {
                clienterutclienteNew.getRegistrosVentasCollection().add(registrosVentas);
                clienterutclienteNew = em.merge(clienterutclienteNew);
            }
            if (habitacionidhabitacionOld != null && !habitacionidhabitacionOld.equals(habitacionidhabitacionNew)) {
                habitacionidhabitacionOld.getRegistrosVentasCollection().remove(registrosVentas);
                habitacionidhabitacionOld = em.merge(habitacionidhabitacionOld);
            }
            if (habitacionidhabitacionNew != null && !habitacionidhabitacionNew.equals(habitacionidhabitacionOld)) {
                habitacionidhabitacionNew.getRegistrosVentasCollection().add(registrosVentas);
                habitacionidhabitacionNew = em.merge(habitacionidhabitacionNew);
            }
            if (usuariosrutusuarioOld != null && !usuariosrutusuarioOld.equals(usuariosrutusuarioNew)) {
                usuariosrutusuarioOld.getRegistrosVentasCollection().remove(registrosVentas);
                usuariosrutusuarioOld = em.merge(usuariosrutusuarioOld);
            }
            if (usuariosrutusuarioNew != null && !usuariosrutusuarioNew.equals(usuariosrutusuarioOld)) {
                usuariosrutusuarioNew.getRegistrosVentasCollection().add(registrosVentas);
                usuariosrutusuarioNew = em.merge(usuariosrutusuarioNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registrosVentas.getIdRegistro();
                if (findRegistrosVentas(id) == null) {
                    throw new NonexistentEntityException("The registrosVentas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RegistrosVentas registrosVentas;
            try {
                registrosVentas = em.getReference(RegistrosVentas.class, id);
                registrosVentas.getIdRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrosVentas with id " + id + " no longer exists.", enfe);
            }
            Boleta boletaidboleta = registrosVentas.getBoletaidboleta();
            if (boletaidboleta != null) {
                boletaidboleta.getRegistrosVentasCollection().remove(registrosVentas);
                boletaidboleta = em.merge(boletaidboleta);
            }
            Cliente clienterutcliente = registrosVentas.getClienterutcliente();
            if (clienterutcliente != null) {
                clienterutcliente.getRegistrosVentasCollection().remove(registrosVentas);
                clienterutcliente = em.merge(clienterutcliente);
            }
            Habitacion habitacionidhabitacion = registrosVentas.getHabitacionidhabitacion();
            if (habitacionidhabitacion != null) {
                habitacionidhabitacion.getRegistrosVentasCollection().remove(registrosVentas);
                habitacionidhabitacion = em.merge(habitacionidhabitacion);
            }
            Usuarios usuariosrutusuario = registrosVentas.getUsuariosrutusuario();
            if (usuariosrutusuario != null) {
                usuariosrutusuario.getRegistrosVentasCollection().remove(registrosVentas);
                usuariosrutusuario = em.merge(usuariosrutusuario);
            }
            em.remove(registrosVentas);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistrosVentas> findRegistrosVentasEntities() {
        return findRegistrosVentasEntities(true, -1, -1);
    }

    public List<RegistrosVentas> findRegistrosVentasEntities(int maxResults, int firstResult) {
        return findRegistrosVentasEntities(false, maxResults, firstResult);
    }

    private List<RegistrosVentas> findRegistrosVentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistrosVentas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RegistrosVentas findRegistrosVentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistrosVentas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrosVentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistrosVentas> rt = cq.from(RegistrosVentas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
