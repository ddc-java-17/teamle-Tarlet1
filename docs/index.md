---
title: Overview
description: "Project proposal or summary of in-progress/completed project."
menu: Overview
order: 0
---

{% include ddc-abbreviations.md %}

## Summary

The app I intend to build will be a guessing game, where the goal is to guess a professional sports organization. After each guess you send in, you will be given back a response showing you how close your guesses were for each category on the board. The categories that will be shown are location, division, conference, overall sport (if playing with multiple sports as options), and number of championships won. I'm still brainstorming on additional categories based on how many leagues you have selected. 

## Intended users & user stories
{: menu="Users" }

  fans of the NFL

    As a fan of the NFL, i want to use the NFL teams only option to test my knowledge of NFL franchises and see if i can figure out which team is the correct answer.

  fans of the NBA

    As a fan of the NBA, i want to use the NBA teams only option to see if i can figure out which NBA team is the correct answer given the context given back after my guesses.

  fans of the MLB

    As a MLB fan, i want use the MLB teams only option to see if im able to determine the right franchise when given context clues.

  fans of the NHL

    As a fan of the NHL, i want to use the NHL only option to learn more about NHL franchises by playing this game and seeing the correct answers in each category for the different teams.

  fans of multiple of above listed leagues

    As a sports fan in general, i want to use the all leagues option to truly put my sports knowledge to the test, and see if i can narrow it down to the right leagues and ultimately, right team.



## Functionality

The user will be able to select from which leagues they would like the list of teams to be from.
They will then submit there first guess.
the result given back will have all categories for the team they sent as the guess.
If the team they guessed and the team that is the correct answer are the same in one of those categories, the answer for the team they sent in that category will be green. If its close, but not quite right, it will be yellow. If its not close or right, it will be white.
Given those clues the user will submit another guess, and the same result will occur for the new team they submitted.
This continues until the user gets the team right, or until they ruin out of guesses, then the correct team will be shown to them 
The user will be given the option to play again, or to return home to select a new league/leagues.

## Persistent data
{: menu="Persistence" }

*USER 
  *Display name
  * percentage of games completed with correct guess 
  * which league last game was played on 
  * time stamp of last game played 
  * time stamp of last guess sent in a game if game was not completed before app was closed, so the game can be continued 
  * list of professional sports teams, broken up into leagues 


    
## Device/external services
{: menu="Services" }

*External services 
  *keyboard 
  *contacts
  *messages 
  *https://api-sports.io/

## Stretch goals and possible enhancements 
{: menu="Stretch goals" }

none i can think of at this time. 