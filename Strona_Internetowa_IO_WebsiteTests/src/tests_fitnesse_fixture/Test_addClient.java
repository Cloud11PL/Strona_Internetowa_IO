/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import fit.ColumnFixture;

/**
 *
 * @author Wojciech
 */
public class Test_addClient extends ColumnFixture{
    
    String dataclients[], data, result;
    int number;
    
    public boolean addClient_() {
        result = null;
        result = SetUp.facade.addClient(dataclients);
        data = SetUp.data.clientData[number].toString();
        return data.equals(result);
    }
    
}
