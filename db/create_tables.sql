CREATE TABLE IF NOT EXISTS artists (
    artist_id VARCHAR(40),
    name VARCHAR(64),
    bio TEXT,
    num_followers INT,
    PRIMARY KEY (artist_id)
);

CREATE TABLE IF NOT EXISTS artist_genres (
    artist_id VARCHAR(40),
    genre VARCHAR(32),
    PRIMARY KEY (artist_id, genre)
);

CREATE TABLE IF NOT EXISTS songs (
    song_id VARCHAR(40),
    name VARCHAR(256),
    album_id VARCHAR(40),
    duration INT,
    explicit TINYINT,
    disc_number INT,
    track_number INT,
    lyrics TEXT,
    num_plays INT,

    PRIMARY KEY (song_id)
);

CREATE TABLE IF NOT EXISTS song_artists (
    song_id VARCHAR(40),
    artist_id VARCHAR(40),
    PRIMARY KEY (song_id, artist_id)
);

CREATE TABLE IF NOT EXISTS albums (
    album_id VARCHAR(40),
    name VARCHAR(256),
    release_date DATE,
    PRIMARY KEY (album_id)
);

CREATE TABLE IF NOT EXISTS album_artists (
    album_id VARCHAR(40),
    artist_id VARCHAR(40),
    PRIMARY KEY (album_id, artist_id)
);

CREATE TABLE IF NOT EXISTS album_songs (
    album_id VARCHAR(40),
    song_id VARCHAR(40),
    PRIMARY KEY (album_id, song_id)
);

CREATE TABLE IF NOT EXISTS concerts (
    concert_id VARCHAR(40),
    name VARCHAR(256),
    concert_date DATETIME,
    venue_id VARCHAR(40),
    ticketing_url VARCHAR(256),
    PRIMARY KEY (concert_id)
);

CREATE TABLE IF NOT EXISTS concert_venues (
    venue_id VARCHAR(40),
    name VARCHAR(32),
    address_id VARCHAR(40),
    PRIMARY KEY (venue_id)
);

CREATE TABLE IF NOT EXISTS concert_artists (
    concert_id VARCHAR(40),
    artist_id VARCHAR(40),
    PRIMARY KEY (concert_id, artist_id)
);

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(40),
    username VARCHAR(32),
    hashed_password VARCHAR(256),
    email_address VARCHAR(64),
    zip VARCHAR(10),
    birth_date DATE,
    premium TINYINT,
    admin TINYINT,
    facebook_id VARCHAR(256),

    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_artist_follows (
    user_id VARCHAR(40),
    artist_id VARCHAR(40),
    PRIMARY KEY (user_id, artist_id)
);

CREATE TABLE IF NOT EXISTS user_songs_saved (
    user_id VARCHAR(40),
    song_id VARCHAR(40),
    PRIMARY KEY(user_id, song_id)
);

CREATE TABLE IF NOT EXISTS user_albums_saved (
    user_id VARCHAR(40),
    album_id VARCHAR(40),
    PRIMARY KEY (user_id, album_id)
);

CREATE TABLE IF NOT EXISTS user_songs_played (
    user_id VARCHAR(40),
    song_id VARCHAR(40),
    date_played DATETIME,

    PRIMARY KEY (user_id, song_id, date_played)
);

CREATE TABLE IF NOT EXISTS playlists (
    playlist_id VARCHAR(40),
    creator_id VARCHAR(40),
    date_created DATE,
    name VARCHAR(256),
    description VARCHAR(512),
    is_public TINYINT,
    num_followers INT,
    
    PRIMARY KEY (playlist_id)
);

CREATE TABLE IF NOT EXISTS playlist_songs (
    playlist_id VARCHAR(40),
    song_id VARCHAR(40),
    track_number INT,
    time_added DATE,
    PRIMARY KEY (playlist_id, track_number)
);

CREATE TABLE IF NOT EXISTS user_playlist_follows (
    user_id VARCHAR(40),
    playlist_id VARCHAR(40),
    PRIMARY KEY (user_id, playlist_id)
);


### PAYMENTS ###

CREATE TABLE IF NOT EXISTS payments (
    card_number VARCHAR(32),
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    cvv VARCHAR(4),
    expiration_date DATE,
    company VARCHAR(64),
    address_id VARCHAR(40),
    phone_number VARCHAR(32),

    PRIMARY KEY (card_number)
);

CREATE TABLE IF NOT EXISTS address (
    address_id VARCHAR(40),
    street VARCHAR(32),
    city VARCHAR(32),
    state VARCHAR(2),
    zip VARCHAR(10),

    PRIMARY KEY (address_id)
);

CREATE TABLE IF NOT EXISTS user_payments (
    user_id VARCHAR(40),
    payment_id VARCHAR(40),

    PRIMARY KEY (user_id, payment_id)
);

### FOR REVENUE SUMMARIES ###

CREATE TABLE IF NOT EXISTS revenue_summary_users (
    payment_id VARCHAR(40),
    payment_amount DECIMAL(10, 2),
    date_paid DATE
);

CREATE TABLE IF NOT EXISTS revenue_summary_ads (
    ad_id VARCHAR(40),
    num_monthly_views INT,
    payment_amount DECIMAL(10, 2),
    date_paid DATE
);

CREATE TABLE IF NOT EXISTS revenue_summary_songs (
    song_id VARCHAR(40),
    num_monthly_plays INT,
    payment_amount DECIMAL(10, 2),
    date_paid DATE
);

### DB ACCESS TABLES ###

CREATE TABLE IF NOT EXISTS db_users (
    db_user_id VARCHAR(40),
    hashed_password VARCHAR(256),
    email_address VARCHAR(64),
    request VARCHAR(512),
    artist_id VARCHAR(40),
    permission_artist TINYINT,
    permission_album TINYINT,
    permission_concert TINYINT,
    approved TINYINT,

    PRIMARY KEY (db_user_id)
);

### DEBUG ###

CREATE TABLE IF NOT EXISTS  test (
	id VARCHAR(256),
    name VARCHAR(256)
);
