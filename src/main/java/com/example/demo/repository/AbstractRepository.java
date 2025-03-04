package com.example.demo.repository;

import com.example.demo.util.CollectionUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractRepository<T> {

    @PersistenceContext
    private EntityManager em;

//    private final Class<T> entityClass;

//    public AbstractRepository(Class<T> entityClass) {
//        this.entityClass = entityClass;
//    }

    protected EntityManager getEntityManager() {
        return em;
    }

    protected T getSingleResult(CriteriaQuery<T> cq) {
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        try {
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    protected List<T> getResultList(CriteriaQuery<T> cq, List<Order> orderList, Integer index, Integer limit) {
        if (CollectionUtil.isNotNullOrEmpty(orderList)) cq.orderBy(orderList);
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        if (index != null) q.setFirstResult(index);
        if (limit != null) q.setMaxResults(limit);
        return q.getResultList();
    }

    protected long getCount(CriteriaBuilder cb, CriteriaQuery<Long> cq, Root<T> root) {
        cq.select(cb.countDistinct(root));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);

        try {
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return 0;
        }
    }
}
