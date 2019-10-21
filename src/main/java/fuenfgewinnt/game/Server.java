/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jotschi
 */
public class Server extends java.lang.Thread {

    private ServerSocket srvr;
    private Socket skt;
    private SetSocketAble parent;

    public Server(SetSocketAble o) {
        parent = o;
    }

    @Override
    public void run() {
        try {
            srvr = new ServerSocket(12234);
            skt = srvr.accept();
            System.out.print("Server has connected!\n");
            parent.setSocket(skt);
        } catch (Exception e) {
            try {
                if(srvr != null) {
                    srvr.close();
                    if(!srvr.isClosed()) {
                        parent.setSocket(null);
                    }
                }else {
                    parent.setSocket(null);
                }
                if(skt != null) {
                    skt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
        try {
            if(srvr != null) {
                srvr.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit() {
        try {
            if (srvr != null) {
                srvr.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getConnectionInfo() {
        if (srvr != null) {
            return srvr.toString();
        }
        return null;
    }
}
