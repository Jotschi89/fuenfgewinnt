/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.game;

import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Jotschi
 */
public class Client {
    public static Socket join(InetAddress adr) {
        try {
            Socket skt = new Socket(adr, 12234);
            return skt;
        } catch (Exception e) {
            return null;
        }
    }
}
