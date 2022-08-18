# ISA-2021
Java SDK verzija 16
PosgreSql verzija 13

Alati koji su korisceni za jednostavniju izradu projekta:
1. InteliJ IDEA 2021.1(Community edition)
2. pgAdmin 4 v4.25
3. VS Code

Koraci za pokretanje:
1. Postaviti Environment varijable na sledeci nacin:
    1.1. U "Path" varijablu dodati putanju do PostgreSql bin foldera, ako vec ne postoji (C:\Program Files\PostgreSQL\13\bin)
    1.2. Kreirati Environment varijablu JDBC_DATABASE_USERNAME i postaviti na zeljenu vrednost
    1.3. Kreirati Environment varijablu JDBC_DATABASE_PASSWORD i postaviti na zeljenu vrednost
    1.4. Otvoriti pgAdmin i kreirati bazu desnim klikom na Databases/Create/Database
        ![baza](https://user-images.githubusercontent.com/58110517/185405713-6d41e320-d6d3-4ac4-880c-5b359fa853fb.png)
    1.5. Popuniti ime_baze i ostaviti Owner da bude postgres. Zatim kreirati Environment varijablu JDBC_ISA_DATABASE_URL i postaviti vrednost na           jdbc:postgresql://localhost:5432/ime_baze     
2. Otvoriti isa projekat u InteliJ IDEA
3. Desni klik na IsaApplication i zatim Run IsaApplication.main() pokrece aplikaciju(ukoliko se ne pokrene ispratiti sliku ispod)
  ![inteliJ podesavanja](https://user-images.githubusercontent.com/58110517/185410359-5798733e-efbd-4e44-864a-ab8b089982ca.png)
4. Otvoriti frontend projekat u VS kodu i otvoriti podfolder src/app u integrisanom terminalu VS koda
5. Izvrsiti komandu npm install
6. Kada to zavrsi, izvrsiti komandu ng serve -o

Skripta se nalazi na putanji: ISA-2021/isa/src/main/resources/data.sql.
Sifre za sve usere su 123.
