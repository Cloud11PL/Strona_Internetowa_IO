/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationtier;

import bussineslogic.model.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juju
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "Strona_Internetowa_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    String lastID;

    public void addClient(List<Client> clients) {
        for (Client client : clients) {
            if (client.getID() == null) {
                getEntityManager().persist(client);
            }
        }

    }
    
    public List<Client> getClientList(){
        Query query = getEntityManager().createQuery("SELECT a FROM Client a");
        return query.getResultList();
    }
    
}
