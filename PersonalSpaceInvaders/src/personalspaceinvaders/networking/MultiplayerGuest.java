package personalspaceinvaders.networking;

import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import personalspaceinvaders.SceneManager;
import personalspaceinvaders.Scenes.MainMenuScene;
import personalspaceinvaders.Scenes.MultiplayerScene;

/**
 *
 * @author SHerbocopter
 */
public class MultiplayerGuest extends MultiplayerBase {
    public String ipAddress;
    public int port;
    
    public MultiplayerGuest(MultiplayerScene scene, String ipAddress, int port) {
        this.scene = scene;
        this.ipAddress = ipAddress;
        this.port = port;
    }
    
    @Override
    public void run() {
        super.run();
        
        try {
            boolean foundServer = false;
            for (int i = 0; i < POLLING_TRIES && foundServer == false; ++i) {
                try {
                    clientSocket = new Socket(ipAddress, port);
                    foundServer = true;
                }
                catch (Exception ex) {
                    System.out.format(">Peer not found, trying again in %d seconds\n",
                                        POLLING_TIME / 1000);
                    foundServer = false;
                    Thread.sleep(POLLING_TIME);
                }
            }
            
            if (foundServer == false) {
                SceneManager.getInstance().changeScene(new MainMenuScene());
                this.interrupt();
                return;
            }
            
            streamToPeer = new ObjectOutputStream(clientSocket.getOutputStream());
            streamFromPeer = new ObjectInputStream(clientSocket.getInputStream());
            
            startLoops();
        }
        catch (Exception ex) {
            System.out.println();
        }
    }
}
