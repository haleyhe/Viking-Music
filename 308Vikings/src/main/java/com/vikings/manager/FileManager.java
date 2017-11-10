package com.vikings.manager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Manager for actions relating to File uploads
 * (e.g. uploading Playlist thumbnail)
 */
@Service
public class FileManager {
    
    private static final long MAX_IMAGE_SIZE_BYTES = 10000000; // 10 MB
    private static final int PLAYLIST_THUMBNAIL_WIDTH = 400;
    private static final int PLAYLIST_THUMBNAIL_HEIGHT = 400;
    
    /**
     * Attempts to upload the given Playlist thumbnail image to the server.
     * @param file
     *  The file containing the playlist image.
     * @param playlistId
     *  ID of the playlist (for file naming).
     * @return 
     *  True if successful, false if invalid file.
     */
    public boolean uploadPlaylistThumbnail(MultipartFile file, String playlistId) {
        if (file.getSize() > MAX_IMAGE_SIZE_BYTES) {
            return false;
        }
        
        try {
            BufferedImage thumbnail = ImageIO.read(file.getInputStream());
            Image resizedThumbnail = thumbnail.getScaledInstance(PLAYLIST_THUMBNAIL_WIDTH, PLAYLIST_THUMBNAIL_HEIGHT, Image.SCALE_DEFAULT);
            
            // The resized image has to be redrawn onto a new BufferedImage.
            // Resizing converts the BufferedImage to an Image, but only BufferedImages
            // can be written using ImageIO.
            BufferedImage bufferedResizedThumbnail = new BufferedImage(PLAYLIST_THUMBNAIL_WIDTH, PLAYLIST_THUMBNAIL_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D bufferedGraphic = bufferedResizedThumbnail.createGraphics();
            bufferedGraphic.drawImage(resizedThumbnail, 0, 0, PLAYLIST_THUMBNAIL_WIDTH, PLAYLIST_THUMBNAIL_HEIGHT, null);
            bufferedGraphic.dispose();
            
            String sourceFilepath = System.getProperty("file.Playlist.sourceThumbnailPath") + playlistId + ".jpg";
            String targetFilepath = System.getProperty("file.Playlist.targetThumbnailPath") + playlistId + ".jpg";
            File sourceImageFile = new File(sourceFilepath);
            File targetImageFile = new File(targetFilepath);
            sourceImageFile.getParentFile().mkdirs();
            targetImageFile.getParentFile().mkdirs();
            ImageIO.write(bufferedResizedThumbnail, "jpg", sourceImageFile);
            ImageIO.write(bufferedResizedThumbnail, "jpg", targetImageFile);
            
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }
    
}
