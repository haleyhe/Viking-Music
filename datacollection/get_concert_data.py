import requests
from bs4 import BeautifulSoup
import re
import json

visitedUrls = {}
CONCERTS_PER_ARTIST = 8
CONCERTS_FILEPATH = './'
CONCERTS_JSON_FILEPATH = CONCERTS_FILEPATH + 'concerts.json'

def writeJsons():
	# construct the completed list of concerts
	concerts = []
	for key, concert in visitedUrls.items():
		concert['songkickUrl'] = key
		concerts.append(concert)

	# write the file
	concertsDict  = {}
	concertsDict['concerts'] = concerts
	with open(CONCERTS_JSON_FILEPATH, 'w') as f:
		json.dump(concertsDict, f, indent=4, sort_keys=True)


def getConcertDetails(url):
	try:
		concert = {}
		page = requests.get(url)
		html = BeautifulSoup(page.text, "html.parser")

		# venue info
		venue = {}
		venueInfo = html.find("div", class_="venue-info-details")
		venue['name'] = venueInfo.find("a", class_="url").getText()
		venueAddress = venueInfo.find("p", class_="venue-hcard").getText().split("\n")
		streetPattern = re.compile("^([0-9]+).+[a-z]+.+$")
		zipPattern = re.compile("^([0-9]+)$")
		cityPattern = re.compile("^.*,.*,.*$")
		for line in venueAddress:
			if zipPattern.match(line):
				venue['zip'] = line
			if cityPattern.match(line):
				lineSplit = line.split(", ")
				venue['city'] = lineSplit[0]
				venue['state'] = lineSplit[1]
			if streetPattern.match(line):
				venue['street'] = line
		concert['venue'] = venue

		# ticketing URL
		ticketingInfo = html.find("div", class_="ticket-wrapper")
		ticketingUrl = "https://www.songkick.com" + ticketingInfo.find("a")['href']
		ticketingUrl = ticketingUrl.replace("?", "")


		concert['ticketingUrl'] = ticketingUrl

		return concert

	except:
	 	return None

def searchForConcerts(artistPageUrl, artistId):
	page = requests.get(artistPageUrl)
	html = BeautifulSoup(page.text, "html.parser")
	concertsHtml = html.find("ul", class_="event-listings artist-focus")
	concertsHtml = concertsHtml.findAll("li")
	count = 0
	for i in range(0, len(concertsHtml), 2):
		if count >= CONCERTS_PER_ARTIST:
			break

		try:
			concertInfo = concertsHtml[i + 1]
			location = concertInfo.find("p", class_="location").getText()
			# skip non-US concerts
			if ", US" not in location:
				continue

			# if here, fetch full concert info,
			# including the time and venue info

			concertDetailedInfoUrl = "https://www.songkick.com" +  concertInfo.find("a")['href']

			if concertDetailedInfoUrl in visitedUrls:
				print("\tEncountered duplicate concert. Adding second artist for:")
				print("\t" + str(concertDetailedInfoUrl))
				visitedUrls[concertDetailedInfoUrl]['artists'].append(artistId)
				continue

			concert = getConcertDetails(concertDetailedInfoUrl)

			# if there was an error, just move on
			if concert == None:
				continue

			times = concertsHtml[i].findAll('time')
			for j in times:
				if j.has_attr('datetime'):
					concert['date'] = j['datetime'][0:10]

			# and finally add the artist
			concert['artists'] = []
			concert['artists'].append(artistId)

			print("\t" + str(concert))

			visitedUrls[concertDetailedInfoUrl] = concert

			count += 1
		except:
			# just ignore errors
			continue

# for each artist in the list, search for concerts
with open('artistUris.txt') as f:
	for line in f:
		line = line.rstrip()
		# skip empty lines or comments
		if line[0] == '#' or len(line) == 0:
			continue

		line = line.split('\t')
		artistId = line[0].split(':')[2]
		artistName = line[1]
		concertCalendarUrl = line[2]
		print("Searching concerts for " + artistName + "...")
		if concertCalendarUrl == "N/A":
			print("\tNo concerts available.")
		else:
			searchForConcerts(concertCalendarUrl, artistId)

# write the results (contained in visitedUrls dict)
print("Writing result to JSON...")
writeJsons()

print("Done.")