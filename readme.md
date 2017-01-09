# Photography website (http://dametenebra.com)

#### This repo contains :

 * A full dynamic Angularjs & php website (microservice pattern)
 * A MySQL database to list photos, links, categories, messages, owners
 * A full java backend to manage database and ftp

## Why ?

I have made this solution for my wife, she wanted a website to expose her pictures.
All components i used are free and well documented, that's why i share this project.
However, make it yours if you want, but please : 
* not just copy/past the website, make it your own (the css used here is very simple)
* quote me 
* and not sell it.

## MySQL database

#### To init your db, use the script php/database/create_db.sql :

 * replace `yourdbname` (line 14, 16 & 17) by your db name
 * copy it in phpmyadmin or sql console and execute
 * create a user and update php/services/connection/connection.php with your domain, user login & password and db name

## Java backend

#### To use the backend program, you need to create a file data/conf/conf.properties with theses informations : 

```javascript
DEFAULT_PATH={default path to your service location : ex http://localhost/dtpv2/php/services/}
prog.target=prod|local
ftp.url={url of your ftp server}
ftp.user={login of your ftp server}
ftp.pwd={password of your ftp server}
```

#### Then :
 
 * open project in your favorite java IDE
 * import project with maven
 * rename the project 
 * test it -> DAOIT.java
 * export it in .jar file
 * put your conf file with the good path in your jar location
 * enjoy :)

## Frontend

#### Replace :

* title tag
* all meta tags
* header title
* footer title

#### Update : 

* legal notice
* all references to dametenebra.com in the php/services/message/message_insert.php (email notification)

#### Build :

```bash
$ npm install
```
```bash
# dev build (with non min css && js)
$ grunt dev
```
```bash
# prod build (with min css && js)
$ grunt prod
```

## Contact

Contact me at [eliott.laversin@gmail.com](mailto:eliott.laversin@gmail.com?subject=[DTPV2]-subject) for any request.