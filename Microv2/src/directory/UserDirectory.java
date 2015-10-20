package directory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UserDirectory implements IUserDirectory{
	
	@PersistenceContext
	private EntityManager em;
	private List<FinalUser> users = new ArrayList<FinalUser>();
	
	public void addUser(FinalUser user) {		
		users = lookUpAllUsers();
		if(!users.contains(user)) {
			em.persist(user);
		}		
	}

	public void removeUser(FinalUser user) {
		FinalUser user0 = em.merge(user);
		em.remove(user0);
	}
	
	public List<FinalUser> lookUpAllUsers() {
		Query q = em.createQuery("select u from FinalUser u");
		return (List<FinalUser>)q.getResultList();
	}
	
	public NewsGroupRights lookUpAUserRights(FinalUser user) {
		Query q = em.createQuery("select u.newsGroupRights from FinalUser u where u = :user");
		q.setParameter("user", user);
		return (NewsGroupRights) q.getSingleResult();
	}
	
	public void updateAUserRights(FinalUser user, NewsGroupRights rights) {
		users = lookUpAllUsers();
		Iterator<FinalUser> it = users.iterator();
		boolean trouve = false;
		while(it.hasNext() && trouve == false) {
			FinalUser userCourant = it.next();
			if(userCourant.getUserName().equals(user.getUserName())) {
				trouve = true;
				userCourant.setUserRights(rights);
			}
		}
		
					
	}
	
	
}
