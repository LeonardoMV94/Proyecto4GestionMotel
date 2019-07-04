/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.Habitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.TipoHabitacion;
import entity.RegistrosVentas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;

/**
 *
 * @author Leonardo
 */
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) throws RollbackFailureException, Exception {
        if (habitacion.getRegistrosVentasCollection() == null) {
            habitacion.setRegistrosVentasCollection(new ArrayList<RegistrosVentas>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoHabitacion tipoHabitacionIdTipoHabitacion = habitacion.getTipoHabitacionIdTipoHabitacion();
            if (tipoHabitacionIdTipoHabitacion != null) {
                tipoHabitacionIdTipoHabitacion = em.getReference(tipoHabitacionIdTipoHabitacion.getClass(), tipoHabitacionIdTipoHabitacion.getIdTipoHabitacion());
                habitacion.setTipoHabitacionIdTipoHabitacion(tipoHabitacionIdTipoHabitacion);
            }
            Collection<RegistrosVentas> attachedRegistrosVentasCollection = new ArrayList<RegistrosVentas>();
            for (RegistrosVentas registrosVentasCollectionRegistrosVentasToAttach : habitacion.getRegistrosVentasCollection()) {
                registrosVentasCollectionRegistrosVentasToAttach = em.getReference(registrosVentasCollectionRegistrosVentasToAttach.getClass(), registrosVentasCollectionRegistrosVentasToAttach.getIdRegistro());
                attachedRegistrosVentasCollection.add(registrosVentasCollectionRegistrosVentasToAttach);
            }
            habitacion.setRegistrosVentasCollection(attachedRegistrosVentasCollection);
            em.persist(habitacion);
            if (tipoHabitacionIdTipoHabitacion != null) {
                tipoHabitacionIdTipoHabitacion.getHabitacionCollection().add(habitacion);
                tipoHabitacionIdTipoHabitacion = em.merge(tipoHabitacionIdTipoHabitacion);
            }
            for (RegistrosVentas registrosVentasCollectionRegistrosVentas : habitacion.getRegistrosVentasCollection()) {
                Habitacion oldHabitacionidhabitacionOfRegistrosVentasCollectionRegistrosVentas = registrosVentasCollectionRegistrosVentas.getHabitacionidhabitacion();
                registrosVentasCollectionRegistrosVentas.setHabitacionidhabitacion(habitacion);
                registrosVentasCollectionRegistrosVentas = em.merge(registrosVentasCollectionRegistrosVentas);
                if (oldHabitacionidhabitacionOfRegistrosVentasCollectionRegistrosVentas != null) {
                    oldHabitacionidhabitacionOfRegistrosVentasCollectionRegistrosVentas.getRegistrosVentasCollection().remove(registrosVentasCollectionRegistrosVentas);
                    oldHabitacionidhabitacionOfRegistrosVentasCollectionRegistrosVentas = em.merge(oldHabitacionidhabitacionOfRegistrosVentasCollectionRegistrosVentas);
                }
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

    public void edit(Habitacion habitacion) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getIdHabitacion());
            TipoHabitacion tipoHabitacionIdTipoHabitacionOld = persistentHabitacion.getTipoHabitacionIdTipoHabitacion();
            TipoHabitacion tipoHabitacionIdTipoHabitacionNew = habitacion.getTipoHabitacionIdTipoHabitacion();
            Collection<RegistrosVentas> registrosVentasCollectionOld = persistentHabitacion.getRegistrosVentasCollection();
            Collection<RegistrosVentas> registrosVentasCollectionNew = habitacion.getRegistrosVentasCollection();
            List<String> illegalOrphanMessages = null;
            for (RegistrosVentas registrosVentasCollectionOldRegistrosVentas : registrosVentasCollectionOld) {
                if (!registrosVentasCollectionNew.contains(registrosVentasCollectionOldRegistrosVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistrosVentas " + registrosVentasCollectionOldRegistrosVentas + " since its habitacionidhabitacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoHabitacionIdTipoHabitacionNew != null) {
                tipoHabitacionIdTipoHabitacionNew = em.getReference(tipoHabitacionIdTipoHabitacionNew.getClass(), tipoHabitacionIdTipoHabitacionNew.getIdTipoHabitacion());
                habitacion.setTipoHabitacionIdTipoHabitacion(tipoHabitacionIdTipoHabitacionNew);
            }
            Collection<RegistrosVentas> attachedRegistrosVentasCollectionNew = new ArrayList<RegistrosVentas>();
            for (RegistrosVentas registrosVentasCollectionNewRegistrosVentasToAttach : registrosVentasCollectionNew) {
                registrosVentasCollectionNewRegistrosVentasToAttach = em.getReference(registrosVentasCollectionNewRegistrosVentasToAttach.getClass(), registrosVentasCollectionNewRegistrosVentasToAttach.getIdRegistro());
                attachedRegistrosVentasCollectionNew.add(registrosVentasCollectionNewRegistrosVentasToAttach);
            }
            registrosVentasCollectionNew = attachedRegistrosVentasCollectionNew;
            habitacion.setRegistrosVentasCollection(registrosVentasCollectionNew);
            habitacion = em.merge(habitacion);
            if (tipoHabitacionIdTipoHabitacionOld != null && !tipoHabitacionIdTipoHabitacionOld.equals(tipoHabitacionIdTipoHabitacionNew)) {
                tipoHabitacionIdTipoHabitacionOld.getHabitacionCollection().remove(habitacion);
                tipoHabitacionIdTipoHabitacionOld = em.merge(tipoHabitacionIdTipoHabitacionOld);
            }
            if (tipoHabitacionIdTipoHabitacionNew != null && !tipoHabitacionIdTipoHabitacionNew.equals(tipoHabitacionIdTipoHabitacionOld)) {
                tipoHabitacionIdTipoHabitacionNew.getHabitacionCollection().add(habitacion);
                tipoHabitacionIdTipoHabitacionNew = em.merge(tipoHabitacionIdTipoHabitacionNew);
            }
            for (RegistrosVentas registrosVentasCollectionNewRegistrosVentas : registrosVentasCollectionNew) {
                if (!registrosVentasCollectionOld.contains(registrosVentasCollectionNewRegistrosVentas)) {
                    Habitacion oldHabitacionidhabitacionOfRegistrosVentasCollectionNewRegistrosVentas = registrosVentasCollectionNewRegistrosVentas.getHabitacionidhabitacion();
                    registrosVentasCollectionNewRegistrosVentas.setHabitacionidhabitacion(habitacion);
                    registrosVentasCollectionNewRegistrosVentas = em.merge(registrosVentasCollectionNewRegistrosVentas);
                    if (oldHabitacionidhabitacionOfRegistrosVentasCollectionNewRegistrosVentas != null && !oldHabitacionidhabitacionOfRegistrosVentasCollectionNewRegistrosVentas.equals(habitacion)) {
                        oldHabitacionidhabitacionOfRegistrosVentasCollectionNewRegistrosVentas.getRegistrosVentasCollection().remove(registrosVentasCollectionNewRegistrosVentas);
                        oldHabitacionidhabitacionOfRegistrosVentasCollectionNewRegistrosVentas = em.merge(oldHabitacionidhabitacionOfRegistrosVentasCollectionNewRegistrosVentas);
                    }
                }
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
                Integer id = habitacion.getIdHabitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getIdHabitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RegistrosVentas> registrosVentasCollectionOrphanCheck = habitacion.getRegistrosVentasCollection();
            for (RegistrosVentas registrosVentasCollectionOrphanCheckRegistrosVentas : registrosVentasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habitacion (" + habitacion + ") cannot be destroyed since the RegistrosVentas " + registrosVentasCollectionOrphanCheckRegistrosVentas + " in its registrosVentasCollection field has a non-nullable habitacionidhabitacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoHabitacion tipoHabitacionIdTipoHabitacion = habitacion.getTipoHabitacionIdTipoHabitacion();
            if (tipoHabitacionIdTipoHabitacion != null) {
                tipoHabitacionIdTipoHabitacion.getHabitacionCollection().remove(habitacion);
                tipoHabitacionIdTipoHabitacion = em.merge(tipoHabitacionIdTipoHabitacion);
            }
            em.remove(habitacion);
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

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
