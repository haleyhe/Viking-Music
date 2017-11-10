CREATE TABLE IF NOT EXISTS Artists (
    artistId VARCHAR(40),
    name VARCHAR(64),
    bio TEXT,
    numFollowers INT DEFAULT 0,
    royaltyRate DECIMAL(5,3) DEFAULT 0.1,
    PRIMARY KEY (artistId)
);

CREATE TABLE IF NOT EXISTS ArtistNames (
	artistId VARCHAR(40),
    firstName VARCHAR(64),
    lastName VARCHAR(64),
    
    PRIMARY KEY (artistId, firstName, lastName)
);

CREATE TABLE IF NOT EXISTS ArtistGenres (
    artistId VARCHAR(40),
    genre VARCHAR(32),
    PRIMARY KEY (artistId, genre)
);

CREATE TABLE IF NOT EXISTS Songs (
    songId VARCHAR(40),
    name VARCHAR(256),
    duration INT,
    explicit TINYINT,
    discNumber INT,
    trackNumber INT,
    lyrics TEXT,
    numPlays INT DEFAULT 0,

    PRIMARY KEY (songId)
);

CREATE TABLE IF NOT EXISTS SongArtists (
    songId VARCHAR(40),
    artistId VARCHAR(40),
    PRIMARY KEY (songId, artistId)
);

CREATE TABLE IF NOT EXISTS Albums (
    albumId VARCHAR(40),
    name VARCHAR(256),
    releaseDate DATE,
    PRIMARY KEY (albumId)
);

CREATE TABLE IF NOT EXISTS AlbumArtists (
    albumId VARCHAR(40),
    artistId VARCHAR(40),
    PRIMARY KEY (albumId, artistId)
);

CREATE TABLE IF NOT EXISTS AlbumSongs (
    albumId VARCHAR(40),
    songId VARCHAR(40),
    PRIMARY KEY (albumId, songId)
);

CREATE TABLE IF NOT EXISTS Concerts (
    concertId VARCHAR(40),
    name VARCHAR(256),
    concertDate DATETIME,
    venueId VARCHAR(40),
    ticketingUrl VARCHAR(256),
    PRIMARY KEY (concertId)
);

CREATE TABLE IF NOT EXISTS ConcertVenues (
    venueId VARCHAR(40),
    name VARCHAR(256),
    street VARCHAR(40),
    zip VARCHAR(10),
    PRIMARY KEY (venueId)
);

CREATE TABLE IF NOT EXISTS ConcertArtists (
    concertId VARCHAR(40),
    artistId VARCHAR(40),
    PRIMARY KEY (concertId, artistId)
);

CREATE TABLE IF NOT EXISTS Users (
    userId VARCHAR(40),
    username VARCHAR(32),
    hashedPassword VARCHAR(256),
    emailAddress VARCHAR(64),
    zip VARCHAR(10),
    birthDate DATE,
    premium TINYINT,
    admin TINYINT,
    facebookId VARCHAR(256),

    PRIMARY KEY (userId)
);

CREATE TABLE IF NOT EXISTS ForgotPasswordRequests (
    requestId VARCHAR(40),
    userId VARCHAR(40),
    expirationDate DATETIME,
    processed TINYINT,
    
    PRIMARY KEY (requestId)
);

CREATE TABLE IF NOT EXISTS UserArtistsFollowed (
    userId VARCHAR(40),
    artistId VARCHAR(40),
    dateFollowed DATETIME,
    PRIMARY KEY (userId, artistId)
);

CREATE TABLE IF NOT EXISTS UserSongsSaved (
    userId VARCHAR(40),
    songId VARCHAR(40),
    dateSaved DATETIME,
    PRIMARY KEY(userId, songId)
);

CREATE TABLE IF NOT EXISTS UserAlbumsSaved (
    userId VARCHAR(40),
    albumId VARCHAR(40),
    dateSaved DATETIME,
    PRIMARY KEY (userId, albumId)
);

CREATE TABLE IF NOT EXISTS UserSongsPlayed (
    userId VARCHAR(40),
    songId VARCHAR(40),
    datePlayed DATETIME,
    clickedPlay TINYINT,

    PRIMARY KEY (userId, songId, datePlayed)
);

CREATE TABLE IF NOT EXISTS Playlists (
    playlistId VARCHAR(40),
    creatorId VARCHAR(40),
    dateCreated DATETIME,
    name VARCHAR(256),
    description VARCHAR(512),
    isPublic TINYINT,
    numFollowers INT,
    
    PRIMARY KEY (playlistId)
);

CREATE TABLE IF NOT EXISTS PlaylistSongs (
    playlistId VARCHAR(40),
    songId VARCHAR(40),
    trackNumber INT,
    dateAdded DATETIME,
    PRIMARY KEY (playlistId, trackNumber)
);

CREATE TABLE IF NOT EXISTS UserPlaylistsFollowed (
    userId VARCHAR(40),
    playlistId VARCHAR(40),
    dateFollowed DATETIME,
    PRIMARY KEY (userId, playlistId)
);


### PAYMENTS ###

CREATE TABLE IF NOT EXISTS Payments (
    cardNumber VARCHAR(32),
    firstName VARCHAR(64),
    lastName VARCHAR(64),
    cvv VARCHAR(4),
    expirationDate DATE,
    company VARCHAR(32),
    street VARCHAR(40),
    zip VARCHAR(10),
    phoneNumber VARCHAR(32),

    PRIMARY KEY (cardNumber)
);

CREATE TABLE IF NOT EXISTS Addresses (
    street VARCHAR(32),
    city VARCHAR(32),
    state VARCHAR(2),
    zip VARCHAR(10),

    PRIMARY KEY (street, zip)
);

CREATE TABLE IF NOT EXISTS UserPayments (
    userId VARCHAR(40),
    cardNumber VARCHAR(32),

    PRIMARY KEY (userId, cardNumber)
);

### FOR REVENUE SUMMARIES ###

CREATE TABLE IF NOT EXISTS RevenueSummaryUsers (
    userId VARCHAR(40),
    cardNumber VARCHAR(32),
    paymentAmount DECIMAL(10, 2),
    datePaid DATE,
    PRIMARY KEY (cardNumber, datePaid)
);

CREATE TABLE IF NOT EXISTS RevenueSummaryAds (
    adId VARCHAR(40),
    numMonthlyViews INT,
    paymentAmount DECIMAL(10, 2),
    datePaid DATE,
    PRIMARY KEY (adId, datePaid)
);

CREATE TABLE IF NOT EXISTS RevenueSummarySongs (
    songId VARCHAR(40),
    numMonthlyPlays INT,
    paymentAmount DECIMAL(10, 2),
    datePaid DATE,
    PRIMARY KEY (songId, datePaid)
);

### DB ACCESS TABLES ###

CREATE TABLE IF NOT EXISTS DBUsers (
    dbUserId VARCHAR(40),
    hashedPassword VARCHAR(256),
    emailAddress VARCHAR(64),
    request VARCHAR(512),
    artistId VARCHAR(40),
    permissionArtist TINYINT,
    permissionAlbum TINYINT,
    permissionConcert TINYINT,
    approved TINYINT,

    PRIMARY KEY (dbUserId)
);

### DEBUG ###

CREATE TABLE IF NOT EXISTS  Test (
	id VARCHAR(256),
    name VARCHAR(256)
);
