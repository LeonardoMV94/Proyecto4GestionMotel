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
import entity.MetodoPago;
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
public class MetodoPagoJpaController implements Serializable {

    public MetodoPagoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MetodoPago metodoPago) throws RollbackFailureException, Exception {
        if (metodoPago.getBoletaCollection() == null) {
            metodoPago.setBoletaCollection(new ArrayList<Boleta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Boleta> attachedBoletaCollection = new ArrayList<Boleta>();
            for (Boleta boletaCollectionBoletaToAttach : metodoPago.getBoletaCollection()) {
                boletaCollectionBoletaToAttach = em.getReference(boletaCollectionBoletaToAttach.getClass(), boletaCollectionBoletaToAttach.getIdBoleta());
                attachedBoletaCollection.add(boletaCollectionBoletaToAttach);
            }
            metodoPago.setBoletaCollection(attachedBoletaCollection);
            em.persist(metodoPago);
            for (Boleta boletaCollectionBoleta : metodoPago.getBoletaCollection()) {
                MetodoPago oldMetodoPagoIdMetodoPagoOfBoletaCollectionBoleta = boletaCollectionBoleta.getMetodoPagoIdMetodoPago();
                boletaCollectionBoleta.setMetodoPagoIdMetodoPago(metodoPago);
                boletaCollectionBoleta = em.merge(boletaCollectionBoleta);
                if (oldMetodoPagoIdMetodoPagoOfBoletaCollectionBoleta != null) {
                    oldMetodoPagoIdMetodoPagoOfBoletaCollectionBoleta.getBoletaCollection().remove(boletaCollectionBoleta);
                    oldMetodoPagoIdMetodoPagoOfBoletaCollectionBoleta = em.merge(oldMetodoPagoIdMetodoPagoOfBoletaCollectionBoleta);
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

    public void edit(MetodoPago metodoPago) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MetodoPago persistentMetodoPago = em.find(MetodoPago.class, metodoPago.getIdMetodoPago());
            Collection<Boleta> boletaCollectionOld = persistentMetodoPago.getBoletaCollection();
            Collection<Boleta> boletaCollectionNew = metodoPago.getBoletaCollection();
            List<String> illegalOrphanMessages = null;
            for (Boleta boletaCollectionOldBoleta : boletaCollectionOld) {
                if (!boletaCollectionNew.contains(boletaCollectionOldBoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Boleta " + boletaCollectionOldBoleta + " since its metodoPagoIdMetodoPago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Boleta> attachedBoletaCollectionNew = new ArrayList<Boleta>();
            for (Boleta boletaCollectionNewBoletaToAttach : boletaCollectionNew) {
                boletaCollectionNewBoletaToAttach = em.getReference(boletaCollectionNewBoletaToAttach.getClass(), boletaCollectionNewBoletaToAttach.getIdBoleta());
                attachedBoletaCollectionNew.add(boletaCollectionNewBoletaToAttach);
            }
            boletaCollectionNew = attachedBoletaCollectionNew;
            metodoPago.setBoletaCollection(boletaCollectionNew);
            metodoPago = em.merge(metodoPago);
            for (Boleta boletaCollectionNewBoleta : boletaCollectionNew) {
                if (!boletaCollectionOld.contains(boletaCollectionNewBoleta)) {
                    MetodoPago oldMetodoPagoIdMetodoPagoOfBoletaCollectionNewBoleta = boletaCollectionNewBoleta.getMetodoPagoIdMetodoPago();
                    boletaCollectionNewBoleta.setMetodoPagoIdMetodoPago(metodoPago);
                    boletaCollectionNewBoleta = em.merge(boletaCollectionNewBoleta);
                    if (oldMetodoPagoIdMetodoPagoOfBoletaCollectionNewBoleta != null && !oldMetodoPagoIdMetodoPagoOfBoletaCollectionNewBoleta.equals(metodoPago)) {
                        oldMetodoPagoIdMetodoPagoOfBoletaCollectionNewBoleta.getBoletaCollection().remove(boletaCollectionNewBoleta);
                        oldMetodoPagoIdMetodoPagoOfBoletaCollectionNewBoleta = em.merge(oldMetodoPagoIdMetodoPagoOfBoletaCollectionNewBoleta);
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
                Integer id = metodoPago.getIdMetodoPago();
                if (findMetodoPago(id) == null) {
                    throw new NonexistentEntityException("The metodoPago with id " + id + " no longer exists.");
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
            MetodoPago metodoPago;
            try {
                metodoPago = em.getReference(MetodoPago.class, id);
                metodoPago.getIdMetodoPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metodoPago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Boleta> boletaCollectionOrphanCheck = metodoPago.getBoletaCollection();
            for (Boleta boletaCollectionOrphanCheckBoleta : boletaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MetodoPago (" + metodoPago + ") cannot be destroyed since the Boleta " + boletaCollectionOrphanCheckBoleta + " in its boletaCollection field has a non-nullable metodoPagoIdMetodoPago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(metodoPago);
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

    public List<MetodoPago> findMetodoPagoEntities() {
        return findMetodoPagoEntities(true, -1, -1);
    }

    public List<MetodoPago> findMetodoPagoEntities(int maxResults, int firstResult) {
        return findMetodoPagoEntities(false, maxResults, firstResult);
    }

    private List<MetodoPago> findMetodoPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MetodoPago.class));
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

    public MetodoPago findMetodoPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MetodoPago.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetodoPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MetodoPago> rt = cq.from(MetodoPago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
