---
title: Overview
description: "Project proposal or summary of in-progress/completed project."
menu: Overview
order: 0
---

{% include ddc-abbreviations.md %}

## Summary

The app I intend to build will be a guessing game, where the goal is to guess a professional sports organization. After each guess you send in, you will be given back a response showing you how close your guesses were for each category on the board. The categories that will be shown are location, division, conference, overall sport (if playing with multiple sports as options), and number of championships won. I'm still brainstorming on additional categories based on how many leagues you have selected. 

The functionality of the app would include picking a league for the pool to include. The game would consist of a drop-down list of all teams in the selected leagues, and would narrow itself down to match the characters typed by the user. There would be a result screen showing you how many guesses you took or what the correct answer is if you run out of guesses. The game will also track stats that you could check from the profile screen. It will also include a messaging feature, where you can challenge friends of family to beat your score. 

## Intended users & user stories
{: menu="Users" }
* fans of the NFL

    > As a fan of the NFL, i want to use the NFL teams only option to test my knowledge of NFL franchises and see if i can figure out which team is the correct answer.

* People who enjoy puzzles

    > As a person who likes to push myself mentally, I want to use this app to push my sports knowledge to the limit and see the fewest amount of guesses i'd need to solve a game.
  
* Overall sports fans 

    > As a sports fan in general, i want to use the all leagues option to truly put my sports knowledge to the test, and see if i can narrow it down to the right leagues and ultimately, right team as quickly as possible.



## Functionality

The user will be able to select from which leagues they would like the list of teams to be from.
They will then submit there first guess.
the result given back will have all categories for the team they sent as the guess.
If the team they guessed and the team that is the correct answer are the same in one of those categories, the answer for the team they sent in that category will be green. If it is not right, it will be red.
Given those clues the user will submit another guess, and the same result will occur for the new team they submitted.
This continues until the user gets the team right, then a successful pop-up will be shown.

## Persistent data
{: menu="Persistence" }

  * User
  * Display name 
  * which league last game was played on 
  * time stamp of last game played 
  * time stamp of last guess sent in a game if game was not completed before app was closed, so the game can be continued 
  * list of professional sports teams, broken up into leagues 


    
## Device/external services
{: menu="Services" }

  * Google sign-in service (Mandatory)
  > This service will be used to sign-in/out of your account on our app.

  > If the service is interrupted, players will not be able to access their accounts.

  * Internet access (Mandatory)
    > This service will connect our app to the internet, allowing you to sign-in to your account and track stats.

    > If this service is interrupted, the user will not be able to get into a game.

  * Messaging services (optional) (stretch goal)
    > Send simple data to other apps  |  Android Developers

    > This service will allow users to send friends/family texts messages inviting them to beat there score for a played game.

    > If this service is interrupted or not allowed, users won’t be able to use texts as a means of inviting other users to play a game.

  * https://www.thesportsdb.com/free_sports_api
    > API used to populate the list of sports and leagues available.
  
    > Provides the information for the guess field categories for secret team and all guessed teams.   

## Stretch goals and possible enhancements 
{: menu="Stretch goals" }

> Have primary team color return a literal color swatch, rather than a "#000000" code.

> Add more/ better hints for each guessed team to make it easier to figure out the secret team.

> Filter list of sports and leagues to only include sports and teams that are team-based and will populate the auto complete text view.

> Implement messaging to be able to send friends your scores and challenge them to beat it.
