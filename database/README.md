# MOBLIMA Schema

## Movies
0. (int) movieID - Primary Key
1. (String) title
2. (String) type
3. (String) synopsis
4. (Double) rating
5. (String) showingStatus
6. (String) director
7. (ArrayList< String >) cast
8. (ArrayList< int >) reviewsID - Foreign Key to Reviews' Column 0 - reviewsID

## Reviews
0. (int) reviewsID - Primary Key
1. (int) movieGoersID - Foreign Key to MovieGoers' Column 0 - movieGoersID
2. (double) rating
3. (String) reviews
4. (int) movieID - Foreign Key to Movies' Column 0 - movieID

## Staffs
0. (int) staffID - Primary Key
1. (String) name
2. (String) email
3. (String) passwordHashed

## MovieGoers
0. (int) movieGoersID - Primary Key
1. (String) name
2. (String) email
3. (int) age
4. (String) passwordHashed - Using .hashCode() function
5. (int) mobileNumber
6. (String) TID_List

## Transactions
0. (String) TID
1. (int) movieGoersID - Foreign Key to MovieGoers' Column 0 - movieGoersID
2. (String) bookingDate
3. (String) bookingTime
4. (String) cinemaCode
5. (int) seatingNum
6. (double) price
7. (int) movieID - Foreign Key to Movies' Column 0 - movieID

## Cineplexes
0. (String) cineplexCode
1. (String) name
2. (ArrayList< String >) cinemasCode

## Cinemas
0. (String) cinemaCode
1. (String) cinemaClass
2. (ArrayList< int >) seatingPlan
3. (ArrayList< String >) sessionsID - sessionData+sessionTtime - refer to Sessions column 1 and 2

## Sessions
0. (int) movieID - Foreign Key to Movies' Column 0 - movieID
1. (String) sessionDate
2. (String) sessionTime
3. (ArrayList< int >) seatingPlan

## HolidayData
0. (String) name
1. (String) date

## TicketData
0. (String) movieType
1. (String) cineplexCode
2. (String) ageCat
3. (String) dayType (Weekday/Special)
4. (boolean) afterSix
5. (int) price

## PriceData
0.(String) Cat - e.g. Student/Adult/SeniorCitizen/Special - refers to eve of PH and PH
1.(double) Price
