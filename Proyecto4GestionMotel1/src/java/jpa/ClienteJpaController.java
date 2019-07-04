/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.RegistrosVentas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import jpa.exceptions.RollbackFailureException;

/**
 *
 * @author Leonardo
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cliente.getRegistrosVentasCollection() == null) {
            cliente.setRegistrosVentasCollection(new ArrayList<RegistrosVentas>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<RegistrosVentas> attachedRegistrosVentasCollection = new ArrayList<RegistrosVentas>();
            for (RegistrosVentas registrosVentasCollectionRegistrosVentasToAttach : cliente.getRegistrosVentasCollection()) {
                registrosVentasCollectionRegistrosVentasToAttach = em.getReference(registrosVentasCollectionRegistrosVentasToAttach.getClass(), registrosVentasCollectionRegistrosVentasToAttach.getIdRegistro());
                attachedRegistrosVentasCollection.add(registrosVentasCollectionRegistrosVentasToAttach);
            }
            cliente.setRegistrosVentasCollection(attachedRegistrosVentasCollection);
            em.persist(cliente);
            for (RegistrosVentas registrosVentasCollectionRegistrosVentas : cliente.getRegistrosVentasCollection()) {
                Cliente oldClienterutclienteOfRegistrosVentasCollectionRegistrosVentas = registrosVentasCollectionRegistrosVentas.getClienterutcliente();
                registrosVentasCollectionRegistrosVentas.setClienterutcliente(cliente);
                registrosVentasCollectionRegistrosVentas = em.merge(registrosVentasCollectionRegistrosVentas);
                if (oldClienterutclienteOfRegistrosVentasCollectionRegistrosVentas != null) {
                    oldClienterutclienteOfRegistrosVentasCollectionRegistrosVentas.getRegistrosVentasCollection().remove(registrosVentasCollectionRegistrosVentas);
                    oldClienterutclienteOfRegistrosVentasCollectionRegistrosVentas = em.merge(oldClienterutclienteOfRegistrosVentasCollectionRegistrosVentas);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCliente(cliente.getRutCliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getRutCliente());
            Collection<RegistrosVentas> registrosVentasCollectionOld = persistentCliente.getRegistrosVentasCollection();
            Collection<RegistrosVentas> registrosVentasCollectionNew = cliente.getRegistrosVentasCollection();
            List<String> illegalOrphanMessages = null;
            for (RegistrosVentas registrosVentasCollectionOldRegistrosVentas : registrosVentasCollectionOld) {
                if (!registrosVentasCollectionNew.contains(registrosVentasCollectionOldRegistrosVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistrosVentas " + registrosVentasCollectionOldRegistrosVentas + " since its clienterutcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<RegistrosVentas> attachedRegistrosVentasCollectionNew = new ArrayList<RegistrosVentas>();
            for (RegistrosVentas registrosVentasCollectionNewRegistrosVentasToAttach : registrosVentasCollectionNew) {
                registrosVentasCollectionNewRegistrosVentasToAttach = em.getReference(registrosVentasCollectionNewRegistrosVentasToAttach.getClass(), registrosVentasCollectionNewRegistrosVentasToAttach.getIdRegistro());
                attachedRegistrosVentasCollectionNew.add(registrosVentasCollectionNewRegistrosVentasToAttach);
            }
            registrosVentasCollectionNew = attachedRegistrosVentasCollectionNew;
            cliente.setRegistrosVentasCollection(registrosVentasCollectionNew);
            cliente = em.merge(cliente);
            for (RegistrosVentas registrosVentasCollectionNewRegistrosVentas : registrosVentasCollectionNew) {
                if (!registrosVentasCollectionOld.contains(registrosVentasCollectionNewRegistrosVentas)) {
                    Cliente oldClienterutclienteOfRegistrosVentasCollectionNewRegistrosVentas = registrosVentasCollectionNewRegistrosVentas.getClienterutcliente();
                    registrosVentasCollectionNewRegistrosVentas.setClienterutcliente(cliente);
                    registrosVentasCollectionNewRegistrosVentas = em.merge(registrosVentasCollectionNewRegistrosVentas);
                    if (oldClienterutclienteOfRegistrosVentasCollectionNewRegistrosVentas != null && !oldClienterutclienteOfRegistrosVentasCollectionNewRegistrosVentas.equals(cliente)) {
                        oldClienterutclienteOfRegistrosVentasCollectionNewRegistrosVentas.getRegistrosVentasCollection().remove(registrosVentasCollectionNewRegistrosVentas);
                        oldClienterutclienteOfRegistrosVentasCollectionNewRegistrosVentas = em.merge(oldClienterutclienteOfRegistrosVentasCollectionNewRegistrosVentas);
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
                String id = cliente.getRutCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getRutCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RegistrosVentas> registrosVentasCollectionOrphanCheck = cliente.getRegistrosVentasCollection();
            for (RegistrosVentas registrosVentasCollectionOrphanCheckRegistrosVentas : registrosVentasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the RegistrosVentas " + registrosVentasCollectionOrphanCheckRegistrosVentas + " in its registrosVentasCollection field has a non-nullable clienterutcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
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

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
