/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djf.ui.controllers;

import djf.AppTemplate;
import djf.ui.dialogs.AppDialogsFacade;
import djf.ui.dialogs.AppResizeDialog;

/**
 *
 * @author jasoncao
 */
public class AppNavigationController {
      // HERE'S THE APP
    AppTemplate app;
    
 
    public AppNavigationController(AppTemplate initApp) {
        // NOTHING YET
        app = initApp;
    }
    
    public void processResizeRequest(){
        AppDialogsFacade.resizeDialog();
    }
}
