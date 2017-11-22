# Python 2.7 script for retrieving data from Spotify.
# Loads artist URIs from artistUris.txt and obtains:
#   -Info for Artist
#   -Info for all of Artist's Albums
#   -Info for all of Artist's Songs
#   -Collects Genre identifiers for all of the given Artists
# Requires Spotipy: https://spotipy.readthedocs.io/en/latest/
# And pylast: https://github.com/pylast/pylast

import spotipy
import spotipy.util
import pylast
import distutils.dir_util
import urllib2
import json
import os
import json
import requests
from bs4 import BeautifulSoup

# Filepath constants
ARTISTS_FILEPATH = './artist/'
ARTISTS_IMAGES_FILEPATH = ARTISTS_FILEPATH + 'images/'
ARTISTS_JSON_FILEPATH = ARTISTS_FILEPATH + 'artists.json'
ALBUMS_FILEPATH = './album/'
ALBUMS_IMAGES_FILEPATH = ALBUMS_FILEPATH + 'images/'
ALBUMS_JSON_FILEPATH = ALBUMS_FILEPATH + 'albums.json'
SONGS_FILEPATH = './'
SONGS_MP3_FILEPATH = SONGS_FILEPATH + 'mp3/'
SONGS_JSON_FILEPATH = SONGS_FILEPATH + 'songs.json'
GENRES_FILEPATH = './genres/'
GENRES_JSON_FILEPATH = GENRES_FILEPATH + 'genres.json'

# Spotify API authentication credentials -- replace with your own
SPOTIFY_USERNAME = 'bryankoelbel'
SPOTIFY_SCOPE = 'user-library-read'
SPOTIFY_CLIENT_ID = '05e7c6482b334e56a6e021f382fb2ae1'
SPOTIFY_CLIENT_SECRET = 'df3690b7878147f381883eeb664e5af2'
SPOTIFY_REDIRECT_URI = 'http://example.com/callback/'

# LastFM API authentication credentials
LASTFM_USERNAME = 'bryankoelbel'
LASTFM_PASSWORD = pylast.md5('INSERT PASSWORD HERE!!!!!!!!!!!')
LASTFM_API_KEY = 'd78627185599686b29173497e4c3a777'
LASTFM_API_SECRET = 'cf3998c95df000233de80d2f12ac21a6'

# Genius API creds
GENIUS_ACCESS_TOKEN = '5dEY76JPQ2Zker-heyhaPmS3wrEFWuEG5ouofdLL65WTK1ShB0O2vaTwQTmhOmpL'

# limit number of albums per artist
ALBUMS_PER_ARTIST = 4

# other settings
DOWNLOAD_IMAGES = True
DOWNLOAD_SONGS = False
DOWNLOAD_LYRICS = True

# Banned album words (so we only get one version of full albums)
bannedWords = ['Deluxe', 'Expanded', 'Special', 'Edition', 'EP', 'Ep', 'Single', 'Live', 'Tour', 'Remastered', 'Spotify', 'Sessions']

# Data structures for results
foundArtistIds = []
artists = {}
artists['artists'] = []
foundAlbumIds = []
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

# handles LastFM authentication
def setupLastFmApi():
    return pylast.LastFMNetwork(api_key=LASTFM_API_KEY, api_secret=LASTFM_API_SECRET, username=LASTFM_USERNAME, password_hash=LASTFM_PASSWORD)

# makes directories for file outputs
def makeDirs():
    distutils.dir_util.mkpath(ARTISTS_IMAGES_FILEPATH)
    distutils.dir_util.mkpath(ALBUMS_IMAGES_FILEPATH)
    distutils.dir_util.mkpath(SONGS_MP3_FILEPATH)
    distutils.dir_util.mkpath(GENRES_FILEPATH)

# downloads file at url to file at filepath
def downloadFile(filepath, url):
    f = open(filepath, 'wb')
    f.write(urllib2.urlopen(url).read())
    f.close()
    return filepath

def getLyricsUrl(artistName, songName):
    # SOME OF THIS CODE FROM
    # https://github.com/jasonqng/genius-lyrics-search
    try:
        querystring = "http://api.genius.com/search?q=" + urllib2.quote(artistName + ' ' + songName) + "&page=1"
        request = urllib2.Request(querystring)
        request.add_header("Authorization", "Bearer " + GENIUS_ACCESS_TOKEN)
        request.add_header("User-Agent", "curl/7.9.8 (i686-pc-linux-gnu) libcurl 7.9.8 (OpenSSL 0.9.6b) (ipv6 enabled)")
        response = urllib2.urlopen(request, timeout=4) #timeout set to 4 seconds; automatically retries if times out
        raw = response.read()
        json_obj = json.loads(raw)
        body = json_obj["response"]["hits"]
        
        # No results
        if len(body) == 0:
            return None
        
        # Take a chance and assume the first result is correct
        url = body[0]["result"]["url"]
        return url
    except:
        return None

