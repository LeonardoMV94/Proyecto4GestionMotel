/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.Boleta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.MetodoPago;
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
public class BoletaJpaController implements Serializable {

    public BoletaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Boleta boleta) throws RollbackFailureException, Exception {
        if (boleta.getRegistrosVentasCollection() == null) {
            boleta.setRegistrosVentasCollection(new ArrayList<RegistrosVentas>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MetodoPago metodoPagoIdMetodoPago = boleta.getMetodoPagoIdMetodoPago();
            if (metodoPagoIdMetodoPago != null) {
                metodoPagoIdMetodoPago = em.getReference(metodoPagoIdMetodoPago.getClass(), metodoPagoIdMetodoPago.getIdMetodoPago());
                boleta.setMetodoPagoIdMetodoPago(metodoPagoIdMetodoPago);
            }
            Collection<RegistrosVentas> attachedRegistrosVentasCollection = new ArrayList<RegistrosVentas>();
            for (RegistrosVentas registrosVentasCollectionRegistrosVentasToAttach : boleta.getRegistrosVentasCollection()) {
                registrosVentasCollectionRegistrosVentasToAttach = em.getReference(registrosVentasCollectionRegistrosVentasToAttach.getClass(), registrosVentasCollectionRegistrosVentasToAttach.getIdRegistro());
                attachedRegistrosVentasCollection.add(registrosVentasCollectionRegistrosVentasToAttach);
            }
            boleta.setRegistrosVentasCollection(attachedRegistrosVentasCollection);
            em.persist(boleta);
            if (metodoPagoIdMetodoPago != null) {
                metodoPagoIdMetodoPago.getBoletaCollection().add(boleta);
                metodoPagoIdMetodoPago = em.merge(metodoPagoIdMetodoPago);
            }
            for (RegistrosVentas registrosVentasCollectionRegistrosVentas : boleta.getRegistrosVentasCollection()) {
                Boleta oldBoletaidboletaOfRegistrosVentasCollectionRegistrosVentas = registrosVentasCollectionRegistrosVentas.getBoletaidboleta();
                registrosVentasCollectionRegistrosVentas.setBoletaidboleta(boleta);
                registrosVentasCollectionRegistrosVentas = em.merge(registrosVentasCollectionRegistrosVentas);
                if (oldBoletaidboletaOfRegistrosVentasCollectionRegistrosVentas != null) {
                    oldBoletaidboletaOfRegistrosVentasCollectionRegistrosVentas.getRegistrosVentasCollection().remove(registrosVentasCollectionRegistrosVentas);
                    oldBoletaidboletaOfRegistrosVentasCollectionRegistrosVentas = em.merge(oldBoletaidboletaOfRegistrosVentasCollectionRegistrosVentas);
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

    public void edit(Boleta boleta) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Boleta persistentBoleta = em.find(Boleta.class, boleta.getIdBoleta());
            MetodoPago metodoPagoIdMetodoPagoOld = persistentBoleta.getMetodoPagoIdMetodoPago();
            MetodoPago metodoPagoIdMetodoPagoNew = boleta.getMetodoPagoIdMetodoPago();
            Collection<RegistrosVentas> registrosVentasCollectionOld = persistentBoleta.getRegistrosVentasCollection();
            Collection<RegistrosVentas> registrosVentasCollectionNew = boleta.getRegistrosVentasCollection();
            List<String> illegalOrphanMessages = null;
            for (RegistrosVentas registrosVentasCollectionOldRegistrosVentas : registrosVentasCollectionOld) {
                if (!registrosVentasCollectionNew.contains(registrosVentasCollectionOldRegistrosVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistrosVentas " + registrosVentasCollectionOldRegistrosVentas + " since its boletaidboleta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (metodoPagoIdMetodoPagoNew != null) {
                metodoPagoIdMetodoPagoNew = em.getReference(metodoPagoIdMetodoPagoNew.getClass(), metodoPagoIdMetodoPagoNew.getIdMetodoPago());
                boleta.setMetodoPagoIdMetodoPago(metodoPagoIdMetodoPagoNew);
            }
            Collection<RegistrosVentas> attachedRegistrosVentasCollectionNew = new ArrayList<RegistrosVentas>();
            for (RegistrosVentas registrosVentasCollectionNewRegistrosVentasToAttach : registrosVentasCollectionNew) {
                registrosVentasCollectionNewRegistrosVentasToAttach = em.getReference(registrosVentasCollectionNewRegistrosVentasToAttach.getClass(), registrosVentasCollectionNewRegistrosVentasToAttach.getIdRegistro());
                attachedRegistrosVentasCollectionNew.add(registrosVentasCollectionNewRegistrosVentasToAttach);
            }
            registrosVentasCollectionNew = attachedRegistrosVentasCollectionNew;
            boleta.setRegistrosVentasCollection(registrosVentasCollectionNew);
            boleta = em.merge(boleta);
            if (metodoPagoIdMetodoPagoOld != null && !metodoPagoIdMetodoPagoOld.equals(metodoPagoIdMetodoPagoNew)) {
                metodoPagoIdMetodoPagoOld.getBoletaCollection().remove(boleta);
                metodoPagoIdMetodoPagoOld = em.merge(metodoPagoIdMetodoPagoOld);
            }
            if (metodoPagoIdMetodoPagoNew != null && !metodoPagoIdMetodoPagoNew.equals(metodoPagoIdMetodoPagoOld)) {
                metodoPagoIdMetodoPagoNew.getBoletaCollection().add(boleta);
                metodoPagoIdMetodoPagoNew = em.merge(metodoPagoIdMetodoPagoNew);
            }
            for (RegistrosVentas registrosVentasCollectionNewRegistrosVentas : registrosVentasCollectionNew) {
                if (!registrosVentasCollectionOld.contains(registrosVentasCollectionNewRegistrosVentas)) {
                    Boleta oldBoletaidboletaOfRegistrosVentasCollectionNewRegistrosVentas = registrosVentasCollectionNewRegistrosVentas.getBoletaidboleta();
                    registrosVentasCollectionNewRegistrosVentas.setBoletaidboleta(boleta);
                    registrosVentasCollectionNewRegistrosVentas = em.merge(registrosVentasCollectionNewRegistrosVentas);
                    if (oldBoletaidboletaOfRegistrosVentasCollectionNewRegistrosVentas != null && !oldBoletaidboletaOfRegistrosVentasCollectionNewRegistrosVentas.equals(boleta)) {
                        oldBoletaidboletaOfRegistrosVentasCollectionNewRegistrosVentas.getRegistrosVentasCollection().remove(registrosVentasCollectionNewRegistrosVentas);
                        oldBoletaidboletaOfRegistrosVentasCollectionNewRegistrosVentas = em.merge(oldBoletaidboletaOfRegistrosVentasCollectionNewRegistrosVentas);
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
                Integer id = boleta.getIdBoleta();
                if (findBoleta(id) == null) {
                    throw new NonexistentEntityException("The boleta with id " + id + " no longer exists.");
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
            Boleta boleta;
            try {
                boleta = em.getReference(Boleta.class, id);
                boleta.getIdBoleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The boleta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RegistrosVentas> registrosVentasCollectionOrphanCheck = boleta.getRegistrosVentasCollection();
            for (RegistrosVentas registrosVentasCollectionOrphanCheckRegistrosVentas : registrosVentasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Boleta (" + boleta + ") cannot be destroyed since the RegistrosVentas " + registrosVentasCollectionOrphanCheckRegistrosVentas + " in its registrosVentasCollection field has a non-nullable boletaidboleta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            MetodoPago metodoPagoIdMetodoPago = boleta.getMetodoPagoIdMetodoPago();
            if (metodoPagoIdMetodoPago != null) {
                metodoPagoIdMetodoPago.getBoletaCollection().remove(boleta);
                metodoPagoIdMetodoPago = em.merge(metodoPagoIdMetodoPago);
            }
            em.remove(boleta);
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

    public List<Boleta> findBoletaEntities() {
        return findBoletaEntities(true, -1, -1);
    }

    public List<Boleta> findBoletaEntities(int maxResults, int firstResult) {
        return findBoletaEntities(false, maxResults, firstResult);
    }

    private List<Boleta> findBoletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Boleta.class));
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

    public Boleta findBoleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Boleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getBoletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Boleta> rt = cq.from(Boleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
