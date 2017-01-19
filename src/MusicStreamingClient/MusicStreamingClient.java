package MusicStreamingClient;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

/**
 *
 * @author James Henderson
 */
public class MusicStreamingClient
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Client reading from ");
        
        try(Socket socket = new Socket("localhost", 6655))
        {
            if(socket.isConnected()) {
                InputStream in = new BufferedInputStream(socket.getInputStream());
                play(in);
            }
        }
        
        System.out.println("Client shutting down");
    }
    
    private static synchronized void play(final InputStream in) throws Exception
    {
        AudioInputStream ais = AudioSystem.getAudioInputStream(in);
        try(Clip clip = AudioSystem.getClip())
        {
            clip.open(ais);
            clip.start();
            Thread.sleep(100);
            clip.drain();
        }
    }
}
