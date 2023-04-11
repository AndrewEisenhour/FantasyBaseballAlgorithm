# Fantasy Baseball Drafting Algorithm

Last Updated: 4/11/2023
Author: Andrew Eisenhour

## Premise

This program was designed in response to a frequent problem that I had in fantasy baseball. While preparing for a draft, I would often decide on a particular strategy. That often took the form of prioritizing certain categories based on the relative scarcity or abundance of certain stats. The problem with this was that after a draft began, often that plan would blow up. This could be from other players having the same plan, the plan being bad, or from following the plan even when I should abandon it. I created this algorithm to dynamically assess the values of players in a strategy-agnostic approach. What this means is that the algorithm assesses value during a draft based on how things are changing. The algorithm notices when stats are becoming scarce in one category and abundant in another, which means that it can adapt. The particulars of the mathematics and all of the factors included in it can be found in my upcoming paper.

## Setup


