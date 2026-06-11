# Alien Lurk

## Project Summary

**Alien Lurk** is a sniper style hidden object shooting game made in **Greenfoot**. The player looks across a large city background and searches for aliens that are hidden in the scene. The goal is to find and shoot all aliens in each level before running out of lives.

The game has **10 levels**. As the levels increase, the aliens become harder to find because more aliens appear, some are smaller, and some blend into the background using camouflage colors. The player can pan around the map, zoom in with a sniper scope, aim using the crosshair, and shoot at the center target.

## How to Play

1. Run the Greenfoot scenario.
2. The title screen will appear.
3. Press **SPACE** to start the game.
4. Search the map for hidden aliens.
5. Use the sniper scope to zoom in and shoot aliens.
6. Clear all aliens in a level to continue to the next level.
7. Complete all 10 levels to win. A score will be generated during this process.

## Controls

| Action                          | Control                                          |
| ------------------------------- | ------------------------------------------------ |
| Start game                      | `SPACE`                                          |
| Pan view                        | `WASD` or arrow keys                             |
| Zoom in / out                   | `Z` or right mouse click                         |
| Shoot                           | `SPACE` or left mouse click while zoomed         |
| Continue after clearing a level | `SPACE` or `ENTER`                               |
| Restart after game over / win   | `R`                                              |
| Open level selector             | Click the small `LV` button near the bottom-left |

## Game Features

* **Large scrollable map:** The player can pan across the background to search for aliens.
* **Sniper scope mode:** The player can zoom in to aim more accurately.
* **Animated crosshair:** The crosshair pulses while the sniper scope is active.
* **Camouflaged aliens:** Some aliens sample the background color and blend into the map.
* **Increasing difficulty:** Higher levels include more aliens and harder alien types.
* **HUD display:** The game shows level, score, lives, shots, and aliens remaining.
* **End screens:** The game displays a win screen or game-over screen with the final score.
* **Level selector:** The player can jump to any level using the level selector panel.

## Teacher Notes / Cheat Mode

There is a built-in level selector that can be used as a cheat or testing tool.

To use it:

1. Start the game by pressing **SPACE**.
2. Click the small `LV` button near the bottom-left of the screen.
3. Choose any level from 1 to 10.
4. To quickly test the ending, jump to **Level 10** and clear the final level.

This is useful for testing the later levels and checking the final win screen without playing through all 10 levels from the beginning.

## Files / Main Classes

| Class            | Purpose                                                                                                    |
| ---------------- | ---------------------------------------------------------------------------------------------------------- |
| `SniperWorld`    | Main world class that controls the game state, levels, scoring, zooming, shooting, and win/loss conditions |
| `Alien`          | Represents each alien, including camouflage, animation, peeking, and death animation                       |
| `DisplayColumn`  | Draws the HUD, score, lives, alien counter, and game messages                                              |
| `ZoomView`       | Draws the sniper scope overlay                                                                             |
| `CrosshairActor` | Draws the animated crosshair in the center of the scope                                                    |
| `LevelSelector`  | Allows the player to jump to different levels                                                              |
| `TitleScreen`    | Displays the starting title page                                                                           |
| `EndScreen`      | Displays the final win or game-over screen                                                                 |

## Assets Used

This project uses custom image assets, including:

* `background_final.png`
* `titlePage.png`
* `EndingBackground.png`

These images are used for the main map, title screen, and ending screen.

## Goal of the Project

The goal of this project was to create a simple but complete Greenfoot game with multiple levels, interactive controls, visual effects, and a clear win/loss system. The game combines hidden object searching with sniper style aiming to make the gameplay more challenging and engaging.
