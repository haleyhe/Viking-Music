# Python 2.7 script for retrieving data from Spotify.
# Loads artist URIs from artistUris.txt and obtains:
#   -Info for Artist
#   -Info for all of Artist's Albums
#   -Info for all of Artist's Songs
#   -Collects Genre identifiers for all of the given Artists
# Requires Spotipy: https://spotipy.readthedocs.io/en/latest/

import spotipy
import spotipy.util
import distutils.dir_util
import urllib2
import json
import os

# Filepath constants
ARTISTS_FILEPATH = './data/artists/'
ARTISTS_IMAGES_FILEPATH = ARTISTS_FILEPATH + 'images/'
ARTISTS_JSON_FILEPATH = ARTISTS_FILEPATH + 'artists.json'
ALBUMS_FILEPATH = './data/albums/'
ALBUMS_IMAGES_FILEPATH = ALBUMS_FILEPATH + 'images/'
ALBUMS_JSON_FILEPATH = ALBUMS_FILEPATH + 'albums.json'
SONGS_FILEPATH = './data/songs/'
SONGS_JSON_FILEPATH = SONGS_FILEPATH + 'songs.json'
GENRES_FILEPATH = './data/genres/'
GENRES_JSON_FILEPATH = GENRES_FILEPATH + 'genres.json'

# Spotify API authentication credentials -- replace with your own
SPOTIFY_USERNAME = 'bryankoelbel'
SPOTIFY_SCOPE = 'user-library-read'
SPOTIFY_CLIENT_ID = '05e7c6482b334e56a6e021f382fb2ae1'
SPOTIFY_CLIENT_SECRET = 'df3690b7878147f381883eeb664e5af2'
SPOTIFY_REDIRECT_URI = 'http://example.com/callback/'

# Data structures for results
artists = {}
artists['artists'] = []
albums = {}
albums['albums'] = []
songs = {}
songs['songs'] = []
genres = {}
# store genres as a set to prevent repeat entries
genres['genres'] = set()

# handles Spotify API authentication
def setupApi():
    token = spotipy.util.prompt_for_user_token(SPOTIFY_USERNAME,SPOTIFY_SCOPE,client_id=SPOTIFY_CLIENT_ID,client_secret=SPOTIFY_CLIENT_SECRET,redirect_uri=SPOTIFY_REDIRECT_URI)
    return spotipy.Spotify(auth=token)

# makes directories for file outputs
def makeDirs():
    distutils.dir_util.mkpath(ARTISTS_IMAGES_FILEPATH)
    distutils.dir_util.mkpath(ALBUMS_IMAGES_FILEPATH)
    distutils.dir_util.mkpath(SONGS_FILEPATH)
    distutils.dir_util.mkpath(GENRES_FILEPATH)

# downloads image at url to file at filepath
def downloadImage(filepath, url):
    f = open(filepath, 'wb')
    f.write(urllib2.urlopen(url).read())
    f.close()
    return filepath

# obtains info for Artist with the given Spotify ID
def processArtist(id):
    artist = spotify.artist(id)
    result = {}

    # basic info
    result['name'] = artist['name']
    result['id'] = id
    result['genres'] = artist['genres']
    # add the genres to the master genre list as well
    genres['genres'].update(result['genres'])

    # images
    result['image_url'] = artist['images'][0]['url']
    # save the Artist image locally
    result['image_filepath'] = ARTISTS_IMAGES_FILEPATH + id + '.jpg'
    downloadImage(result['image_filepath'], result['image_url'])
    
    artists['artists'].append(result)
    return

# obtains info for all Albums for Artist with the given Spotify ID
def processAlbumsForArtist(id):
    albumResult = spotify.artist_albums(id, album_type='album')
    for album in albumResult['items']:
        print('\tProcessing Album ' + album['id'] + '...')
        result = {}
        # get additional info with API call
        album = spotify.album(album['id'])
        
        # basic info
        result['name'] = album['name']
        result['id'] = album['id']
        result['artists'] = []
        for artist in album['artists']:
            result['artists'].append(artist['id'])
        result['release_date'] = album['release_date']
        result['copyrights'] = album['copyrights'][0]['text']

        # images
        result['image_url'] = album['images'][0]['url']
        result['image_filepath'] = ALBUMS_IMAGES_FILEPATH + result['id'] + '.jpg'
        downloadImage(result['image_filepath'], result['image_url'])
        
        # Get songs for album
        processSongsForAlbum(result['id'])
        
        albums['albums'].append(result)

# obtains info for all Songs for Album with the given Spotify ID
def processSongsForAlbum(id):
    tracks = spotify.album_tracks(id)
    for track in tracks['items']:
        result = {}

        # basic info
        result['name'] = track['name']
        result['id'] = track['id']
        result['duration'] = track['duration_ms']
        result['album'] = id
        result['artists'] = []
        for artist in track['artists']:
            result['artists'].append(artist['id'])
        result['track_number'] = track['track_number']
        result['disc_number'] = track['disc_number']
        result['explicit'] = track['explicit']
        
        # song preview
        result['preview_url'] = track['preview_url']
        
        songs['songs'].append(result)

# writes resulting Artist, Album, Song, and Genre data to JSON files
def writeJsons():
    with open(ARTISTS_JSON_FILEPATH, 'w') as f:
        json.dump(artists, f, indent=4, sort_keys=True)
    with open(ALBUMS_JSON_FILEPATH, 'w') as f:
        json.dump(albums, f, indent=4, sort_keys=True)
    with open(SONGS_JSON_FILEPATH, 'w') as f:
        json.dump(songs, f, indent=4, sort_keys=True)
    # convert the set to a list
    genres['genres'] = list(genres['genres'])
    with open(GENRES_JSON_FILEPATH, 'w') as f:
        json.dump(genres, f, indent=4, sort_keys=True)
    return


# setup
spotify = setupApi()
makeDirs()

# for each artist in the list, get the Artist info, Album info, and Songs for all Albums
with open('artistUris.txt') as f:
    for line in f:
        line = line.rstrip()
        # skip empty lines or comments
        if line[0] == '#' or len(line) == 0:
            continue
        line = line.split(':')[2]
        # Process the Artist
        print('Processing Artist ' + line + '...')
        processArtist(line)
        # Process all the Albums/Songs
        processAlbumsForArtist(line)
        print('Processed.')

# write the results
writeJsons()

print("Done.")
