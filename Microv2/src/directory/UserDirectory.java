package directory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mailBox.IMailBoxManager;
import mailBox.MailBoxManager;


@Stateless
public class UserDirectory implements IUserDirectory{
	
	@PersistenceContext
	private EntityManager em;
	private List<FinalUser> users = new ArrayList<FinalUser>();
	
	public void addUser(FinalUser user) {		
		users = lookUpAllUsers();
		if(!users.contains(user)) {
			em.persist(user);
			try {
				InitialContext ic = new InitialContext();
				IMailBoxManager iMailBoxManager = (IMailBoxManager) ic.lookup("mailBox.IMailBoxManager");
				iMailBoxManager.addUser(user.getUserName());
			} catch(Exception e) {
	            e.printStackTrace();
	        }
		}		
	}

	public void removeUser(FinalUser user) {
		FinalUser user0 = em.merge(user);
		em.remove(user0);
		try {
			InitialContext ic = new InitialContext();
			IMailBoxManager iMailBoxManager = (IMailBoxManager) ic.lookup("mailBox.IMailBoxManager");
			iMailBoxManager.removeUser(user0.getUserName());
		} catch(Exception e) {
            e.printStackTrace();
        }
		
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
