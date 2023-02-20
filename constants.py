STATS_MAP = {
    0: 'AB',
    1: 'H',
    2: 'AVG',
    3: '2B',
    4: '3B',
    5: 'HR',
    6: 'XBH', # 2B + 3B + HR
    7: '1B',
    8: 'TB', # 1 * COUNT(1B) + 2 * COUNT(2B) + 3 * COUNT(3B) + 4 * COUNT(HR)
    9: 'SLG',
    10: 'B_BB',
    11: 'B_IBB',
    12: 'HBP',
    13: 'SF', # Sacrifice Fly
    14: 'SH', # Sacrifice Hit - i.e. Sacrifice Bunt
    15: 'SAC', # total sacrifices = SF + SH
    16: 'PA',
    17: 'OBP',
    18: 'OPS', # OBP + SLG
    19: 'RC', # Runs Created = TB * (H + BB) / (AB + BB)
    20: 'R',
    21: 'RBI',
    # 22: '',
    23: 'SB',
    24: 'CS',
    25: 'SB-CS', # net steals
    26: 'GDP',
    27: 'B_SO', # batter strike-outs
    28: 'PS', # pitches seen
    29: 'PPA', # pitches per plate appearance = PS / PA
    # 30: '',
    31: 'CYC',
    32: 'GP', # pitcher games pitched
    33: 'GS', # games started
    34: 'OUTS',  # divide by 3 for IP
    35: 'TBF',
    36: 'P',  # pitches
    37: 'P_H',
    38: 'OBA', # Opponent Batting Average
    39: 'P_BB',
    40: 'P_IBB', # intentional walks allowed
    41: 'WHIP',
    42: 'HBP',
    43: 'OOBP', # Opponent On-Base Percentage
    44: 'P_R',
    45: 'ER',
    46: 'P_HR',
    47: 'ERA',
    48: 'K',
    49: 'K/9',
    50: 'WP',
    51: 'BLK',
    52: 'PK', # pickoff
    53: 'W',
    54: 'L',
    55: 'WPCT', # Win Percentage
    56: 'SVO', # Save opportunity
    57: 'SV',
    58: 'BLSV', # BLown SaVe
    59: 'SV%', # Save percentage
    60: 'HLD',
    # 61: '',
    62: 'CG',
    63: 'QS', # Quality Starts
    # 64: '',
    65: 'NH', # No-hitters
    66: 'PG', # Perfect Games
    67: 'TC', # Total Chances = PO + A + E
    68: 'PO', # Put Outs
    69: 'A', # Assists
    70: 'OFA', # Outfield Assists
    71: 'FPCT', # Fielding Percentage
    72: 'E',
    73: 'DP', # Double plays turned
    # Not sure what to call the next four
    # 74 is games played where the batter's team won
    # 75 is the same except when the team lost
    # 76 and 77 are the same except for pitchers
    74: 'B_G_W', 
    75: 'B_G_L',
    76: 'P_G_W',
    77: 'P_G_L',
    # 78: ,
    # 79: ,
    # 80: ,
    81: 'G', # Games Played
    82: 'K/BB', # Strikeout to Walk Ratio
    83: 'SVHD', # Saves + Holds
    99: 'STARTER',
}