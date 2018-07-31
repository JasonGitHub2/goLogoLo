/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo;

import djf.AppTemplate;
import djf.components.AppClipboardComponent;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.components.AppWorkspaceComponent;
import gologolo.clipboard.LogoClipboard;

import gologolo.data.LogoData;
import gologolo.files.LogoFiles;
import gologolo.workspace.goLogoLoWorkspace;
import static javafx.application.Application.launch;

/**
 *
 * @author Jason Cao
 */
public class GoLogoLo extends AppTemplate{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public AppClipboardComponent buildClipboardComponent(AppTemplate app) {
          return new LogoClipboard(this);
    }

    @Override
    public AppDataComponent buildDataComponent(AppTemplate app) {
      return new LogoData(this);
    }

    @Override
    public AppFileComponent buildFileComponent() {
         return new LogoFiles();
    }

    @Override
    public AppWorkspaceComponent buildWorkspaceComponent(AppTemplate app) {
       return new goLogoLoWorkspace(this);
    }
    
}
