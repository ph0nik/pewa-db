package com.pewa.music;

public interface AlbumDAO {

        void listByTitle();
        void listByArtist();
        void listByYear();
        void addAlbum();
        void addEntity();
        void updateAlbum();
        Boolean chkIfExist();

}
