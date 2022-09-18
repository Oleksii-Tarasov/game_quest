# **Escape Quest**
___

### Description
Web application "Escape Quest" - is a text game quest, the history of which develops depending on the player’s 
decisions. The main goal of the game is to get out of the dungeon. It can be achieved in several ways: to go the right 
way or to use the right item in the battle with the boss. There are different endings in the game.

_Game Options:_
- create a character with a name;
- move freely in locations (if this is allowed);
- collect objects scattered on locations;
- use the found items in the boss fight;
- replay the game after winning or losing.
___
### Warnings
Some locations feature music, sounds and animated screamers.
___
### Application launch
**_1 way - Follow the link:_** https://gamequest.fly.dev/escape_quest/

**_2 way - Run on your local computer:_** `escape_quest.war` file in the `dist` project directory needs to be 
deployed into tomcat 9 version. Then use context to run: http://localhost:8080/escape_quest/ 
___
### Description of classes
The package `configuration` contains the classes:
- `ItemProperties` - describes the properties of game items;
- `LocationProperties` - describes the properties of game locations;
- `ModelConfig` - class containing arrays of properties of items and locations.

The package `constant` contains the classes:
- `LocationRules` - contains lists of locations where special rules apply;
- `ResourceFilesPath` - paths to json files that contain information for creating locations and items.

The package `model` contains the classes:
- `Character`, `Location`, `Item` - classes of appropriate game models.
