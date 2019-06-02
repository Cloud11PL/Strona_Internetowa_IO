/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import bussineslogic.Facade;
import fit.Fixture;
import testData.Data;

/**
 *
 * @author Wojciech
 */
public class SetUp extends Fixture{
    static Facade facade;
    static Data data;
    
    public SetUp() {
        facade = new Facade();
        data = new Data();
    }
}
