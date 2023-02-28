import json
from constants import STATS_MAP
infile = open('ESPNData.json')
pitcherOutfile = open('pitchers2.txt', 'w')
batterOutfile = open('batters2.txt', 'w')
rawPlayerData = json.load(infile)
espn_players = rawPlayerData['players']
player_details = espn_players[0]
player_line = ""
batter_categories = ['20', '5', '21', '23', '2']
pitcher_categories = ['48', '53', '57', '47', '41']
for player in espn_players:
    player_line = str(player['id'])
    player_line += ":" + str(player['player']['draftRanksByRankType']['STANDARD']['rank'])
    player_line += ":" + " ".join(str(x) for x in player['player']['eligibleSlots'])
    player_line += ":" + player['player']['fullName']
    player_data = player['player']['stats'][0]['stats']
    if '0' in player_data:
        for stat in batter_categories:
            if stat in player_data:
                player_line += ":" + str(player_data[stat])
            else:
                player_line += ":0"
        batterOutfile.write(player_line + '\n')
        #print(player_line)
    if '34' in player_data:
        for stat in pitcher_categories:
            if stat in player_data:
                player_line += ":" + str(player_data[stat])
            else:
                player_line += ":0"
        pitcherOutfile.write(player_line + '\n')
        #print(player_line)
    '''for category, stat in player['player']['stats'][0]['stats'].items():
        player_line += ":" + str(stat)
    print(player_line)'''