def getLyrics(artistName, songName):
    url = getLyricsUrl(artistName, songName)
    if url:
        try:
            # Scrape the lyrics from the url (thanks https://bigishdata.com/2016/09/27/getting-song-lyrics-from-geniuss-api-scraping/)
            page = requests.get(url)
            html = BeautifulSoup(page.text, "html.parser")
            # remove script tags that they put in the middle of the lyrics
            [h.extract() for h in html('script')]
            # at least Genius is nice and has a tag called 'lyrics'!
            lyrics = html.find("div", class_="lyrics").get_text() #updated css where the lyrics are based in HTML
            if lyrics:
                return lyrics
            else:
                return None
        except:
            return None
    else:
        return None

def getBio(artistName):
    artist = pylast.Artist(artistName, lastfm)
    if artist:
        bio = artist.get_bio_content(language="en")
        if bio:
            return BeautifulSoup(bio, "lxml").text
    return None

# obtains info for Artist with the given Spotify ID
def processArtist(id):
    if id in foundArtistIds:
        return
    foundArtistIds.append(id)
    artist = spotify.artist(id)
    result = {}

    # basic info
    result['name'] = artist['name']
    result['id'] = id
    result['genres'] = artist['genres']
    # add the genres to the master genre list as well
    genres['genres'].update(result['genres'])
    
    # artist bio
    result['bio'] = getBio(result['name'])

    # images
    result['image_url'] = artist['images'][0]['url']
    # save the Artist image locally
    result['image_filepath'] = ARTISTS_IMAGES_FILEPATH + id + '.jpg'
    if DOWNLOAD_IMAGES:
        downloadFile(result['image_filepath'], result['image_url'])
    
    artists['artists'].append(result)
    return

# obtains info for all Albums for Artist with the given Spotify ID
def processAlbumsForArtist(id):
    count = 0
    albumResult = spotify.artist_albums(id, album_type='album')
    for album in albumResult['items']:
        if count == ALBUMS_PER_ARTIST:
            continue
        
        # skip duplicates
        if album['id'] in foundAlbumIds:
            continue
        
        print('\tProcessing Album ' + album['id'] + '...')
        result = {}
        foundAlbumIds.append(album['id'])
        # get additional info with API call
        album = spotify.album(album['id'])
        
        # basic info
        result['name'] = album['name']
        
        # skip banned words
        if any(word in result['name'] for word in bannedWords):
            print('\t\t(Skipped)')
            continue
        
        result['id'] = album['id']
        result['artists'] = []
        for artist in album['artists']:
            result['artists'].append(artist['id'])
            if str(artist['id']) not in foundArtistIds:
                print('\t\tRecursively searching for artist ' + artist['id'] + ' (' + artist['name'] + ')')
                processArtist(artist['id'])
        result['release_date'] = album['release_date']
        result['copyrights'] = album['copyrights'][0]['text']

        # images
        result['image_url'] = album['images'][0]['url']
        result['image_filepath'] = ALBUMS_IMAGES_FILEPATH + result['id'] + '.jpg'
        if DOWNLOAD_IMAGES:
            downloadFile(result['image_filepath'], result['image_url'])
        
        # Get songs for album
        processSongsForAlbum(result['id'])
        
        albums['albums'].append(result)
        count += 1

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
        main_artist = None
        for artist in track['artists']:
            if main_artist == None:
                main_artist = artist['name']
            result['artists'].append(artist['id'])
        result['track_number'] = track['track_number']
        result['disc_number'] = track['disc_number']
        result['explicit'] = track['explicit']
        
        # song preview
        result['preview_url'] = track['preview_url']
        result['preview_filepath'] = SONGS_MP3_FILEPATH + result['id'] + '.mp3'
        if DOWNLOAD_SONGS:
            downloadFile(result['preview_filepath'], result['preview_url'])
        
        # song lyrics
        if DOWNLOAD_LYRICS:
            result['lyrics'] = getLyrics(main_artist, result['name'])
        
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
lastfm = setupLastFmApi()
makeDirs()

# for each artist in the list, get the Artist info, Album info, and Songs for all Albums
with open('artistUris3.txt') as f:
    for line in f:
        line = line.rstrip()
        # skip empty lines or comments
        if line[0] == '#' or len(line) == 0:
            continue
        name = line.split('\t')[1]
        line = line.split('\t')[0]
        line = line.split(':')[2]
        # Process the Artist
        print('Processing Artist ' + line + '(' + name + ')...')
        processArtist(line)
        # Process all the Albums/Songs
        processAlbumsForArtist(line)
        print('Processed.')

# write the results
writeJsons()

print("Done.")
