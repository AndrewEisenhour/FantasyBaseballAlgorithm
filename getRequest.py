import requests
import json
league_id = 99962
year = 2023
leagueUrl = "https://fantasy.espn.com/apis/v3/games/flb/seasons/" + str(year) + "/segments/0/leagues/" + str(league_id)
playerDataUrl = "https://fantasy.espn.com/apis/v3/games/flb/seasons/" + str(year) + "/segments/0/leaguedefaults/1?view=kona_player_info"
playerUrl = "https://fantasy.espn.com/apis/v3/games/flb/seasons/" + str(year) + "/players?scoringPeriodId=0&view=players_wl"
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
                          "espn_s2": "AECYMvoKdqc5EXl7XHnD%2F0V5JL00t79IBxCw9hlQdX6poEdllC3EveSvM1jBuRyR8qu9uA6qgzu5B9O7cqN%2BDb1AhOPE%2FI6tTIjX6ScPhKdh3jnplcNvtqNmu4sBtx9YNjt3Lq7uMQViwmJ9rkmUJK9W26ZOdn8WK2qNhHp3msPKF31MYJ3jISUEJCfhcAci%2BpRjqjDIED7xL%2BXzWSnTKYhHkBRiyXLJF8ocmG6X9mkGnyGAjV8rUj88KAlnSSRJkfawwhojrQ3vkJPTGUfE2a3ZiF9MXhmw2qw60KMu67c1jQ%3D%3D"}

r = requests.get(playerDataUrl, cookies = espn_cookies, headers = espn_headers)
rawPlayerData = r.json()
with open('ESPNData.json', 'w') as outfile:
    json.dump(rawPlayerData, outfile)
    
espn_players = rawPlayerData['players']
player_details = espn_players[0]
