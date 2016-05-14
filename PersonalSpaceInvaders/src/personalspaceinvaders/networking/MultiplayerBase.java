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
import personalspaceinvaders.networking.serializables.EndRoundStatusSer;
import personalspaceinvaders.networking.serializables.Message;
import personalspaceinvaders.networking.serializables.WavesSer;

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
    public boolean isHost = false;
    
    private Semaphore shouldSend = new Semaphore(0, true);
    
    public void sendMessage(Message message) {
        messageToSend = message;
        shouldSend.release();
    }
    
    public void sendWaves(ArrayList<WaveType> waves) {
        Message message = new Message();
        message.type = Message.MsgType.MSG_WAVES;
        message.data = new WavesSer(waves);
        
        sendMessage(message);
    }
    
    public void sendStatus(float currentHitpoints, float maxHitpoints) {
        Message message = new Message();
        message.type = Message.MsgType.MSG_ROUNDRES;
        message.text = "Here's my stats";
        
        EndRoundStatusSer endStatus = new EndRoundStatusSer();
        endStatus.currentHitpoints = currentHitpoints;
        endStatus.maxHitpoints = maxHitpoints;
        message.data = endStatus;
        
        sendMessage(message);
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
                        WavesSer wavesData = (WavesSer)message.data;
                        this.scene.setLocalWaves(wavesData.waveTypes);
                    } break;
                    case MSG_ROUNDRES: {
                        EndRoundStatusSer endStatus = (EndRoundStatusSer) message.data;
                        this.scene.setPeerStatus(endStatus);
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
