# MeadowSMPFlower plugin

This plugin allows 17 players to select a unique effect on the server upon their connection. This effect is permanent. No effect can be assigned to two different players.

## Effects
- Allium Euphoria : Increases the damage dealt to a player.
- Azure Bluet Purity : Unlimited oxygen underwater.
- Blue orchid refreshment : Full food and saturation
- Cornflower tranquility : Reduce fall damage
- Dandelion Gust : Jump effect
- Lilac camouflage : Invisibility effect
- Lily of the valley serenity : Damage resistance effect
- Orange tulip warmth : Fire resistance effect
- Oxeye daisy clarity : Night vision effect
- Peony charm : Luck effect
- Pink tulip delight : Define max health and regeneration effect
- Poppy vitality : Define max health
- Rose blossom : Regeneration effect
- Sunflower radiance : Speed effect
- Tulip grace : Slow falling effect
- White tulipe innoncence : Poison damage canceled
- Wither rose affliction : Gives the victim a wither effect during PvP combat.

## Configuration
- **messages.alreadySelected** Message sent if the player selects an effect tha is already taken.
- **gui.title** Title of the menu opened during the player's first connection.
- **gui.<effect>.displayname** Display of the item name in the menu (Accepts Minecraft color codes).
- **gui.<effect>.material** Item ID displayed in the menu.
- **gui.<effect>.lore** Description of the item displayed in the menu (Accepts Minecraft color codes).
- **gui.poppy-vitality.maxHealth** Maximum number of half hearts of the player.
- **gui.allium-euphoria.increaseAttackDamage** Percentage of additional damage to be added to the initial damage (1 = +100%).
- **gui.wither-rose-affliction.witherEffectInSeconds** Duration of the Wither effect given to the victim.
- **gui.pink-tulip-delight.maxHealth** Maximum number of half hearts of the player.

## Technical

To reset players effects, you need to delete the *database.db* file and restart the server. If you make changes to the configuration file, it is necessary to restart the server. **Do not use the /reload command!**

> **Spigot:** 1.20
> 
> **Java:** 17