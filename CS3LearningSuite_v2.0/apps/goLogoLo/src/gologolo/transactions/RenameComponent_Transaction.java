/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class RenameComponent_Transaction implements jTPS_Transaction{

    String newName;
    LogoPrototype prototypeToRename;
    GoLogoLo app;
    String oldName;
    public RenameComponent_Transaction(String name,LogoPrototype componentToRename,GoLogoLo appLogo){
        newName=name;
        
        prototypeToRename=componentToRename;
        oldName=componentToRename.getName();
        app=appLogo;       
    }
    
    @Override
    public void doTransaction() {
        prototypeToRename.setName(newName);
    }

    @Override
    public void undoTransaction() {
        prototypeToRename.setName(oldName);
    }
    
}
