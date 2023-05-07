# Alkalmazás buildelése Gradle-lel

A meglévő alkalmazást forkoltuk, hogy a módosítások ne érintsék az eredeti programot.

A Gradle telepítését követően a projekt gyökerében a `gradle init` parancsot kiadva legenerálódtak a szükséges gradle fájlok.
A script alapértelmezett értékekkel töltötte fel a `build.gradle` és a `settings.gradle` fájlokat, valamint létrejött egy 
wrapper script is, a `gradlew`, ami a buildelés menetét hivatott megkönnyíteni. Mivel a gradle a forrásfájlokat a `src/main/java`
mappastruktúrában várja és a projektben ezek egyszerűen csak a `src/` mappában voltak, így a buildelés 
csak a fájlok áthelyezését követően volt sikeres. A buildeléshez a projekt mappájában 
kellett kiadni a `./gradlew build` parancsot, ezt követően pedig a `./gradlew run` paranccsal vált futtathatóvá az alkalmazás.