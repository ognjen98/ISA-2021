# ISA-2021
Java SDK verzija 16<br />
PosgreSql verzija 13<br />

Alati koji su korisceni za jednostavniju izradu projekta:<br />
1. InteliJ IDEA 2021.1(Community edition)<br />
2. pgAdmin 4 v4.25<br />
3. VS Code<br />

Koraci za pokretanje:<br />
1. Postaviti Environment varijable na sledeci nacin:<br />
    1.1. U "Path" varijablu dodati putanju do PostgreSql bin foldera, ako vec ne postoji (C:\Program Files\PostgreSQL\13\bin)<br />
    1.2. Kreirati Environment varijablu JDBC_DATABASE_USERNAME i postaviti na zeljenu vrednost<br />
    1.3. Kreirati Environment varijablu JDBC_DATABASE_PASSWORD i postaviti na zeljenu vrednost<br />
    1.4. Otvoriti pgAdmin i kreirati bazu desnim klikom na Databases/Create/Database<br />
        ![baza](https://user-images.githubusercontent.com/58110517/185405713-6d41e320-d6d3-4ac4-880c-5b359fa853fb.png)<br />
    1.5. Popuniti ime_baze i ostaviti Owner da bude postgres. Zatim kreirati Environment varijablu JDBC_ISA_DATABASE_URL i postaviti vrednost na           jdbc:postgresql://localhost:5432/ime_baze     <br />
2. Otvoriti isa projekat u InteliJ IDEA<br />
3. Desni klik na IsaApplication i zatim Run IsaApplication.main() pokrece aplikaciju(ukoliko se ne pokrene ispratiti sliku ispod)<br />
  ![inteliJ podesavanja](https://user-images.githubusercontent.com/58110517/185410359-5798733e-efbd-4e44-864a-ab8b089982ca.png)<br />
4. Otvoriti frontend projekat u VS kodu i otvoriti podfolder src/app u integrisanom terminalu VS koda<br />
5. Izvrsiti komandu npm install<br />
6. Kada to zavrsi, izvrsiti komandu ng serve -o<br />

Skripta se nalazi na putanji: ISA-2021/isa/src/main/resources/data.sql.<br />
Sifre za sve usere su 123.
