package personalspaceinvaders.networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import personalspaceinvaders.SceneManager;
import personalspaceinvaders.Scenes.MainMenuScene;
import personalspaceinvaders.Scenes.MultiplayerScene;

/**
 *
 * @author SHerbocopter
 */
public class MultiplayerHost extends MultiplayerBase {
    public MultiplayerHost(MultiplayerScene scene) {
        this.scene = scene;
    }
    
    @Override
    public void run() {
        super.run();
        
        try {
            serverSocket = new ServerSocket(MULTIPLAYER_PORT);
            
            clientSocket = serverSocket.accept();
            streamFromPeer = new ObjectInputStream(clientSocket.getInputStream());
            streamToPeer = new ObjectOutputStream(clientSocket.getOutputStream());
            streamToPeer.flush();
            
            startLoops();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            kill();
        }
    }
}
