package com.pewa.music;

/**
 * Created by phonik on 2017-02-22.
 */
public class Album {
    private String albumArtist;
    private String albumTitle;
    private String albumDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (albumArtist != null ? !albumArtist.equals(album.albumArtist) : album.albumArtist != null) return false;
        if (albumTitle != null ? !albumTitle.equals(album.albumTitle) : album.albumTitle != null) return false;
        return albumDate != null ? albumDate.equals(album.albumDate) : album.albumDate == null;
    }

    @Override
    public int hashCode() {
        int result = albumArtist != null ? albumArtist.hashCode() : 0;
        result = 31 * result + (albumTitle != null ? albumTitle.hashCode() : 0);
        result = 31 * result + (albumDate != null ? albumDate.hashCode() : 0);
        return result;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(String albumDate) {
        this.albumDate = albumDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumArtist='" + albumArtist + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", albumDate='" + albumDate + '\'' +
                '}';
    }
}
