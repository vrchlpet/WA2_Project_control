package org.cvut.wa2.projectcontrol.DAO;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");
    
    private static PersistenceManager pm = null;

    private PMF() {}

    public static PersistenceManager get() {
    	if (pm == null)
    		pm = pmfInstance.getPersistenceManager();
    	
    	return pm;
    }
}
