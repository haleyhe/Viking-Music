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
    private static final int ARTIST_THUMBNAIL_WIDTH = 500;
    private static final int ARTIST_THUMBNAIL_HEIGHT = 500;
    
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
            BufferedImage resizedThumbnail = resizeImage(thumbnail, PLAYLIST_THUMBNAIL_WIDTH, PLAYLIST_THUMBNAIL_HEIGHT);
            
            String sourceFilepath = System.getProperty("file.rootFilePath") + System.getProperty("file.Playlist.sourceThumbnailPath") + playlistId + ".jpg";
            String targetFilepath = System.getProperty("file.rootFilePath") + System.getProperty("file.Playlist.targetThumbnailPath") + playlistId + ".jpg";
            
            writeImage(resizedThumbnail, sourceFilepath);
            writeImage(resizedThumbnail, targetFilepath);            
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Attempts to upload the given Artist thumbnail image to the server.
     * @param file
     *  The file containing the artist image.
     * @param artistId
     *  ID of the artist (for file naming).
     * @return 
     *  True if successful, false if invalid file.
     */
    public boolean uploadArtistThumbnail(MultipartFile file, String artistId) {
        if (file.getSize() > MAX_IMAGE_SIZE_BYTES) {
            return false;
        }
        
        try {
            BufferedImage thumbnail = ImageIO.read(file.getInputStream());
            BufferedImage resizedThumbnail = resizeImage(thumbnail, ARTIST_THUMBNAIL_WIDTH, ARTIST_THUMBNAIL_HEIGHT);
            
            String sourceFilepath = System.getProperty("file.rootFilePath") + System.getProperty("file.Artist.sourceThumbnailPath") + artistId + ".jpg";
            String targetFilepath = System.getProperty("file.rootFilePath") + System.getProperty("file.Artist.targetThumbnailPath") + artistId + ".jpg";
            
            writeImage(resizedThumbnail, sourceFilepath);
            writeImage(resizedThumbnail, targetFilepath);            
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }
    
    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image resizedThumbnail = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // The resized image has to be redrawn onto a new BufferedImage.
        // Resizing converts the BufferedImage to an Image, but only BufferedImages
        // can be written using ImageIO.
        BufferedImage bufferedResizedThumbnail = new BufferedImage(PLAYLIST_THUMBNAIL_WIDTH, PLAYLIST_THUMBNAIL_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D bufferedGraphic = bufferedResizedThumbnail.createGraphics();
        bufferedGraphic.drawImage(resizedThumbnail, 0, 0, PLAYLIST_THUMBNAIL_WIDTH, PLAYLIST_THUMBNAIL_HEIGHT, null);
        bufferedGraphic.dispose();
        
        return bufferedResizedThumbnail;
    }
    
    private void writeImage(BufferedImage image, String filepath) throws IOException {
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        ImageIO.write(image, "jpg", file);
    }
    
}
