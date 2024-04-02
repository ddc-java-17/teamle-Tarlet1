---
title: Current State
description: "Current state of completion, known deficiencies, and test environments used."
menu: State
order: 40
---

{% include ddc-abbreviations.md %}

## Completion state
{: menu="Completion" }

> The app currently runs a game based on the sport and league you choose, allowing you to submit guesses from an Auto complete text view of all teams in a selected league, and provides data on the team name, location, the year the team was formed, and the primary color of the team you picked. If the team you selected does not have one of those categories in common with the secret team, that column on the guess list will be red. If the team you picked has one of those categories in common with the secret team, that column on the guess list will turn green. You make guesses using the provided hints until you correctly guess the secret team. A success message then pops up. You can then click anywhere on the screen to make the success message disappear and start a new game, go to the setting to change your sport or league preference, or go to the scoreboard to see a list of all the games you've played ranked based on amount of teams in that league, amount of guesses taken to correctly guess the secret team, the duration of the game, and a timestamp of when the game was played.

## Known deficiencies
{: menu="Deficiencies" }

* Certain listed sports don't populate Auto complete text view, not allowing you to submit a guess. Works only for team-based sports.

* Game crashes if start a new game is selected before going to settings screen to set preferences first, sometime still crashes if settings are not gone to first even if preferences were set before.

* Sport and league selection have a bug that sometimes won't allow you to select a certain sport or league, must first go select another sport or league and then go back to the desired league or sport to have it set as preference.

* Sport and league preferences are reset to having nothing selected when phone is rotated. 

* Auto complete text view covers whole screen when phone is rotated during gameplay, blocking list of guesses unless you back out of the list/ keyboard. 

* Certain teams do not have a primary team color attribute set in the API, so during a game the primary team color column for those teams returns empty when those teams are used for a guess.

* The primary team color returns a written out "#000000" format of the color, not a literal color for you to see. Would play better if game returned a literal color.

## Stretch goals and possible enhancements
{: menu="Stretch goals" }

* Have primary team color return a literal color swatch, rather than a "#000000" code.

* Add more/ better hints for each guessed team to make it easier to figure out the secret team.

* Filter list of sports and leagues to only include sports and teams that are team-based and will populate the auto complete text view.

* Implement messaging to be able to send friends your scores and challenge them to beat it.

## Test environments used
{: menu="Environments" }

* Google Pixel 7 API 34 emulator 

* Samsung SM-N981U Samsung galaxy note 20 physical device.

## Technical requirements and dependencies 
{: menu="Dependencies"}

* Target is Android API 34, can support anything android 9 or higher. 