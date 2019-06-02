/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import bussineslogic.Factory;
import bussineslogic.model.Client;
import fit.ColumnFixture;

/**
 *
 * @author Wojciech
 */
public class Test_searchClient extends ColumnFixture{
    String clientdata[];
    Client result, data, client;
    int number;
    Factory factory;
    
    public boolean searchClient_(){
        result = null;
        factory = new Factory();

        client = factory.createClient(clientdata);
        result = SetUp.facade.searchClient(client);
        data = SetUp.data.clientData[number];
        //Jesli masz pomysl dlaczego to nie dziala to ja z checia sie dowiem
        //Ogólnie jeśli wszystko da się .toString() to powinno przejść, ale nie wiem
        //czy taka metoda będzie odpowiednia
        return data.equals(result);
    }
}
