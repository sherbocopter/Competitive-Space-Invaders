package personalspaceinvaders.networking;

import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import personalspaceinvaders.Commons;
import personalspaceinvaders.Scene;
import personalspaceinvaders.Scenes.MultiplayerScene;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.networking.serializables.Message;
import personalspaceinvaders.networking.serializables.WavesSerData;

/**
 *
 * @author SHerbocopter
 */
public class MultiplayerBase extends Thread implements Commons {
    public Socket clientSocket;
    public ObjectInputStream streamFromPeer = null;
    public ObjectOutputStream streamToPeer = null;
    public MultiplayerScene scene;
    private Message messageToSend = null;
    
    private Semaphore shouldSend = new Semaphore(0, true);
    
    public void sendMessage(Message message) {
        messageToSend = message;
        shouldSend.release();
    }
    
    protected void startLoops() {
        Thread recThread = new Thread(this::receiveLoop);
        recThread.start();
        
        sendLoop();
    }
    
    private void sendLoop() {
        try {
            while (true) {
                shouldSend.acquire();
                if (messageToSend != null) {
                    streamToPeer.writeObject(messageToSend);
                }
                else {
                    System.out.println("Message was null");
                }

                messageToSend = null;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void receiveLoop() {
        try {
            while (true) {
                Message message = (Message) streamFromPeer.readObject();
                System.out.println(message.text);

                switch (message.type) {
                    case MSG_WAVES: {
                        WavesSerData wavesData = (WavesSerData)message.data;
                        this.scene.setLocalWaves(wavesData.waveTypes);
                    } break;
                    case MSG_ROUNDRES: {
                        
                    } break;
                    default: {
                        
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
