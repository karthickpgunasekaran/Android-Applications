# Attendance Care

Attendance Care is an android application which helps to maintain and track class attendance for students. Application has User-friendly interface to keep track your attendance.Students can enter timetable and record attendance on daily or weekly basis, also it has the functionality to automatic login based on the GPS location services. The app tracks your overall attendance as well as attendance for individual classes for that semester.Application also has a key feature of going back and changing logs which most of all other app's lack on and also share your attendance report with friends online. Attendance Care also has notification to mark attendance on weekly basis.

## PRO's:
*User-friendly
*One touch marking bunk's
*Easy to attend bunk's
*Cool user-interface
*Calendar enabled
*One-touch attendance calculation
*One time setup
*Notification with preferences
*Customized Predicting attendance
*Share your attendance online



## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:wolox/<reponame>.git
```

## Configuration
### Keystores:
Create `app/keystore.gradle` with the following info:
```gradle
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```
And place both keystores under `app/keystores/` directory:
- `playstore.keystore`
- `stage.keystore`


## Build variants
Use the Android Studio *Build Variants* button to choose between **production** and **staging** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Maintainers
This project is mantained by:
* [Federico Ramundo](http://github.com/framundo)


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
