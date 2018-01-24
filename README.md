# Unloader

Unloader is a small utility mod that unloads dimensions that aren't in use.

This can free up tick time and RAM for your server or client! (Although it's not a whole lot... Don't expect it to magically fix everything.)

## What does it do?
Checks dimensions every 30 seconds (600 ticks) for unused dimensions. An unused dimension is classified by the dimension having no chunks or entities loaded. Once it finds one, it tells forge to unload it. Unloaded dimensions can be loaded back fine.


## TODO:
  - Possibly create a dimension ID blacklist. (The overworld is blacklisted by default.)

### Contributors:
  - Unnoen (author)
  
#### Thanks
  - McJty, for the straightforward tutorials.
  - Nallar, for NTweaks which this is based off.
