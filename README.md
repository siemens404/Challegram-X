## [Challegram X](https://t.me/Challegram_X) — experimental Telegram client based on [Telegram X](https://github.com/TGX-Android/Telegram-X).

![Challegram X](/images/feature.png)

[![Channel](https://img.shields.io/badge/Channel-Telegram-blue.svg)](https://t.me/Challegram_X)
[![Chat](https://img.shields.io/badge/Chat%20EN-Telegram-blue.svg)](https://t.me/chgx_ch)

## Compilation guide
To compile Challegram X you must follow a few simple steps. 

**Preconditions:** you must have configured `keystore.properties`, Telegram `API_ID`, `API_HASH` and YouTube `API_KEY`

[Obtaining Telegram API_ID and API_HASH](https://core.telegram.org/api/obtaining_api_id)

[Obtaining YouTube API_KEY](https://developers.google.com/youtube/android/player/register)

1. Run `$ scripts/./setup.sh` and follow up the instructions.
2. If you specified package name that's different from the one Challegram X uses, [setup Firebase](https://firebase.google.com/docs/android/setup) and replace `google-services.json` with the one that's suitable for the `app.id` you need

## Building
**Challegram X** can be built from the terminal with:
```
./gradlew assembleUniversalRelease
```
You can use this flavors instead of `universal`:
* `arm64`: **arm64-v8a** build with `minSdkVersion` set to `21`
* `arm32`: **armeabi-v7a** build
* `x64`: **x86_64** build with `minSdkVersion` set to `21`
* `x86`: **x86** build

## Thanks:
- [Telegram](https://github.com/DrKLO/Telegram)
- [Telegram X](https://github.com/TGX-Android/Telegram-X)
- [Nekogram](https://gitlab.com/Nekogram/Nekogram)
- [Nekogram X](https://github.com/NekoX-Dev/NekoX)
- [Catogram](https://github.com/Catogram/Catogram)
- [tgxdc](https://github.com/iTaysonLab/tgxdc)
