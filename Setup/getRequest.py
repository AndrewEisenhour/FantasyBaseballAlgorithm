# Retrieves data from ESPN
import requests
import json
league_id = 99962
year = 2025
leagueUrl = "https://fantasy.espn.com/apis/v3/games/flb/seasons/" + str(year) + "/segments/0/leagues/" + str(league_id)
playerDataUrl = "https://lm-api-reads.fantasy.espn.com/apis/v3/games/flb/seasons/" + str(year) + "/segments/0/leaguedefaults/1?view=kona_player_info"
playerUrl = "https://lm-api-reads.fantasy.espn.com/apis/v3/games/flb/seasons/" + str(year) + "/players?scoringPeriodId=0&view=players_wl"
filters = { "players": { "limit": 500, "sortDraftRanks": { "sortPriority": 100, "sortAsc": True, "value": "STANDARD" } } }
espn_headers = {
 "Connection": "keep-alive",
 "Accept": "application/json, text/plain, */*",
 "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36",
 "x-fantasy-filter": json.dumps(filters),
 "x-fantasy-platform": "kona-PROD-1dc40132dc2070ef47881dc95b633e62cebc9913",
 "x-fantasy-source": "kona"
}
espn_cookies={"swid": "{2E92D10F-BFFF-42DA-92D1-0FBFFFE2DA5B}",
                          "espn_s2": "AEBhEXLuxvFgEPQctxi7AZMOIEi3ebVipjooOCnlp%2Fwjv2gmssxL8U%2B15NUWP55lCoX%2FuK7XEdN50Bf7KlSYq6jc1hEYI45OStV5Ozvl9mXEqEtx%2F9TOfxF5c2IfBfi6Kw%2Fm%2BXkDaeMsdCem2ZyasrNfozdlwegExrmh66TDxnOTNamca%2FE8YTIJX9Rr886PKbXXgNQYBbww1tsB28vniMCPvG8rW7XcDzHURGVK16IddGg5qzd4lvOPcKNlDF4pUE1FDntCdLg9AUxl27B0gALgwN%2B9lqDeuhoWKxhxyvqNVA%3D%3D"}

r = requests.get(playerDataUrl, cookies = espn_cookies, headers = espn_headers)
print(r.text)
rawPlayerData = r.json()
with open('Setup/ESPNData.json', 'w') as outfile:
    json.dump(rawPlayerData, outfile)
    
espn_players = rawPlayerData['players']
player_details = espn_players[0]
