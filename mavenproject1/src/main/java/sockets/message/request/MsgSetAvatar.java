
package sockets.message.request;

import javafx.scene.image.Image;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import java.awt.image.BufferedImage;
public class MsgSetAvatar extends MsgBase {
    
    private String  username;
    transient BufferedImage bufferedImage;

    public MsgSetAvatar(String username, BufferedImage bufferedImage) {
        super(MessageType.REQ_SET_AVATAR);
        this.username = username;
        this.bufferedImage =  bufferedImage;
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }



   

    
   
    
    
}
