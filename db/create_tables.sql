CREATE TABLE IF NOT EXISTS artists (
    artist_id VARCHAR(32),
    name VARCHAR(64),
    bio TEXT,
    num_followers INT,
    PRIMARY KEY (artist_id)
);

CREATE TABLE IF NOT EXISTS artist_genres (
    artist_id VARCHAR(32),
    genre VARCHAR(32),
    PRIMARY KEY (artist_id, genre)
);

CREATE TABLE IF NOT EXISTS songs (
    song_id VARCHAR(32),
    name VARCHAR(256),
    album_id VARCHAR(32),
    duration INT,
    explicit TINYINT,
    disc_number INT,
    track_number INT,
    lyrics TEXT,
    num_plays INT,

    PRIMARY KEY (song_id)
);

CREATE TABLE IF NOT EXISTS song_artists (
    song_id VARCHAR(32),
    artist_id VARCHAR(32),
    PRIMARY KEY (song_id, artist_id)
);

CREATE TABLE IF NOT EXISTS albums (
    album_id VARCHAR(32),
    name VARCHAR(256),
    release_date DATE,
    PRIMARY KEY (album_id)
);

CREATE TABLE IF NOT EXISTS album_artists (
    album_id VARCHAR(32),
    artist_id VARCHAR(32),
    PRIMARY KEY (album_id, artist_id)
);

CREATE TABLE IF NOT EXISTS album_songs (
    album_id VARCHAR(32),
    song_id VARCHAR(32),
    PRIMARY KEY (album_id, song_id)
);

CREATE TABLE IF NOT EXISTS concerts (
    concert_id VARCHAR(32),
    name VARCHAR(256),
    concert_date DATETIME,
    venue_id VARCHAR(32),
    ticketing_url VARCHAR(256),
    PRIMARY KEY (concert_id)
);

CREATE TABLE IF NOT EXISTS concert_venues (
    venue_id VARCHAR(32),
    name VARCHAR(32),
    address VARCHAR(256),
    zip VARCHAR(10),
    PRIMARY KEY (venue_id)
);

CREATE TABLE IF NOT EXISTS concert_artists (
    concert_id VARCHAR(32),
    artist_id VARCHAR(32),
    PRIMARY KEY (concert_id, artist_id)
);

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(32),
    username VARCHAR(32),
    hashed_password VARCHAR(256),
    email_address VARCHAR(64),
    zip VARCHAR(10),
    birth_date DATE,
    premium TINYINT,

    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_artist_follows (
    user_id VARCHAR(32),
    artist_id VARCHAR(32),
    PRIMARY KEY (user_id, artist_id)
);

CREATE TABLE IF NOT EXISTS user_songs_saved (
    user_id VARCHAR(32),
    song_id VARCHAR(32),
    PRIMARY KEY(user_id, song_id)
);

CREATE TABLE IF NOT EXISTS user_albums_saved (
    user_id VARCHAR(32),
    album_id VARCHAR(32),
    PRIMARY KEY (user_id, album_id)
);

CREATE TABLE IF NOT EXISTS playlists (
    playlist_id VARCHAR(32),
    creator_id VARCHAR(32),
    date_created DATE,
    name VARCHAR(256),
    description VARCHAR(512),
    is_public TINYINT,
    num_followers INT,
    
    PRIMARY KEY (playlist_id)
);

CREATE TABLE IF NOT EXISTS playlist_songs (
    playlist_id VARCHAR(32),
    song_id VARCHAR(32),
    track_number INT,
    PRIMARY KEY (playlist_id, track_number)
);

CREATE TABLE IF NOT EXISTS user_playlist_follows (
    user_id VARCHAR(32),
    playlist_id VARCHAR(32),
    PRIMARY KEY (user_id, playlist_id)
);
