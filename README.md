# Fantasy Baseball Drafting Algorithm

Last Updated: 4/11/2023
Author: Andrew Eisenhour

## Premise

This program was designed in response to a frequent problem that I had in fantasy baseball. While preparing for a draft, I would often decide on a particular strategy. That often took the form of prioritizing certain categories based on the relative scarcity or abundance of certain stats. The problem with this was that after a draft began, often that plan would blow up. This could be from other players having the same plan, the plan being bad, or from following the plan even when I should abandon it. I created this algorithm to dynamically assess the values of players in a strategy-agnostic approach. What this means is that the algorithm assesses value during a draft based on how things are changing. The algorithm notices when stats are becoming scarce in one category and abundant in another, which means that it can adapt. The particulars of the mathematics and all of the factors included in it can be found in my upcoming paper.

## Setup

Before running the actual algorithm, you must run "getRequest.py" and "processData.py". These files retrieve data from ESPN and put it in a readable format. In "getRequest.py" you must put in the correct year and leagueid in order to get correct results. Some cookies may need updated.

## Drafting

Once the data is prepared, you can run "Algorithm/Ranking.java" in order to start the draft. This will give initial values for all players based on their projections and then proceed to ask if you would like to draft. Enter "y" for yes, and then input the number of players in the draft and your draft positions. At this point, you can begin feeding the algoritm picks as they come in or at any time before your next pick. You can either type the number of the drafted player in the CLI, or you can copy and paste from ESPN's pick log. See "exampleDraft.txt" for the required format of draft picks. Simply copying and pasting from the pick log will achieve this format. The values of players will change after each pick. The top ten most valuable players will be shown first, followed by the most valuable at each position. The algorithm does not account for positional slots already being filled, so the user will need to account for that on their own. Screenshots will be attached at a later point.

![image](https://user-images.githubusercontent.com/54419359/231325572-d88d22a1-dcd6-453c-acdf-aa56da35cc6c.png)
![image](https://user-images.githubusercontent.com/54419359/231325617-53a79d82-6e95-490f-8e55-28083ae18e16.png)


## Helpful Sources
[https://github.com/cwendt94/espn-api](https://github.com/cwendt94/espn-api)  
[https://stmorse.github.io/journal/espn-fantasy-v3.html](https://stmorse.github.io/journal/espn-fantasy-v3.html)  
[https://jman4190.medium.com/how-to-use-python-with-the-espn-fantasy-draft-api-ecde38621b1b](https://jman4190.medium.com/how-to-use-python-with-the-espn-fantasy-draft-api-ecde38621b1b)
