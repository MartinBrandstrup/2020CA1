/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Christian
 */
public class GroupMemberFacade {
    
    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GroupMemberFacade() {
    }
    
     /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GroupMemberFacade getGroupmemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    
    public List<GroupMember> getAllGroupMembers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<GroupMember> query = em.createQuery("SELECT e From GroupMember e", GroupMember.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
