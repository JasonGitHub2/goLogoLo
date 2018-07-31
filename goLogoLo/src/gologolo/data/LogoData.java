/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import djf.components.AppDataComponent;
import gologolo.GoLogoLo;

/**
 *
 * @author jasoncao
 */
public class LogoData implements AppDataComponent{

        GoLogoLo initApp;
    public LogoData(GoLogoLo logoApp) {
           logoApp = initApp;
    }

    @Override
    public void reset() {
     System.out.print("Remember to implement");
    }
    
}
