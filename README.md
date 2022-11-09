## [Challegram X](https://t.me/Challegram_X) — experimental Telegram client based on [Telegram X](https://github.com/TGX-Android/Telegram-X).

![Challegram X](/images/feature.png)

[![Channel](https://img.shields.io/badge/Channel-Telegram-blue.svg)](https://t.me/Challegram_X)
[![Chat](https://img.shields.io/badge/Chat%20EN-Telegram-blue.svg)](https://t.me/chgx_ch)

## Compilation guide
To compile Challegram X you must follow a few simple steps. 

**Preconditions:** you must have configured `keystore.properties`, Telegram `API_ID`, `API_HASH` and YouTube `API_KEY`

[Obtaining Telegram API_ID and API_HASH](https://core.telegram.org/api/obtaining_api_id)

<<<<<<< HEAD
[Obtaining YouTube API_KEY](https://developers.google.com/youtube/android/player/register)
=======
* At least **5,34GB** of free disk space: **487,10MB** for source codes and around **4,85GB** for files generated after building all variants
* **4GB** of RAM
* **macOS** or **Linux**-based operating system. **Windows** platform is supported by using [MSYS](https://www.msys2.org/) (e.g., [Git Bash](https://gitforwindows.org/)).
>>>>>>> 2916d7f7 (Building on Windows platform via MSYS)

1. Run `$ scripts/./setup.sh` and follow up the instructions.
2. If you specified package name that's different from the one Challegram X uses, [setup Firebase](https://firebase.google.com/docs/android/setup) and replace `google-services.json` with the one that's suitable for the `app.id` you need

## Building
**Challegram X** can be built from the terminal with:
```
./gradlew assembleUniversalRelease
```
You can use this flavors instead of `universal`:
* `arm64`: **arm64-v8a** build with `minSdkVersion` set to `21`

#### Ubuntu

* git with LFS: `# apt install git git-lfs`
* Run `$ git lfs install` for the current user, if you didn't have `git-lfs` previously installed

#### Windows

* Shell with `git`, `wget`, and `make` utilities:
    * **MSYS**: `$ pacman -S make git mingw-w64-x86_64-git-lfs`
    * **Git Bash**: 
        1. Download [wget](https://eternallybored.org/misc/wget/), unzip `wget.exe` and move to your `Git\mingw64\bin\`
        2. Download [make](https://sourceforge.net/projects/ezwinports/files/make-4.3-without-guile-w32-bin.zip), unzip and copy the contents to your `Git\mingw64\` merging the folders, but do **NOT** overwrite any existing files
* Run `$ git lfs install` for the current user, if you didn't have `git lfs` previously initialized

### Building

1. `$ git clone --recursive --depth=1 --shallow-submodules https://github.com/TGX-Android/Telegram-X tgx` — clone **Telegram X** with submodules
2. In case you forgot the `--recursive` flag, `cd` into `tgx` directory and: `$ git submodule init && git submodule update --init --recursive --depth=1`
3. Create `keystore.properties` file outside of source tree with the following properties:<br/>`keystore.file`: absolute path to the keystore file<br/>`keystore.password`: password for the keystore<br/>`key.alias`: key alias that will be used to sign the app<br/>`key.password`: key password.<br/>**Warning**: keep this file safe and make sure nobody, except you, has access to it. For production builds one could use a separate user with home folder encryption to avoid harm from physical theft
4. `$ cd tgx`
5. Run `$ scripts/./setup.sh` and follow up the instructions
6. If you specified package name that's different from the one Telegram X uses, [setup Firebase](https://firebase.google.com/docs/android/setup) and replace `google-services.json` with the one that's suitable for the `app.id` you need
7. Now you can open the project using **[Android Studio](https://developer.android.com/studio/)** or build manually from the command line: `./gradlew assembleUniversalRelease`.

#### Available flavors

* `arm64`: **arm64-v8a** build with `minSdkVersion` set to `21` (**Lollipop**)
>>>>>>> 2916d7f7 (Building on Windows platform via MSYS)
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
