# QuestingLib
**QuestingLib** is a standalone quest generation library for Java-based games.
It provides a flexible, modular system for creating dynamic and replayable quest content that can be easily integrated into any Java game or engine.

# ✨ Features
* Procedural quest generation
* Modular quest components
* Easy integration into Java projects
* Lightweight and engine-agnostic
* Designed for replayability

# 📦 Getting and Building the Source

Clone the repository and build the library using Gradle:
```
git clone https://github.com/LotrExtendedTeam/QuestingLib.git
cd QuestingLib
./gradlew build
```
After building, the compiled outputs will be in the 'QuestingLib/builds' folder.

# 🛠 IDE Setup

## Eclipse
1. Generate Eclipse project files:
``` 
./gradlew eclipse
```
2. Open Eclipse → File → Import → General → Existing Projects into Workspace
3. Select the ``QuestingLib`` folder and finish the import.

## IntelliJ IDEA

1. Open IntelliJ and select Open or Import.
2. Navigate to the QuestingLib folder.
3. If prompted, use the Gradle configuration to set up the project.
4. Gradle will handle dependencies and project structure automatically.


# 🧠 Design Thought Process

## Highest-Level Design
When prompted, the questing engine returns a quest based on a set of available parameters.
The engine’s job is to select a quest that fits the current game context and constraints.

## High-Level Design
When a quest is requested:

1. The engine iterates through a weighted list of quest categories.
2. It applies restrictions to eliminate categories and subcategories that don’t fit the current context.
3.Once a valid pool of quests remains, the engine selects one at random.
This ensures both structure (via weighting and rules) and variety (via randomness).

## Low-Level Design
### Quest Categories
*  Categories define broad quest types, such as:
  * Single-goal vs multi-goal
  * Procedurally generated vs statically authored
* Categories can contain subcategories to further specialize behavior.

### Quest Restrictions
Restrictions prevent certain quests from appearing under specific conditions.

Examples of restrictions include:
* Biomes / regions
* Time of day
* NPC presence
* Player alignment
* Held items
* World state flags

### Quest Context
When a quest is requested, the engine first generates a context object that describes the current environment and situation.
This context is then:
*  Passed to quest categories to rule out invalid subcategories.
*  Passed to subcategories to validate parameters and generate eligible quests.

### Engine Initialization
* The engine should be created when the implementing project parameters are valid.
* All quest categories, subcategories, and restriction types are registered at initialization time.
* This registration is not done inside the library.
* Instead, the implementing project is responsible for defining and registering its own categories, subcategories, and rules.