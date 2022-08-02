## Arclight Plugin Patcher

**NOTE**: This repository only contains the WorldEdit-related patch.   
You'll have to code it yourself, with looking through Arclight API & Spigot API,   
if you want to patch other plugins not properly working on Arclight.

### Update Instructions

**WARN**: If you don't remove `{server-root}/.arclight/patcher` directory, the new patch won't apply! 

1. You want to replace plugin file, so you *must* stop the server first.
2. Drop `patcher-loader-(version).jar` in `plugins`.
3. Head to `.arclight`. This will exist on the root of the server.
4. Remove `patcher` directory. If you don't remove it, the new patch won't be applied.
5. Now you can start the server. 

### Build

The build uses Spigot-API, so you don't have to build it with buildtools.

1. You'll want to make your clone _(don't just download- the build will fail if you just download it)_ of this repo.   
I'll recommend creating your own fork before cloning, and work on that fork.
2. If you want to customize it, you'll want to head up to two `build.gradle`, one at the root and the other in `patcher-loader`.   
You can manage the build distributions with some uncommenting (removing `//`) and commenting (adding `//` at the very first of the very line).   
    > **WARN**: If two-or-more definition of Spigot-API or Java Version exists, the build can fail. Implement *only* one Spigot-API, and define *only* one JAVA version!   
    **Note**: If you want to customize it out of the code, you'll have to add your own version of Spigot-API. Further, you'll have to check the build number(like `R0.1`) to successfully configure it.
3. You'll want to grab some Java JDK, according to the setup on `build.gradle` of the root.   
If you didn't, just install Java 17. That's the default.   
Don't forget to add their `bin` directory to `PATH`!
    > *Note: You don't have to configure this if you've set up the IDE.*
4. Do `gradlew build`. Note that you can also use `gradlew jar`.
    > OS-specific:
    > * Windows CMD: `gradlew.bat build`
    > * Windows Powershell: `.\gradlew.bat build`
    > * Bash: `./gradlew build`
5. Grab plugin in `patcher-loader/build/libs`.
