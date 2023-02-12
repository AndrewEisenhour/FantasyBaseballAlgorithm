import json
infile = open('ESPNData.json')
rawPlayerData = json.load(infile)
espn_players = rawPlayerData['players']
player_details = espn_players[0]
for player in espn_players:
    print(player['player']['fullName'])
    print(player['player']['stats'][0]['stats'])
