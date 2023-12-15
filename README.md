In order to download inputs, you have to retrieve your session cookie from https://adventofcode.com/ and then add it in a file `.adventofcode.session` at your home folder.
build project jar
```sh
mvn clean install -DskipTests
```

then you can launch the download from project root folder :
```sh
./aoc download --day 1
```

to generate day files execute:
```sh
./aoc scaffold --day 1
```