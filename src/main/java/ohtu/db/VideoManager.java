/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtu.types.Book;
import ohtu.types.Video;

/**
 *
 * @author ColdFish
 */
public class VideoManager implements sqlManager<Video, Integer> {

    private Database database;

    public VideoManager(Database database) {
        this.database = database;
    }

    public boolean add(Video video, String user) throws SQLException {
        Connection connection = database.getConnection();
        CallableStatement stmt = connection.prepareCall("{call AddVideoAndLink(?,?,?,?)}");

        stmt.setObject(1, video.getTitle());
        stmt.setObject(2, video.getURL());
        stmt.setObject(3, video.getPoster());
        stmt.setObject(4, user);

        int diu = stmt.executeUpdate();

        stmt.close();
        connection.close();

        return diu == 1;
    }

    @Override
    public Video findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT Video.id, title, URL FROM Video, Author FROM Video, Author WHERE Video.id = ?  AND Video.fk_Poster_id = Author.id");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Video o = new Video(rs);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Video> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT Video.id, title, URL FROM Video, Author WHERE Video.id = ? FROM Video, Author WHERE Video.fk_Poster_id = Author.id");
        ResultSet rs = stmt.executeQuery();
        List<Video> videos = new ArrayList<>();
        while (rs.next()) {
            videos.add(new Video(rs));
        }

        rs.close();
        stmt.close();
        connection.close();

        return videos;
    }

    public List<Video> findAll(String user) throws SQLException {
        Connection connection = database.getConnection();
        CallableStatement stmt = connection.prepareCall("{call getVideosForID(?)}");
        stmt.setObject(1, user);

        ResultSet rs = stmt.executeQuery();
        List<Video> books = new ArrayList<>();
        while (rs.next()) {
            books.add(new Video(rs));
        }

        rs.close();
        stmt.close();
        connection.close();

        return books;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
