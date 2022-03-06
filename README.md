# Why ChatHelper?
I created chat helper because there isn't another plugin as simple as chat helper. Every other
chat related plugin that exists, is over sized, and over complicated. This plugin bridges
the gap between necessity, and simplicity.

### Features
- `Non bypassing Anti curse`
- `Max chat caps`
- `Anti chat/command spam`
- `PlaceholderAPI support`
- `Custom chat formatting`

### Config
This is the default config.yml file. The default anti-curse implementation should work fine
for most people, but everything can be changed here!

```yaml
# Words that aren't allowed
# to be typed in chat
#
# This check is designed to see
# if the message contains the
# anti-curse, and it also has
# additional checks for bypassing
# using symbols or spaces
#
# Disable by AntiCurse: []
AntiCurse:
  - fuk
  - fuck
  - shit
  - bitch
  - dick
  - nigg
  - puss
  - cunt
  - whor
  - vagina
  - penis

# This is if you want the
# word "ass" to be checked for in chat
#
# This exists for a more efficient
# check, because ass is slightly more
# annoying due to words like "grass" and "class"
AssCheck: true

# Maximum caps in a message
# Disable by MaxCaps: 0
MaxCaps: 20

# Cooldown in-between chat messages
# Disable by ChatCooldown: 0
ChatCooldown: 2

# List of commands to have a cooldown
# Cooldowns do NOT store in a database
# therefore I do NOT recommend using this
# for serious cooldown usages like kits
#
# Disable by CommandCooldowns: []
CommandCooldowns:
  - msg:2
  - message:2
  - tell:2
  - whisper:2
  - pay:10

# Simple chat formatting
# has PlaceholderAPI support
# Disable by ChatFormat: ""
ChatFormat: "&r%player% &8&l> &f%message%"

# Disable any of the below by -> ""
AntiCurseMessage: "&cWarning: Your message contains a bad word!"
MaxCapsMessage: "&cWarning: Too many caps!"
ChatCooldownMessage: "&cError: You can't type for another %time%!"
CommandCooldownMessage: "&cError: You can't type '%command%' for another %time%!"
ReloadMessage: "&aChatHelper config successfully reloaded!"
```
