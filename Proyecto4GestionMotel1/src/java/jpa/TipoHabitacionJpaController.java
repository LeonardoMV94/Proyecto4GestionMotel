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
import entity.Habitacion;
import entity.TipoHabitacion;
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
public class TipoHabitacionJpaController implements Serializable {

    public TipoHabitacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoHabitacion tipoHabitacion) throws RollbackFailureException, Exception {
        if (tipoHabitacion.getHabitacionCollection() == null) {
            tipoHabitacion.setHabitacionCollection(new ArrayList<Habitacion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Habitacion> attachedHabitacionCollection = new ArrayList<Habitacion>();
            for (Habitacion habitacionCollectionHabitacionToAttach : tipoHabitacion.getHabitacionCollection()) {
                habitacionCollectionHabitacionToAttach = em.getReference(habitacionCollectionHabitacionToAttach.getClass(), habitacionCollectionHabitacionToAttach.getIdHabitacion());
                attachedHabitacionCollection.add(habitacionCollectionHabitacionToAttach);
            }
            tipoHabitacion.setHabitacionCollection(attachedHabitacionCollection);
            em.persist(tipoHabitacion);
            for (Habitacion habitacionCollectionHabitacion : tipoHabitacion.getHabitacionCollection()) {
                TipoHabitacion oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionHabitacion = habitacionCollectionHabitacion.getTipoHabitacionIdTipoHabitacion();
                habitacionCollectionHabitacion.setTipoHabitacionIdTipoHabitacion(tipoHabitacion);
                habitacionCollectionHabitacion = em.merge(habitacionCollectionHabitacion);
                if (oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionHabitacion != null) {
                    oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionHabitacion.getHabitacionCollection().remove(habitacionCollectionHabitacion);
                    oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionHabitacion = em.merge(oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionHabitacion);
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

    public void edit(TipoHabitacion tipoHabitacion) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoHabitacion persistentTipoHabitacion = em.find(TipoHabitacion.class, tipoHabitacion.getIdTipoHabitacion());
            Collection<Habitacion> habitacionCollectionOld = persistentTipoHabitacion.getHabitacionCollection();
            Collection<Habitacion> habitacionCollectionNew = tipoHabitacion.getHabitacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Habitacion habitacionCollectionOldHabitacion : habitacionCollectionOld) {
                if (!habitacionCollectionNew.contains(habitacionCollectionOldHabitacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Habitacion " + habitacionCollectionOldHabitacion + " since its tipoHabitacionIdTipoHabitacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Habitacion> attachedHabitacionCollectionNew = new ArrayList<Habitacion>();
            for (Habitacion habitacionCollectionNewHabitacionToAttach : habitacionCollectionNew) {
                habitacionCollectionNewHabitacionToAttach = em.getReference(habitacionCollectionNewHabitacionToAttach.getClass(), habitacionCollectionNewHabitacionToAttach.getIdHabitacion());
                attachedHabitacionCollectionNew.add(habitacionCollectionNewHabitacionToAttach);
            }
            habitacionCollectionNew = attachedHabitacionCollectionNew;
            tipoHabitacion.setHabitacionCollection(habitacionCollectionNew);
            tipoHabitacion = em.merge(tipoHabitacion);
            for (Habitacion habitacionCollectionNewHabitacion : habitacionCollectionNew) {
                if (!habitacionCollectionOld.contains(habitacionCollectionNewHabitacion)) {
                    TipoHabitacion oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionNewHabitacion = habitacionCollectionNewHabitacion.getTipoHabitacionIdTipoHabitacion();
                    habitacionCollectionNewHabitacion.setTipoHabitacionIdTipoHabitacion(tipoHabitacion);
                    habitacionCollectionNewHabitacion = em.merge(habitacionCollectionNewHabitacion);
                    if (oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionNewHabitacion != null && !oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionNewHabitacion.equals(tipoHabitacion)) {
                        oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionNewHabitacion.getHabitacionCollection().remove(habitacionCollectionNewHabitacion);
                        oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionNewHabitacion = em.merge(oldTipoHabitacionIdTipoHabitacionOfHabitacionCollectionNewHabitacion);
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
                Integer id = tipoHabitacion.getIdTipoHabitacion();
                if (findTipoHabitacion(id) == null) {
                    throw new NonexistentEntityException("The tipoHabitacion with id " + id + " no longer exists.");
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
            TipoHabitacion tipoHabitacion;
            try {
                tipoHabitacion = em.getReference(TipoHabitacion.class, id);
                tipoHabitacion.getIdTipoHabitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoHabitacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Habitacion> habitacionCollectionOrphanCheck = tipoHabitacion.getHabitacionCollection();
            for (Habitacion habitacionCollectionOrphanCheckHabitacion : habitacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoHabitacion (" + tipoHabitacion + ") cannot be destroyed since the Habitacion " + habitacionCollectionOrphanCheckHabitacion + " in its habitacionCollection field has a non-nullable tipoHabitacionIdTipoHabitacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoHabitacion);
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

    public List<TipoHabitacion> findTipoHabitacionEntities() {
        return findTipoHabitacionEntities(true, -1, -1);
    }

    public List<TipoHabitacion> findTipoHabitacionEntities(int maxResults, int firstResult) {
        return findTipoHabitacionEntities(false, maxResults, firstResult);
    }

    private List<TipoHabitacion> findTipoHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoHabitacion.class));
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

    public TipoHabitacion findTipoHabitacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoHabitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoHabitacion> rt = cq.from(TipoHabitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
