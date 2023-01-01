# Direction Menggunakan Openstreetmap

## Instalasi

- Buka setting.gradle, dibagian repositories tambahkan 
```sh
maven { url 'https://jitpack.io' }
```
- Lalu import package namenya di build.gradle
```sh
implementation 'org.osmdroid:osmdroid-android:6.1.13'
implementation 'org.apache.commons:commons-lang3:3.8.1'
implementation 'com.google.code.gson:gson:2.8.6'
implementation 'com.squareup.okhttp3:okhttp:4.7.2'
implementation 'com.github.MKergall:osmbonuspack:6.9.0'
```
- Klik Sync Now
