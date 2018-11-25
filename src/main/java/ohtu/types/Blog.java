/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.types;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Toothy
 */
public class Blog extends ItemType {

    private String poster;
    private String URL;

    public Blog(String URL, String title, String poster) {
        super.setType(typeIdentifier.blog);
        super.setTitle(title);
        this.poster = poster;
        this.URL = URL;
    }

    public Blog(int id, String URL, String title, String poster) {
        super.setType(typeIdentifier.blog);
        super.setId(id);
        super.setTitle(title);
        this.poster = poster;
        this.URL = URL;
    }

    public Blog(ResultSet rs) throws SQLException {
        super.setType(typeIdentifier.blog);
        super.setId(rs.getInt("id"));
        super.setTitle(rs.getString("title"));
        URL = rs.getString("URL");
        poster = rs.getString("Author");
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

}