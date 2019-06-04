/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationtier;

import bussineslogic.model.ShoppingBasket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juju
 */
@Stateless
public class ShoppingBasketFacade extends AbstractFacade<ShoppingBasket> {

    @PersistenceContext(unitName = "Strona_Internetowa_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoppingBasketFacade() {
        super(ShoppingBasket.class);
    }
    
}
